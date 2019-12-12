package com.spike.codegenerationservice.component;

import com.spike.codegenerationservice.component.peon.DatabasePeon;
import com.spike.codegenerationservice.component.peon.RepositoryCodePeon;
import com.spike.codegenerationservice.configuration.GlobalConfig;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class Generator {
    private DatabasePeon databasePeon;
    private RepositoryCodePeon repositoryCodePeon;

    public void generate(GlobalConfig globalConfig) {
        var tables = this.databasePeon.build(globalConfig.getPackageName());
        this.repositoryCodePeon.build(tables);
    }
}



