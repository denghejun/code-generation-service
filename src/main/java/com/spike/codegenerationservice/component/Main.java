package com.spike.codegenerationservice.component;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class Main {
    private Application application;
    private Generator generator;

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReadyEvent() {
        try {
            this.generator.generate();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            this.application.shutdown();
        }
    }
}
