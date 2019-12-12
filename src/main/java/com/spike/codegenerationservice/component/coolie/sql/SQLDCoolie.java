package com.spike.codegenerationservice.component.coolie.sql;

import com.spike.codegenerationservice.component.coolie.abstraction.SQLCoolie;
import com.spike.codegenerationservice.model.DataTable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SQLDCoolie extends SQLCoolie {

    private static String C = "INSERT INTO %s(%s) VALUES (%s)";

    @Override
    public String build(DataTable table) {
        return "delete sql";
    }
}
