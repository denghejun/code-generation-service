package com.spike.codegenerationservice.component.peon;

import com.spike.codegenerationservice.component.peon.abstraction.Peon;
import com.spike.codegenerationservice.component.peon.method.*;
import com.spike.codegenerationservice.configuration.GlobalConfig;
import com.spike.codegenerationservice.model.DataTable;
import com.squareup.javapoet.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Generated;
import javax.lang.model.element.Modifier;
import java.nio.file.Paths;
import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class RepositoryCodePeon extends Peon<List<DataTable>, String> {

    public static final String SUFFIX_REPOSITORY = "Repository";

    private GlobalConfig globalConfig;
    private MethodCPeon methodCPeon;
    private MethodRPeon methodRPeon;
    private MethodUPeon methodUPeon;
    private MethodDPeon methodDPeon;
    private MethodRByIdPeon methodRByIdPeon;

    @Override
    public String build(List<DataTable> dataTables) {
        for (DataTable table : dataTables) {
            try {
                String interfaceName = table.getName() + SUFFIX_REPOSITORY;
                TypeSpec repositoryTypeSpec = TypeSpec.interfaceBuilder(interfaceName)
                        .addModifiers(Modifier.PUBLIC)
                        .addAnnotation(AnnotationSpec.builder(Generated.class)
                                .addMember("value", "$S", RepositoryCodePeon.class)
                                .build())
                        .addMethod(this.methodCPeon.build(table))
                        // .addMethod(this.methodRPeon.build(table))
                        .addMethod(this.methodUPeon.build(table))
                        .addMethod(this.methodDPeon.build(table))
                        .addMethod(this.methodRByIdPeon.build(table))
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
}
