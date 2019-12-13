package com.spike.codegenerationservice.component.peon.abstraction;

import com.spike.codegenerationservice.model.DataColumn;
import com.spike.codegenerationservice.model.DataTable;

import java.util.List;
import java.util.stream.Collectors;

public abstract class SQLPeon extends Peon<DataTable, String> {
    protected String buildWhereConditionStatementOnlyPrimaryColumns(DataTable dataTable) {
        return this.buildEqualStatement(dataTable.getPrimaryColumns(), " AND ");
    }

    protected String buildEqualsStatementExcludePrimaryKeys(DataTable dataTable) {
        return this.buildEqualStatement(dataTable.getNonPrimaryColumns(), ",");
    }

    protected String buildValueStatementForAllColumns(DataTable table) {
        return table.getColumns()
                .stream()
                .map(c -> ":" + c.getName())
                .collect(Collectors.joining(","));
    }

    protected String buildSelectBodyStatementForAllColumns(DataTable table) {
        return table.getColumns()
                .stream()
                .map(DataColumn::getMetaName)
                .collect(Collectors.joining(","));
    }

    private String buildEqualStatement(List<DataColumn> columns, String delimiter) {
        return columns.stream()
                .map(c -> c.getMetaName() + "=:" + c.getName())
                .collect(Collectors.joining(delimiter));
    }
}
