package com.spike.codegenerationservice.component.coolie.sql;

import com.spike.codegenerationservice.component.coolie.abstraction.SQLPeon;
import com.spike.codegenerationservice.model.DataTable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SQLRPeon extends SQLPeon {

    private static String C = "INSERT INTO %s(%s) VALUES (%s)";

    @Override
    public String build(DataTable table) {
        return "find all sql";
    }
}
