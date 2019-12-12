package com.spike.codegenerationservice.component.coolie;

import com.google.common.collect.Lists;
import com.google.common.reflect.ClassPath.ClassInfo;
import com.spike.codegenerationservice.component.coolie.abstraction.Coolie;
import com.spike.codegenerationservice.model.ReflectionDataColumn;
import com.spike.codegenerationservice.model.ReflectionDataTable;
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
public class DatabaseCoolie extends Coolie<String, List<ReflectionDataTable>> {
    public static final Function<String, Boolean> FILTER_QT = className -> className.startsWith("Q");
    public static final Function<String, Boolean> FILTER_NON_QT = className -> !className.startsWith("Q");
    private ClassLocator classLocator;
    private QClassInstanceLocator qClassInstanceLocator;
    private QClassMethodLocator qClassMethodLocator;

    @Override
    public List<ReflectionDataTable> build(String packageName) {
        return this.populateNonQClassInfo(packageName, buildReflectionDataTables(packageName));
    }

    private List<ReflectionDataTable> buildReflectionDataTables(String food) {
        List<ReflectionDataTable> reflectionDataTables = Lists.newArrayList();
        List<ClassInfo> qClassInfos = this.classLocator.getClassInfos(food, FILTER_QT);
        for (ClassInfo qClass : qClassInfos) {
            Object qInstance = this.qClassInstanceLocator.getQClassInstance(qClass);
            ReflectionDataTable table = this.qClassMethodLocator.getTables(qInstance);
            reflectionDataTables.add(table);
        }

        return reflectionDataTables;
    }

    private List<ReflectionDataTable> populateNonQClassInfo(String packageName, List<ReflectionDataTable> dataTables) {
        List<ClassInfo> nonQClassInfos = this.classLocator.getClassInfos(packageName, FILTER_NON_QT);
        for (ReflectionDataTable table : dataTables) {
            Class tableEntityClass = nonQClassInfos.stream()
                    .filter(classInfo -> classInfo.getSimpleName().equals(table.getName()))
                    .findFirst().get().load();
            table.setClazz(tableEntityClass);
            for (ReflectionDataColumn column : table.getColumns()) {
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
