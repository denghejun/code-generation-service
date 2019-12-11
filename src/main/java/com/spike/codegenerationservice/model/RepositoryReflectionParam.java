package com.spike.codegenerationservice.model;

import java.util.List;

public class RepositoryReflectionParam {
    private String tableName;
    private List<String> columnNames;
    private List<String> primaryKeyNames;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(List<String> columnNames) {
        this.columnNames = columnNames;
    }

    public List<String> getPrimaryKeyNames() {
        return primaryKeyNames;
    }

    public void setPrimaryKeyNames(List<String> primaryKeyNames) {
        this.primaryKeyNames = primaryKeyNames;
    }
}
