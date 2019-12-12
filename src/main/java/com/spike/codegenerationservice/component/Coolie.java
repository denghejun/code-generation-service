package com.spike.codegenerationservice.component;

import com.google.common.collect.Lists;
import com.google.common.reflect.ClassPath;
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
public class Coolie {
    public static final Function<String, Boolean> FILTER_QT = className -> className.startsWith("QT");
    private ClassLocator classLocator;
    private QClassInstanceLocator qClassInstanceLocator;
    private QClassMethodLocator qClassMethodLocator;

    public List<ReflectionDataTable> buildDataTables(String packageName) {
        List<ReflectionDataTable> reflectionDataTables = Lists.newArrayList();
        List<ClassPath.ClassInfo> qClassInfos = this.classLocator.getClassInfos(packageName, FILTER_QT);
        for (ClassPath.ClassInfo qClass : qClassInfos) {
            Object qInstance = this.qClassInstanceLocator.getQClassInstance(qClass);
            ReflectionDataTable table = this.qClassMethodLocator.getTables(qInstance);
            reflectionDataTables.add(table);
        }

        return reflectionDataTables;
    }
}
