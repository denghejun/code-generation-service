package com.spike.codegenerationservice.component;

import com.spike.codegenerationservice.configuration.GlobalConfig;
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
    private GlobalConfig globalConfig;

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReadyEvent() {
        try {
            log.info(this.globalConfig.toString());
            this.generator.generate(this.globalConfig);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            this.application.shutdown();
        }
    }
}
