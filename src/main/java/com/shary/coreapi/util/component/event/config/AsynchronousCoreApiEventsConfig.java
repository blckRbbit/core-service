package com.shary.coreapi.util.component.event.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

/**
 * Configuration enable asynchronous event handling
 */
@Configuration
public class AsynchronousCoreApiEventsConfig {
    @Bean(name = "applicationEventMulticaster")
    public ApplicationEventMulticaster simpleApplicationEventMulticaster() {
        SimpleApplicationEventMulticaster eventMultiCaster =
                new SimpleApplicationEventMulticaster();

        eventMultiCaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
        return eventMultiCaster;
    }
}
