package com.spike.codegenerationservice.component;

import com.spike.codegenerationservice.component.coolie.DatabaseCoolie;
import com.spike.codegenerationservice.component.coolie.RepositoryCodeCoolie;
import com.spike.codegenerationservice.configuration.GlobalConfig;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class Generator {
    private DatabaseCoolie databaseCoolie;
    private RepositoryCodeCoolie repositoryCodeCoolie;

    public void generate(GlobalConfig globalConfig) {
        var tables = this.databaseCoolie.build(globalConfig.getPackageName());
        this.repositoryCodeCoolie.build(tables);
    }
}



