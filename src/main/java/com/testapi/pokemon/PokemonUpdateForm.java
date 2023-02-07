package com.testapi.pokemon;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PokemonUpdateForm extends PokemonCreationForm{
    private final String imageUrl;

    @JsonCreator
    public PokemonUpdateForm(@JsonProperty("name") String name, @JsonProperty("power") Integer power, @JsonProperty("imageUrl") String imageUrl) {
        super(name, power);
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
