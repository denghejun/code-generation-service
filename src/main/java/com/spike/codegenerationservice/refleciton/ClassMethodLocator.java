package com.spike.codegenerationservice.refleciton;

import com.google.common.collect.Lists;
import com.google.common.reflect.Invokable;
import com.google.common.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Slf4j
@Component
public class ClassMethodLocator {

    public Object run(Object instance, QClassMethodNameEnum methodName, Object... args) {
        return this.run(instance, methodName, null, args);
    }

    public Object run(Object instance, QClassMethodNameEnum methodName, List<Class<?>> parametersClassList, Object... args) {
        Class<?> clazz = instance.getClass();
        TypeToken<?> typeToken = TypeToken.of(clazz);
        Object value = null;

        try {
            Invokable invokable = typeToken.method(clazz.getMethod(methodName.getValue(),
                    parametersClassList == null ? null :
                            parametersClassList.toArray(new Class[parametersClassList.size()])));
            value = invokable.invoke(instance, args);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            log.error(e.getMessage(), e);
        }

        return value;
    }
}
