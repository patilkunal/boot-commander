package com.inovision.commander.audit;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AuditHistoryRepository extends CrudRepository<AuditHistory, String> {

    List<AuditHistory> findByHistoryTypeAndEntityIdAndCreatedBy(AuditHistoryType historyType, String entityId, String createdBy);
    List<AuditHistory> findByHistoryTypeAndEntityId(AuditHistoryType historyType, String entityId);
}
