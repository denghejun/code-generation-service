package com.spike.codegenerationservice.component.coolie;

import com.google.common.collect.Lists;
import com.google.common.reflect.ClassPath.ClassInfo;
import com.spike.codegenerationservice.component.coolie.abstraction.Peon;
import com.spike.codegenerationservice.model.DataColumn;
import com.spike.codegenerationservice.model.DataTable;
import com.spike.codegenerationservice.refleciton.ClassLocator;
import com.spike.codegenerationservice.refleciton.QClassInstanceLocator;
import com.spike.codegenerationservice.refleciton.QClassMethodLocator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
@Slf4j
@AllArgsConstructor
public class DatabasePeon extends Peon<String, List<DataTable>> {
    public static final Function<String, Boolean> FILTER_QT = className -> className.startsWith("Q");
    public static final Function<String, Boolean> FILTER_NON_QT = className -> !className.startsWith("Q");
    private ClassLocator classLocator;
    private QClassInstanceLocator qClassInstanceLocator;
    private QClassMethodLocator qClassMethodLocator;

    @Override
    public List<DataTable> build(String packageName) {
        return this.populateNonQClassInfo(packageName, buildDataTables(packageName));
    }

    private List<DataTable> buildDataTables(String food) {
        List<DataTable> dataTables = Lists.newArrayList();
        List<ClassInfo> qClassInfos = this.classLocator.getClassInfos(food, FILTER_QT);
        for (ClassInfo qClass : qClassInfos) {
            Object qInstance = this.qClassInstanceLocator.getQClassInstance(qClass);
            DataTable table = this.qClassMethodLocator.getTables(qInstance);
            dataTables.add(table);
        }

        return dataTables;
    }

    private List<DataTable> populateNonQClassInfo(String packageName, List<DataTable> dataTables) {
        List<ClassInfo> nonQClassInfos = this.classLocator.getClassInfos(packageName, FILTER_NON_QT);
        for (DataTable table : dataTables) {
            Class tableEntityClass = nonQClassInfos.stream()
                    .filter(classInfo -> classInfo.getSimpleName().equals(table.getName()))
                    .findFirst().get().load();
            table.setClazz(tableEntityClass);
            for (DataColumn column : table.getColumns()) {
                try {
                    Class columnEntityClass = tableEntityClass.getDeclaredField(column.getName()).getType();
                    column.setClazz(columnEntityClass);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
        }

        return dataTables;
    }
}
