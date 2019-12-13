package com.spike.codegenerationservice.component.peon.method;

import com.spike.codegenerationservice.component.peon.abstraction.MethodSpecPeon;
import com.spike.codegenerationservice.component.peon.sql.SQLRPeon;
import com.spike.codegenerationservice.model.DataTable;
import com.squareup.javapoet.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.springframework.stereotype.Component;

import javax.lang.model.element.Modifier;

/**
 * @SqlQuery("SELECT * FROM T_POLICY_PROD_LIAB")
 * @RegisterBeanMapper(TPolicyProdLiab.class) List<TPolicyProdLiab> findAll();
 */
@Component
@AllArgsConstructor
@Slf4j
public class MethodRPeon extends MethodSpecPeon {

    private static final String METHOD_NAME_R = "findAll";
    private SQLRPeon sqlrPeon;

    @Override
    public MethodSpec build(DataTable table) {
        return MethodSpec.methodBuilder(METHOD_NAME_R)
                .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
                .returns(this.ofListParameterType(table.getClazz()))
                .addAnnotation(AnnotationSpec.builder(SqlQuery.class)
                        .addMember(ANNOTATION_MEMBER_VALUE,
                                ANNOTATION_MEMBER_VALUE_FORMAT,
                                this.sqlrPeon.build(table))
                        .build())
                .addAnnotation(AnnotationSpec.builder(RegisterBeanMapper.class)
                        .addMember(ANNOTATION_MEMBER_VALUE, CodeBlock.builder()
                                .add(table.getName() + ".class")
                                .build())
                        .build())
                .build();
    }
}
