package com.inovision.commander.audit;

import java.util.List;

public interface AuditHistoryService {

    public void saveHistory(AuditHistoryType type, String id, String fieldName, String oldValue, String newValue, String changeByUserId, String orgId);
    public void saveHistory(AuditHistoryType type, Object oldObject, Object newObject, List<String> fieldName, String changeByUserId, String orgId);
    public List<AuditHistory> getHistory(String objectId, String userId, AuditHistoryType type);
    public List<AuditHistory> getHistory(String objectId, AuditHistoryType type);
}
