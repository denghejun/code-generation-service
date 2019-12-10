package com.spike.codegenerationservice.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Generator {
    private final JdbiOperationsForTest jdbiOperationsFortest = new JdbiOperationsForTest();

    public void generate() {
        jdbiOperationsFortest.jdbiOperation();
    }
}
