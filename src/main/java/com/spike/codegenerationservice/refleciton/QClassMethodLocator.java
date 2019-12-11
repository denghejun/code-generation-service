package com.spike.codegenerationservice.refleciton;

import com.google.common.reflect.Invokable;
import com.google.common.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;

@Slf4j
@Component
public class QClassMethodLocator {

    public Object run(Object instance, QClassMethodNameEnum methodName) {
        Class<?> clazz = instance.getClass();
        TypeToken<?> typeToken = TypeToken.of(clazz);
        Object value = null;

        try {
            Invokable invokable = typeToken.method(clazz.getMethod(methodName.getValue()));
            value = invokable.invoke(instance);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            log.error(e.getMessage(), e);
        }

        return value;
    }
}
