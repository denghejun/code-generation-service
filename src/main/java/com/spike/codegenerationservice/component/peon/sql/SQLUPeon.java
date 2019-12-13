package com.spike.codegenerationservice.component.peon.sql;

import com.spike.codegenerationservice.component.peon.abstraction.SQLPeon;
import com.spike.codegenerationservice.model.DataColumn;
import com.spike.codegenerationservice.model.DataTable;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class SQLUPeon extends SQLPeon {

    private static String C = "UPDATE %s SET %s WHERE %s";

    @Override
    public String build(DataTable table) {
        var tableName = table.getMetaName();
        var sets = format(table.getNonPrimaryColumns());
        var wheres = format(table.getPrimaryColumns());
        return String.format(C, tableName, sets, wheres);
    }

    private String format(List<DataColumn> columns) {
        return String.join(",", columns.stream()
                .map(c -> c.getMetaName() + "=:" + c.getName())
                .collect(Collectors.toList()));
    }
}
