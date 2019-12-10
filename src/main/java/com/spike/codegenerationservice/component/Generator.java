package com.spike.codegenerationservice.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Generator {
    public void generate() {
        this.log.info("Doing work ...5s");
    }
}
