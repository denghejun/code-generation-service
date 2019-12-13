package com.spike.codegenerationservice.component.peon.sql;

import com.spike.codegenerationservice.component.peon.abstraction.SQLPeon;
import com.spike.codegenerationservice.model.DataTable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SQLRPeon extends SQLPeon {

    private static String R = "SELECT * FROM %s";

    @Override
    public String build(DataTable table) {
        return String.format(R, table.getMetaName());
    }
}
