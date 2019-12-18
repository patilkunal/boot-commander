package com.inovision.commander.audit;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
@Table(name = "audit_history")
public class AuditHistory {

	@Id
	@Column(name = "id", nullable = false)
    protected String id;
	
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Column(name = "entity", nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private AuditEntity entity;

    @Column(name = "entity_id", nullable = false, length = 32)
    @NotNull
    @Size(max = 32)
    private String entityId;

    @Column(name = "history_type", nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private AuditHistoryType historyType;

    @Column(name = "change_json", nullable = false, length = 2000)
    @Size(max = 2000)
    private String changeJson;

    @Column(name = "created_by", nullable = false, length = 32)
    @NotNull
    @Size(max = 32)
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", nullable = false)
    @NotNull
    private Date createDate;
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AuditEntity getEntity() {
		return entity;
	}

	public void setEntity(AuditEntity entity) {
		this.entity = entity;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public AuditHistoryType getHistoryType() {
		return historyType;
	}

	public void setHistoryType(AuditHistoryType historyType) {
		this.historyType = historyType;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public String getChangeJson() {
		return changeJson;
	}
	
	public void setChangeJson(String changeJson) {
		this.changeJson = changeJson;
	}

	public Map<String, ChangePair> getChangeMap() {
        Map<String, ChangePair> map = new HashMap<>();
        if(StringUtils.isNotEmpty(changeJson)) {
            try {
                Map<String, Map> fieldMap = OBJECT_MAPPER.readValue(changeJson, Map.class);
                fieldMap.forEach((k, v) -> {
                    Map<String, String> cpMap = (Map) v;
                    ChangePair cp = new ChangePair();
                    cp.setNewValue(cpMap.get("newValue"));
                    cp.setOldValue(cpMap.get("oldValue"));
                    map.put(k, cp);
                });
            } catch (Exception e) {
            }
        }
        return map;
    }

    public static final class ChangePair {
        public ChangePair() {}
        public ChangePair(String oldval, String newVal) {
            this.oldValue = oldval;
            this.newValue = newVal;
        }
        private String oldValue;
        private String newValue;
        
        public void setNewValue(String newValue) {
			this.newValue = newValue;
		}
        
        public String getNewValue() {
			return newValue;
		}
        
        public void setOldValue(String oldValue) {
			this.oldValue = oldValue;
		}
        
        public String getOldValue() {
			return oldValue;
		}
    }
	
}
