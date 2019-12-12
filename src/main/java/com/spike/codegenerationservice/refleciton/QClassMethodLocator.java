package com.spike.codegenerationservice.refleciton;

import com.querydsl.core.types.Path;
import com.querydsl.sql.ColumnMetadata;
import com.querydsl.sql.PrimaryKey;
import com.spike.codegenerationservice.model.ReflectionDataColumn;
import com.spike.codegenerationservice.model.ReflectionDataTable;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Slf4j
@AllArgsConstructor
public class QClassMethodLocator {

    private ClassMethodLocator classMethodLocator;

    private String getMetaColumnName(Object qInstance, Path<?> column) {
        List<Class<?>> parameterTypes = new ArrayList<>();
        parameterTypes.add(Path.class);
        return ((ColumnMetadata) this.classMethodLocator.run(qInstance, QClassMethodNameEnum.GET_METADATA, parameterTypes, column)).getName();
    }

    private ArrayList getPrimaryKeys(Object qInstance) {
        return (ArrayList) ((PrimaryKey) this.classMethodLocator.run(qInstance, QClassMethodNameEnum.GET_PRIMARY_KEY)).getLocalColumns().stream().collect(Collectors.toList());
    }

    private List<ReflectionDataColumn> getColumns(Object qInstance) {
        ArrayList columns = (ArrayList) this.classMethodLocator.run(qInstance, QClassMethodNameEnum.GET_COLUMNS);
        return (List<ReflectionDataColumn>) columns.stream().map(col -> {
            String name = ((Path) col).getMetadata().getName();
            String metaName = this.getMetaColumnName(qInstance, (Path) col);
            ArrayList primaryKeys = this.getPrimaryKeys(qInstance);
            boolean isPrimaryKey = primaryKeys.stream().anyMatch(c -> Objects.equals(((Path) c).getMetadata().getName(), name));
            return new ReflectionDataColumn(name, metaName, isPrimaryKey);
        }).collect(Collectors.toList());
    }

    private String getTableName(Object qInstance) {
        return this.classMethodLocator.run(qInstance, QClassMethodNameEnum.GET_TABLE_NAME).toString();
    }

    public ReflectionDataTable getTables(Object qInstance) {
        ReflectionDataTable table = new ReflectionDataTable();
        table.setMetaName(this.getTableName(qInstance));
        table.setColumns(this.getColumns(qInstance));
        return table;
    }
}
