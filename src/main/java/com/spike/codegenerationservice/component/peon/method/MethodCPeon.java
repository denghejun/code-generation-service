package com.spike.codegenerationservice.component.peon.method;

import com.spike.codegenerationservice.component.peon.abstraction.MethodSpecPeon;
import com.spike.codegenerationservice.component.peon.sql.SQLCPeon;
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
 * @SqlUpdate("INSERT INTO T_POLICY_PROD_LIAB(CONFIG_ID, ITEM_ID, LIAB_ID, LIAB_TYPE)
 * VALUES (:configId, :itemId, :liabId, :liabType)")
 * void insert(@BindBean TPolicyProdLiab tPolicyProdLiab);
 */
@Component
@AllArgsConstructor
@Slf4j
public class MethodCPeon extends MethodSpecPeon {

    private static final String METHOD_NAME_C = "insert";
    private SQLCPeon sqlcPeon;

    @Override
    public MethodSpec build(DataTable table) {
        return MethodSpec.methodBuilder(METHOD_NAME_C)
                .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
                .returns(void.class)
                .addAnnotation(AnnotationSpec.builder(SqlUpdate.class)
                        .addMember(ANNOTATION_MEMBER_VALUE,
                                ANNOTATION_MEMBER_VALUE_FORMAT,
                                this.sqlcPeon.build(table))
                        .build())
                .addParameter(ParameterSpec.builder(table.getClazz(), PARAMETER_NAME_ENTITY)
                        .addAnnotation(BindBean.class)
                        .build())
                .build();
    }
}
