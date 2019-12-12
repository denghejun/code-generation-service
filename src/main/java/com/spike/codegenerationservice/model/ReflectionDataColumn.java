package com.spike.codegenerationservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReflectionDataColumn {
    private String name;
    private String metaName;
    private boolean primaryKey;

    @Override
    public String toString() {
        return "ReflectionDataColumn{" +
                "name='" + name + '\'' +
                ", metaName='" + metaName + '\'' +
                ", primaryKey=" + primaryKey +
                '}';
    }
}
