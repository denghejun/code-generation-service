package com.spike.codegenerationservice.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Main {

    @Autowired
    private Application application;

    @Autowired
    private Generator generator;

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReadyEvent() {
        try {
            this.generator.generate();
        } catch (Exception e) {
            this.log.error(e.getMessage(), e);
        } finally {
            this.application.shutdown();
        }
    }
}
