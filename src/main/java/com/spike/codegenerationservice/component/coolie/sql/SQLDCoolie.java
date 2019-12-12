package com.spike.codegenerationservice.component.coolie.sql;

import com.spike.codegenerationservice.component.coolie.abstraction.SQLCoolie;
import com.spike.codegenerationservice.model.ReflectionDataTable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@Slf4j
public class SQLDCoolie extends SQLCoolie {

    private static String C = "INSERT INTO %s(%s) VALUES (%s)";

    @Override
    public String build(ReflectionDataTable table) {
        return "delete sql";
    }
}
