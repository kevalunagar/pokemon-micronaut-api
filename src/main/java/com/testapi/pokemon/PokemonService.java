package com.testapi.pokemon;

import com.testapi.exception.InvalidDataException;
import com.testapi.power.Power;
import com.testapi.power.PowerService;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

@Singleton
public class PokemonService {

  private final PokemonRepository pokemonRepository;
  private final PowerService powerService;

  public PokemonService(PokemonRepository pokemonRepository, PowerService powerService) {
    this.pokemonRepository = pokemonRepository;
    this.powerService = powerService;
  }

  public List<Pokemon> get() {
    return pokemonRepository.findAll();
  }

  public Pokemon create(PokemonCreationForm pokemon) {
    Optional<Pokemon> byName = pokemonRepository.findByName(pokemon.getName());
    if (byName.isPresent()) {
      throw new InvalidDataException("Pokemon name already exists");
    }
    Power power = powerService.get(pokemon.getPower());
    Pokemon newPokemon = new Pokemon();
    newPokemon.setName(pokemon.getName());
    newPokemon.setPower(power);
    return pokemonRepository.save(newPokemon);
  }

  public Pokemon update(PokemonUpdateForm pokemon, Integer id) {
    Optional<Pokemon> byId = pokemonRepository.findById(id);
    if (byId.isPresent()) {
      Power power = powerService.get(pokemon.getPower());
      Pokemon updatePokemon = new Pokemon();
      updatePokemon.setPower(power);
      updatePokemon.setName(pokemon.getName());
      updatePokemon.setImageUrl(pokemon.getImageUrl());
      updatePokemon.setId(id);
      return pokemonRepository.update(updatePokemon);
    } else throw new InvalidDataException("Id Doesn't Exists");
  }

  public Pokemon getById(Integer id) {
    return pokemonRepository
        .findById(id)
        .orElseThrow(() -> new InvalidDataException("Id Doesn't Exists"));
  }

  public void deleteById(Integer id) {
    if (pokemonRepository.existsById(id)) pokemonRepository.deleteById(id);
    else throw new InvalidDataException("Id Doesn't Exists");
  }
}
