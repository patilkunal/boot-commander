package com.inovision.commander.audit;

public enum AuditHistoryType {

    HOST_CHANGE(AuditEntity.HOST, "hostName");

    private AuditEntity auditEntity;
    //This can be list properties. Keeping it simple for now.
    private String auditProperty;

    AuditHistoryType(AuditEntity entity, String property) {
        this.auditEntity = entity;
        this.auditProperty = property;
    }

    public AuditEntity getAuditEntity() {
        return auditEntity;
    }

    public String getAuditProperty() {
        return auditProperty;
    }
}
