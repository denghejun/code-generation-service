package com.spike.codegenerationservice.component;

import com.spike.codegenerationservice.configuration.GlobalConfig;
import com.spike.codegenerationservice.model.ReflectionDataTable;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class Generator {

    private Coolie coolie;

    public void generate(GlobalConfig globalConfig) {
        List<ReflectionDataTable> tables = this.coolie.buildDataTables(globalConfig.getPackageName());
    }
}



