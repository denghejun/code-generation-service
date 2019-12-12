package com.spike.codegenerationservice.refleciton;

import com.google.common.reflect.ClassPath.ClassInfo;
import com.google.common.reflect.Reflection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Slf4j
@Component
public class QClassInstanceLocator {
    public Object getQClassInstance(ClassInfo classInfo) {
        String simpleName = classInfo.getSimpleName();
        Class<?> clzz = classInfo.load();
        Reflection.initialize(clzz);
        char[] chars = simpleName.substring(1).toCharArray();
        chars[0] = Character.toLowerCase(chars[0]);
        String staticInstanceFieldName = String.valueOf(chars);
        Object instance = null;

        try {
            Field instanceField = clzz.getDeclaredField(staticInstanceFieldName);
            instance = instanceField.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            log.error(e.getMessage(), e);
        }

        return instance;
    }
}
