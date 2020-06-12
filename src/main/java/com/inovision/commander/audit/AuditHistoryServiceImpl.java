package com.inovision.commander.audit;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional(readOnly = true)
public class AuditHistoryServiceImpl implements AuditHistoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuditHistoryServiceImpl.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private AuditHistoryRepository auditHistoryRepository;

    @Override
    @Transactional(readOnly = false)
    public void saveHistory(AuditHistoryType type, String id, String fieldName, String oldValue, String newValue, String changeByUserId, String orgId) {
        AuditHistory history = new AuditHistory();
        history.setHistoryType(type);
        history.setEntity(type.getAuditEntity());
        history.setEntityId(id);
        history.setCreatedBy(changeByUserId);
        history.setCreateDate(new Date());
        Map<String, AuditHistory.ChangePair> map = new HashMap<>();
        map.put(fieldName, new AuditHistory.ChangePair(oldValue, newValue));
        try {
            history.setChangeJson(OBJECT_MAPPER.writeValueAsString(map));
            auditHistoryRepository.save(history);
        } catch(JsonProcessingException jpe) {
            LOGGER.error("Error in saving audit history : " + jpe.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void saveHistory(AuditHistoryType type, Object prevState, Object newState, List<String> fieldName, String changeByUserId, String orgId) {
        // cannot audit if objects are not of same types,
        if(!areSameTypes(prevState, newState)) 
            return;

        AuditHistory history = new AuditHistory();
        history.setHistoryType(type);
        history.setEntity(type.getAuditEntity());
        history.setCreatedBy(changeByUserId);
        history.setCreateDate(new Date());
        Map<String, AuditHistory.ChangePair> map = new HashMap<>();
        fieldName.forEach((field) -> {
            try {
                String id = BeanUtils.getProperty(prevState, "id");
                history.setEntityId(id);
                String oldVal = BeanUtils.getProperty(prevState, field);
                String newVal = BeanUtils.getProperty(newState, field);
                AuditHistory.ChangePair cp = new AuditHistory.ChangePair(oldVal, newVal);
                map.put(field, cp);
            } catch (Exception e) {
                LOGGER.error("Error in getting bean property: " + e.getMessage());
            }
        });
        try {
            if (!map.isEmpty()) {
                history.setChangeJson(OBJECT_MAPPER.writeValueAsString(map));
            }
            auditHistoryRepository.save(history);
        } catch(JsonProcessingException jpe) {
            LOGGER.error("Error in saving audit history : " + jpe.getMessage());
        }
    }

    @Override
    public List<AuditHistory> getHistory(String objectId, String userId, AuditHistoryType type) {
        return auditHistoryRepository.findByHistoryTypeAndEntityIdAndCreatedBy(type, objectId, userId);
    }

    @Override
    public List<AuditHistory> getHistory(String objectId, AuditHistoryType type) {
        return auditHistoryRepository.findByHistoryTypeAndEntityId(type, objectId);
    }

    private boolean areSameTypes(Object obj1, Object obj2) {
        boolean same = obj1 != null 
            && obj2 != null 
            && obj1.getClass().equals(obj2.getClass());

        if(same) {
            try {
                String id1 = BeanUtils.getProperty(obj1, "id");
                String id2 = BeanUtils.getProperty(obj1, "id");
                same = id1.equals(id2);
            } catch(Exception e) {
                //cannot compare objects based on id property
            }
        }
        return same;
    }

}
