package com.spike.codegenerationservice.refleciton;

public enum QClassMethodNameEnum {
    GET_TABLE_NAME("getTableName"),
    GET_PRIMARY_KEY("getPrimaryKey"),
    GET_COLUMNS("getColumns"),
    GET_METADATA("getMetadata");

    private String value;

    QClassMethodNameEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
