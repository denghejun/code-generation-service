package com.spike.codegenerationservice.component.coolie;

import com.spike.codegenerationservice.component.coolie.abstraction.Peon;
import com.spike.codegenerationservice.component.coolie.method.*;
import com.spike.codegenerationservice.configuration.GlobalConfig;
import com.spike.codegenerationservice.model.DataTable;
import com.squareup.javapoet.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.lang.model.element.Modifier;
import java.nio.file.Paths;
import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class RepositoryCodePeon extends Peon<List<DataTable>, String> {

    public static final String SUFFIX_REPOSITORY = "Repository";

    private GlobalConfig globalConfig;
    private MethodCPeon methodCCoolie;
    private MethodRPeon methodRCoolie;
    private MethodUPeon methodUCoolie;
    private MethodDPeon methodDCoolie;
    private MethodRByIdPeon methodRByIdCoolie;

    @Override
    public String build(List<DataTable> food) {
        for (DataTable table : food) {
            try {
                String interfaceName = table.getName() + SUFFIX_REPOSITORY;
                TypeSpec repositoryTypeSpec = TypeSpec.interfaceBuilder(interfaceName)
                        .addModifiers(Modifier.PUBLIC)
                        .addMethod(this.methodCCoolie.build(table))
                        .addMethod(this.methodRCoolie.build(table))
                        .addMethod(this.methodUCoolie.build(table))
                        .addMethod(this.methodDCoolie.build(table))
                        .addMethod(this.methodRByIdCoolie.build(table))
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
