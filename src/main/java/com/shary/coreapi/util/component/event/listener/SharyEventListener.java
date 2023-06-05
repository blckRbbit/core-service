package com.shary.coreapi.util.component.event.listener;

import com.github.shary2023.core.component.patcher.EntityPatcher;
import com.shary.coreapi.util.component.event.EntityEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public abstract class SharyEventListener<M, R extends JpaRepository<M, ?>,
        E extends ApplicationEvent & EntityEvent<M>> {

    @Autowired
    protected R repository;
    protected EntityPatcher<M> patcher;

    public SharyEventListener() {
    }

    public SharyEventListener(R repository) {
        this.repository = repository;
    }

    public SharyEventListener(R repository, EntityPatcher<M> patcher) {
        this.repository = repository;
        this.patcher = patcher;
    }

    @EventListener
    public abstract void onApplicationEvent(E event);
}
