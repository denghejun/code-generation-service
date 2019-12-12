package com.spike.codegenerationservice.component.peon.sql;

import com.spike.codegenerationservice.component.peon.abstraction.SQLPeon;
import com.spike.codegenerationservice.model.DataTable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@Slf4j
public class SQLCPeon extends SQLPeon {

    private static String C = "INSERT INTO %s(%s) VALUES (%s)";

    @Override
    public String build(DataTable table) {
        String tableName = table.getMetaName();
        String columns = String.join(",", table.getColumns()
                .stream()
                .map(c -> c.getMetaName())
                .collect(Collectors.toList()));

        String values = String.join(",", table.getColumns()
                .stream()
                .map(c -> ":" + c.getName())
                .collect(Collectors.toList()));

        return String.format(C, tableName, columns, values);
    }
}
