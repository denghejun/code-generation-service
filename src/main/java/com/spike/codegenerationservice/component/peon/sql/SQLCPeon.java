package com.spike.codegenerationservice.component.peon.sql;

import com.spike.codegenerationservice.component.peon.abstraction.SQLPeon;
import com.spike.codegenerationservice.model.DataTable;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@Slf4j
public class SQLCPeon extends SQLPeon {

    private static String C = "INSERT INTO %s(%s) VALUES (%s)";

    @Override
    public String build(DataTable dataTable) {
        var table = dataTable.getMetaName();
        var columns = this.buildSelectBodyStatementForAllColumns(dataTable);
        var values = this.buildValueStatementForAllColumns(dataTable);
        return String.format(C, table, columns, values);
    }
}
