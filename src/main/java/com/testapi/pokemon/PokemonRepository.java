package com.testapi.pokemon;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Repository
public interface PokemonRepository extends CrudRepository<Pokemon, Integer> {
    Optional<Pokemon> findByName(String name);
    List<Pokemon> findAll();

    Optional<Pokemon> findByNameIgnoreCase(String name);
    Optional<Pokemon> findByNameAndImageUrl(String name, String img);

}
