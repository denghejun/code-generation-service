package com.spike.codegenerationservice.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RepositoryReflectionParam {
    private String tableName;
    private List<String> columnNames;
    private List<String> primaryKeyNames;
}
