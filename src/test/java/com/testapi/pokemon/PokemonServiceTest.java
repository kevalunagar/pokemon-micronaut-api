package com.testapi.pokemon;

import com.testapi.exception.InvalidDataException;
import com.testapi.power.Power;
import com.testapi.power.PowerService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

public class PokemonServiceTest {

  PokemonRepository pokemonRepository;
  PowerService powerService;
  PokemonService pokemonService;
  Pokemon pikachu, paras, bulbasaur, ditto;
  PokemonCreationForm pokemonToCreate;
  PokemonUpdateForm pokemonToUpdate;

  @BeforeEach
  void setUp() {
    pokemonRepository = Mockito.mock(PokemonRepository.class);
    powerService = Mockito.mock(PowerService.class);
    pokemonService = new PokemonService(pokemonRepository, powerService);
    pikachu = new Pokemon(2, "Pikachu", new Power(5, "Fire"), null);
    paras = new Pokemon(3, "Paras", new Power(5, "Fire"), null);
    bulbasaur = new Pokemon(4, "Bulbasaur", new Power(4, "Grass"), null);
    ditto = new Pokemon(5, "Ditto", new Power(5, "Fire"), null);
  }

  @Test
  void testGetPokemon() {
    Mockito.when(pokemonRepository.findAll()).thenReturn(List.of(pikachu, paras, bulbasaur, ditto));
    List<Pokemon> pokemonList = pokemonService.get();
    Mockito.verify(pokemonRepository).findAll();
    Assertions.assertThat(pokemonList)
        .containsExactlyInAnyOrderElementsOf(List.of(pikachu, paras, bulbasaur, ditto));
  }

  @Test
  void testFindByNameThatExists() {
    pokemonToCreate = new PokemonCreationForm("Pikachu", 5);
    Mockito.when(pokemonRepository.findByName(pokemonToCreate.getName()))
        .thenReturn(Optional.ofNullable(pikachu));
    Assertions.assertThatThrownBy(() -> pokemonService.create(pokemonToCreate))
        .isInstanceOf(InvalidDataException.class);
  }

  @Test
  void testGetPower() {
    pokemonToCreate = new PokemonCreationForm("Pikachu", 5);
    Power generatedPower = new Power(5, "Fire");
    Mockito.when(powerService.get(pokemonToCreate.getPower())).thenReturn(generatedPower);
    Power power = powerService.get(pokemonToCreate.getPower());
    Mockito.verify(powerService).get(pokemonToCreate.getPower());
    Assertions.assertThat(power).isEqualTo(generatedPower);
  }

  @Test
  void testCreatePokemon() {
    pokemonToCreate = new PokemonCreationForm("Charmeleon", 5);
    Power power = new Power(5, "Fire");
    Pokemon expectedPokemon = new Pokemon(7, "Charmeleon", power, null);
    Mockito.when(pokemonRepository.findByName(pokemonToCreate.getName()))
        .thenReturn(Optional.ofNullable(null));
    Mockito.when(pokemonRepository.save(Mockito.any())).thenReturn(expectedPokemon);
    Mockito.when(powerService.get(pokemonToCreate.getPower())).thenReturn(power);

    Pokemon createdPokemon = pokemonService.create(pokemonToCreate);
    Mockito.verify(pokemonRepository).findByName(pokemonToCreate.getName());
    Mockito.verify(powerService).get(pokemonToCreate.getPower());
    Mockito.verify(pokemonRepository).save(Mockito.any());

    Assertions.assertThat(createdPokemon).isEqualTo(expectedPokemon);
  }

  @Test
  void testUpdateNameNotExists() {
    pokemonToUpdate = new PokemonUpdateForm("abc", 5, null);
    Mockito.when(pokemonRepository.findByName(pokemonToUpdate.getName()))
        .thenReturn(Optional.ofNullable(null));
    Assertions.assertThatThrownBy(() -> pokemonService.update(pokemonToUpdate, 10))
        .isInstanceOf(InvalidDataException.class);
  }

  @Test
  void testUpdatePokemon() {
    pokemonToUpdate = new PokemonUpdateForm("Pikachu", 4, null);
    Power power = new Power(4, "Grass");
    Pokemon expectedPokemon = new Pokemon(2, "Pikachu", power, null);
    Mockito.when(pokemonRepository.findByName(pokemonToUpdate.getName()))
        .thenReturn(Optional.ofNullable(pikachu));
    Mockito.when(pokemonRepository.update(Mockito.any())).thenReturn(expectedPokemon);
    Mockito.when(powerService.get(pokemonToUpdate.getPower())).thenReturn(power);

    Pokemon updatedPokemon = pokemonService.update(pokemonToUpdate, 2);
    Mockito.verify(pokemonRepository).findByName(pokemonToUpdate.getName());
    Mockito.verify(powerService).get(pokemonToUpdate.getPower());
    Mockito.verify(pokemonRepository).update(Mockito.any());

    Assertions.assertThat(updatedPokemon).isEqualTo(expectedPokemon);
  }

  @Test
  void testGetById() {
    Mockito.when(pokemonRepository.findById(2)).thenReturn(Optional.ofNullable(pikachu));
    Pokemon pokemon = pokemonService.getById(2);
    Mockito.verify(pokemonRepository).findById(2);
    Assertions.assertThat(pokemon).isEqualTo(pikachu);
  }

  @Test
  void testGetByIdException() {
    Mockito.when(pokemonRepository.findById(8)).thenReturn(Optional.ofNullable(null));
    Assertions.assertThatThrownBy(() -> pokemonService.getById(8))
        .isInstanceOf(InvalidDataException.class);
  }

  @Test
  void testDeleteById() {
    Mockito.when(pokemonRepository.existsById(5)).thenReturn(true);
    pokemonService.deleteById(5);
    Mockito.verify(pokemonRepository).existsById(5);
    Mockito.verify(pokemonRepository).deleteById(5);
  }

  @Test
  void testDeleteByIdException() {
    Mockito.when(pokemonRepository.existsById(8)).thenReturn(false);
    Assertions.assertThatThrownBy(() -> pokemonService.deleteById(8))
        .isInstanceOf(InvalidDataException.class);
  }
}
