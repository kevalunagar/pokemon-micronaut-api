package com.testapi.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import jakarta.inject.Singleton;

@Singleton
public class Serialization implements ApplicationEventListener<ServerStartupEvent> {
    ObjectMapper mapper;

    public Serialization(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void onApplicationEvent(ServerStartupEvent event) {
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES , true);
    }
}
