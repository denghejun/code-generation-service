package com.spike.codegenerationservice.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class DataTable {
    private Class<?> clazz;
    private String name;
    private String metaName;
    private List<DataColumn> columns;

    public List<DataColumn> getPrimaryColumns() {
        return columns == null ? null : columns.stream().filter(col -> col.isPrimaryKey()).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "DataTable{" +
                "clazz=" + clazz +
                ", name='" + name + '\'' +
                ", metaName='" + metaName + '\'' +
                ", columns=" + columns +
                '}';
    }
}
