package com.spike.codegenerationservice.component.peon.sql;

import com.spike.codegenerationservice.component.peon.abstraction.SQLPeon;
import com.spike.codegenerationservice.model.DataTable;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SQLDPeon extends SQLPeon {

    private static String D = "DELETE FROM %s WHERE %s";

    @Override
    public String build(DataTable dataTable) {
        var table = dataTable.getMetaName();
        var wheres = this.buildWhereConditionStatementOnlyPrimaryColumns(dataTable);
        return String.format(D, table, wheres);
    }
}
