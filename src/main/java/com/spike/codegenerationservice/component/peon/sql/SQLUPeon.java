package com.spike.codegenerationservice.component.peon.sql;

import com.spike.codegenerationservice.component.peon.abstraction.SQLPeon;
import com.spike.codegenerationservice.model.DataTable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SQLUPeon extends SQLPeon {

    private static String C = "INSERT INTO %s(%s) VALUES (%s)";

    @Override
    public String build(DataTable table) {
        return "update sql";
    }
}
