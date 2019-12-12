package com.spike.codegenerationservice.component.coolie.method;

import com.spike.codegenerationservice.component.coolie.abstraction.MethodSpecCoolie;
import com.spike.codegenerationservice.component.coolie.sql.SQLCCoolie;
import com.spike.codegenerationservice.model.ReflectionDataTable;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.springframework.stereotype.Component;

import javax.lang.model.element.Modifier;

@Component
@AllArgsConstructor
@Slf4j
public class MethodCCoolie extends MethodSpecCoolie {

    private static final String METHOD_NAME_C = "insert";
    private SQLCCoolie sqlcCoolie;

    @Override
    public MethodSpec build(ReflectionDataTable table) {
        return MethodSpec.methodBuilder(METHOD_NAME_C)
                .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
                .returns(void.class)
                .addAnnotation(AnnotationSpec.builder(SqlUpdate.class)
                        .addMember(ANNOTATION_MEMBER_VALUE,
                                ANNOTATION_MEMBER_VALUE_FORMAT,
                                this.sqlcCoolie.build(table))
                        .build())
                .addParameter(ParameterSpec.builder(table.getClazz(), PARAMETER_NAME_ENTITY)
                        .addAnnotation(BindBean.class)
                        .build())
                .build();
    }
}
