package com.spike.codegenerationservice.component;

import com.google.common.reflect.ClassPath.ClassInfo;
import com.spike.codegenerationservice.configuration.GlobalConfig;
import com.spike.codegenerationservice.refleciton.ClassLocator;
import com.spike.codegenerationservice.refleciton.QClassInstanceLocator;
import com.spike.codegenerationservice.refleciton.QClassMethodLocator;
import com.spike.codegenerationservice.refleciton.QClassMethodNameEnum;
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
        List<ClassInfo> classInfos = this.classLocator.getClassInfos(globalConfig.getPackageName(), className -> className.startsWith("QT"));
        for (ClassInfo classInfo : classInfos) {
            Object instance = this.qClassInstanceLocator.getInstance(classInfo);
            Object value = this.qClassMethodLocator.run(instance, QClassMethodNameEnum.GET_TABLE_NAME);
            log.debug(value.toString());
        }
    }
}



