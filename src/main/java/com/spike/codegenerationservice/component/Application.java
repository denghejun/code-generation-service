package com.spike.codegenerationservice.component;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.context.ShutdownEndpoint;
import org.springframework.cloud.context.restart.RestartEndpoint;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class Application {
    private RestartEndpoint restartEndpoint;
    private ShutdownEndpoint shutdownEndpoint;

    public void restart() {
        this.restartEndpoint.restart();
    }

    public void shutdown() {
        this.shutdownEndpoint.shutdown();
    }
}
