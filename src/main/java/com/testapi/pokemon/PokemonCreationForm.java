package com.testapi.pokemon;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.testapi.power.Power;

public class PokemonCreationForm {
    private final String name;
    private final Integer powerId;

    @JsonCreator
    public PokemonCreationForm(@JsonProperty("name") String name, @JsonProperty("power") Integer power) {
        this.name = name;
        this.powerId = power;
    }

    public String getName() {
        return name;
    }

    public Integer getPower() {
        return powerId;
    }
}
