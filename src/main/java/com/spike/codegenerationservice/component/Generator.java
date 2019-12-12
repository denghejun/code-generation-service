package com.spike.codegenerationservice.component;

import com.google.common.reflect.ClassPath.ClassInfo;
import com.spike.codegenerationservice.configuration.GlobalConfig;
import com.spike.codegenerationservice.model.ReflectionDataColumn;
import com.spike.codegenerationservice.model.ReflectionDataTable;
import com.spike.codegenerationservice.refleciton.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class Generator {
    private ClassLocator classLocator;
    private QClassInstanceLocator qClassInstanceLocator;
    private QClassMethodLocator qClassMethodLocator;

    public void generate(GlobalConfig globalConfig) {
        List<ClassInfo> qClassInfos = this.classLocator.getClassInfos(globalConfig.getPackageName(), className -> className.startsWith("QT"));
        for (ClassInfo qClass : qClassInfos) {
            Object qInstance = this.qClassInstanceLocator.getQClassInstance(qClass);
            ReflectionDataTable table = this.qClassMethodLocator.getTables(qInstance);
            log.info(table.toString());
        }
    }
}



