package com.spike.codegenerationservice.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.context.ShutdownEndpoint;
import org.springframework.cloud.context.restart.RestartEndpoint;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Application {
    @Autowired
    private RestartEndpoint restartEndpoint;

    @Autowired
    private ShutdownEndpoint shutdownEndpoint;

    public void restart() {
        this.log.info("Application is going to be restart willfully ...");
        this.restartEndpoint.restart();
    }

    public void shutdown() {
        this.log.info("Application is going to be shutdown willfully ...");
        this.shutdownEndpoint.shutdown();
    }
}
