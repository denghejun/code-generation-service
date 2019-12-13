package com.spike.codegenerationservice.component.peon.method;

import com.spike.codegenerationservice.component.peon.abstraction.MethodSpecPeon;
import com.spike.codegenerationservice.component.peon.sql.SQLUPeon;
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


/**
 * @SqlUpdate("UPDATE T_POLICY_PROD_LIAB SET ITEM_ID=:itemId, LIAB_ID=:liabId, LIAB_TYPE=:liabType
 * WHERE CONFIG_ID=:configId")
 * void update(@BindBean TPolicyProdLiab entity);
 */
@Component
@AllArgsConstructor
@Slf4j
public class MethodUPeon extends MethodSpecPeon {

    private static final String METHOD_NAME_U = "update";
    private SQLUPeon sqluPeon;

    @Override
    public MethodSpec build(DataTable table) {
        return MethodSpec.methodBuilder(METHOD_NAME_U)
                .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
                .returns(void.class)
                .addAnnotation(AnnotationSpec.builder(SqlUpdate.class)
                        .addMember(ANNOTATION_MEMBER_VALUE,
                                ANNOTATION_MEMBER_VALUE_FORMAT,
                                this.sqluPeon.build(table))
                        .build())
                .addParameter(ParameterSpec.builder(table.getClazz(), PARAMETER_NAME_ENTITY)
                        .addAnnotation(BindBean.class)
                        .build())
                .build();
    }
}
