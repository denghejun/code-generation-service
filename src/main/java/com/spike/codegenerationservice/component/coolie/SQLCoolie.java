package com.spike.codegenerationservice.component.coolie;

import com.spike.codegenerationservice.component.coolie.abstraction.Coolie;
import com.spike.codegenerationservice.model.ReflectionDataTable;
import com.spike.codegenerationservice.model.SQLStatement;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@Slf4j
@AllArgsConstructor
public class SQLCoolie extends Coolie<ReflectionDataTable, SQLStatement> {
    private static String C = "INSERT INTO %s(%s) VALUES (%s)";

    @Override
    public SQLStatement build(ReflectionDataTable table) {
        return new SQLStatement(this.buildC(table),
                this.buildR(table),
                this.buildU(table),
                this.buildD(table),
                this.buildRById(table));
    }

    private String buildC(ReflectionDataTable table) {
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

    private String buildR(ReflectionDataTable table) {
        return "buildR";
    }

    private String buildU(ReflectionDataTable table) {
        return "buildU";
    }

    private String buildD(ReflectionDataTable table) {
        return "buildD";
    }

    private String buildRById(ReflectionDataTable table) {
        return "buildRById";
    }
}
