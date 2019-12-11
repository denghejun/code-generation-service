package com.spike.codegenerationservice.refleciton;

import com.google.common.collect.Lists;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ClassLocator {
    public List<ClassInfo> getClassInfos(String packageName) {
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try {
            ClassPath classpath = ClassPath.from(loader);
            return classpath.getTopLevelClasses(packageName).stream().collect(Collectors.toList());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return Lists.newArrayList();
        }
    }

    public List<ClassInfo> getClassInfos(String packageName, Function<String, Boolean> classNameFilter) {
        return this.getClassInfos(packageName)
                .stream()
                .filter(classInfo -> classNameFilter.apply(classInfo.getSimpleName()))
                .collect(Collectors.toList());
    }
}
