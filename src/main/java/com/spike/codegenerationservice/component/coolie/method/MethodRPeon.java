package com.spike.codegenerationservice.component.coolie.method;

import com.spike.codegenerationservice.component.coolie.abstraction.MethodSpecPeon;
import com.spike.codegenerationservice.component.coolie.sql.SQLRPeon;
import com.spike.codegenerationservice.model.DataTable;
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
public class MethodRPeon extends MethodSpecPeon {

    private static final String METHOD_NAME_R = "findAll";
    private SQLRPeon sqlrCoolie;

    @Override
    public MethodSpec build(DataTable table) {
        return MethodSpec.methodBuilder(METHOD_NAME_R)
                .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
                .returns(void.class)
                .addAnnotation(AnnotationSpec.builder(SqlUpdate.class)
                        .addMember(ANNOTATION_MEMBER_VALUE,
                                ANNOTATION_MEMBER_VALUE_FORMAT,
                                this.sqlrCoolie.build(table))
                        .build())
                .addParameter(ParameterSpec.builder(table.getClazz(), PARAMETER_NAME_ENTITY)
                        .addAnnotation(BindBean.class)
                        .build())
                .build();
    }
}
