package com.spike.codegenerationservice.component.coolie;

import com.spike.codegenerationservice.component.coolie.abstraction.Coolie;
import com.spike.codegenerationservice.configuration.GlobalConfig;
import com.spike.codegenerationservice.model.ReflectionDataTable;
import com.squareup.javapoet.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.springframework.stereotype.Component;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class RepositoryCodeCoolie extends Coolie<List<ReflectionDataTable>, String> {

    public static final String SUFFIX_REPOSITORY = "Repository";
    public static final String METHOD_NAME_C = "insert";
    public static final String METHOD_NAME_R = "findAll";
    public static final String METHOD_NAME_R_ID = "findById";
    public static final String METHOD_NAME_U = "update";
    public static final String METHOD_NAME_D = "delete";
    public static final String PARAMETER_NAME_ENTITY = "entity";
    public static final String ANNOTATION_MEMBER_VALUE = "value";
    public static final String ANNOTATION_MEMBER_VALUE_FORMAT = "$S";

    private SQLCoolie sqlCoolie;
    private GlobalConfig globalConfig;

    @Override
    public String build(List<ReflectionDataTable> food) {
        for (ReflectionDataTable table : food) {
            try {
                TypeSpec repositoryTypeSpec = this.buildClass(table.getName())
                        .addMethod(this.buildC(table))
                        .addMethod(this.buildR(table))
                        .addMethod(this.buildU(table))
                        .addMethod(this.buildD(table))
                        .addMethod(this.buildRById(table))
                        .build();

                JavaFile.builder(globalConfig.getRepoPackageName(), repositoryTypeSpec)
                        .build()
                        .writeTo(Paths.get(globalConfig.getOutputPath()));
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }

        return "OK";
    }

    private TypeSpec.Builder buildClass(String name) {
        return TypeSpec.interfaceBuilder(name + SUFFIX_REPOSITORY).addModifiers(Modifier.PUBLIC);
    }

    private MethodSpec buildC(ReflectionDataTable table) {
        return MethodSpec.methodBuilder(METHOD_NAME_C)
                .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
                .returns(void.class)
                .addAnnotation(AnnotationSpec.builder(SqlUpdate.class)
                        .addMember(ANNOTATION_MEMBER_VALUE,
                                ANNOTATION_MEMBER_VALUE_FORMAT,
                                this.sqlCoolie.build(table).C)
                        .build())
                .addParameter(ParameterSpec.builder(table.getClazz(), PARAMETER_NAME_ENTITY)
                        .addAnnotation(BindBean.class)
                        .build())
                .build();
    }

    private MethodSpec buildR(ReflectionDataTable table) {
        return MethodSpec.methodBuilder(METHOD_NAME_R)
                .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
                .returns(void.class)
                .addAnnotation(AnnotationSpec.builder(SqlUpdate.class)
                        .addMember(ANNOTATION_MEMBER_VALUE,
                                ANNOTATION_MEMBER_VALUE_FORMAT,
                                this.sqlCoolie.build(table).R)
                        .build())
                .addParameter(ParameterSpec.builder(table.getClazz(), PARAMETER_NAME_ENTITY)
                        .addAnnotation(BindBean.class)
                        .build())
                .build();
    }

    private MethodSpec buildRById(ReflectionDataTable table) {
        return MethodSpec.methodBuilder(METHOD_NAME_R_ID)
                .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
                .returns(void.class)
                .addAnnotation(AnnotationSpec.builder(SqlUpdate.class)
                        .addMember(ANNOTATION_MEMBER_VALUE,
                                ANNOTATION_MEMBER_VALUE_FORMAT,
                                this.sqlCoolie.build(table).R_BY_ID)
                        .build())
                .addParameter(ParameterSpec.builder(table.getClazz(), PARAMETER_NAME_ENTITY)
                        .addAnnotation(BindBean.class)
                        .build())
                .build();
    }

    private MethodSpec buildU(ReflectionDataTable table) {
        return MethodSpec.methodBuilder(METHOD_NAME_U)
                .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
                .returns(void.class)
                .addAnnotation(AnnotationSpec.builder(SqlUpdate.class)
                        .addMember(ANNOTATION_MEMBER_VALUE,
                                ANNOTATION_MEMBER_VALUE_FORMAT,
                                this.sqlCoolie.build(table).U)
                        .build())
                .addParameter(ParameterSpec.builder(table.getClazz(), PARAMETER_NAME_ENTITY)
                        .addAnnotation(BindBean.class)
                        .build())
                .build();
    }

    private MethodSpec buildD(ReflectionDataTable table) {
        return MethodSpec.methodBuilder(METHOD_NAME_D)
                .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
                .returns(void.class)
                .addAnnotation(AnnotationSpec.builder(SqlUpdate.class)
                        .addMember(ANNOTATION_MEMBER_VALUE,
                                ANNOTATION_MEMBER_VALUE_FORMAT,
                                this.sqlCoolie.build(table).D)
                        .build())
                .addParameter(ParameterSpec.builder(table.getClazz(), PARAMETER_NAME_ENTITY)
                        .addAnnotation(BindBean.class)
                        .build())
                .build();
    }
}
