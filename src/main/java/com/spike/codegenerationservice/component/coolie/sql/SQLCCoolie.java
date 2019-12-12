package com.spike.codegenerationservice.component.coolie.sql;

import com.spike.codegenerationservice.component.coolie.abstraction.SQLCoolie;
import com.spike.codegenerationservice.model.DataTable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@Slf4j
public class SQLCCoolie extends SQLCoolie {

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
