package com.spike.codegenerationservice.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ReflectionDataTable {
    private String name;
    private String metaName;
    private List<ReflectionDataColumn> columns;

    public List<ReflectionDataColumn> getPrimaryColumns() {
        return columns == null ? null : columns.stream().filter(col -> col.isPrimaryKey()).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "ReflectionDataTable{" +
                "metaName='" + metaName + '\'' +
                ", columns=" + columns +
                '}';
    }
}
