package com.spike.codegenerationservice.component.peon.method;

import com.spike.codegenerationservice.component.peon.abstraction.MethodSpecPeon;
import com.spike.codegenerationservice.component.peon.sql.SQLRByIdPeon;
import com.spike.codegenerationservice.model.DataTable;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.MethodSpec;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.springframework.stereotype.Component;

import javax.lang.model.element.Modifier;

/**
 * @SqlQuery("SELECT * FROM T_POLICY_PROD_LIAB WHERE CONFIG_ID=:configId")
 * @RegisterBeanMapper(TPolicyProdLiab.class) TPolicyProdLiab findById(Long configId);
 */
@Component
@AllArgsConstructor
@Slf4j
public class MethodRByIdPeon extends MethodSpecPeon {

    private static final String METHOD_NAME_R_ID = "findById";
    private SQLRByIdPeon sqlrByIdPeon;

    @Override
    public MethodSpec build(DataTable table) {
        MethodSpec.Builder builder = MethodSpec.methodBuilder(METHOD_NAME_R_ID)
                .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
                .returns(table.getClazz())
                .addAnnotation(AnnotationSpec.builder(SqlQuery.class)
                        .addMember(ANNOTATION_MEMBER_VALUE,
                                ANNOTATION_MEMBER_VALUE_FORMAT,
                                this.sqlrByIdPeon.build(table))
                        .build())
                .addAnnotation(AnnotationSpec.builder(RegisterBeanMapper.class)
                        .addMember(ANNOTATION_MEMBER_VALUE, CodeBlock.builder()
                                .add(table.getName() + ".class")
                                .build())
                        .build());
        this.populateParametersBaseOnPrimaryColumns(table, builder);
        return builder.build();
    }
}
