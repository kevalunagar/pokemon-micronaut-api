package com.testapi.pokemon;

import com.testapi.exception.InvalidDataException;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;

import java.util.List;

@Controller("/pokemon")
public class PokemonController {

  private final PokemonService pokemonService;

  public PokemonController(PokemonService pokemonService) {
    this.pokemonService = pokemonService;
  }

  @Get()
  public List<Pokemon> getPokemonList() {
    return pokemonService.get();
  }

  @Get("/{id}")
  public Pokemon getById(@PathVariable Integer id) {
    return pokemonService.getById(id);
  }

  @Post
  public HttpResponse<Pokemon> create(@Body PokemonCreationForm pokemon) {
    return HttpResponse.created(pokemonService.create(pokemon));
  }

  @Put("/{id}")
  public Pokemon update(@Body PokemonUpdateForm pokemon, @PathVariable Integer id) {
    return pokemonService.update(pokemon, id);
  }

  @Delete("/{id}")
  public HttpResponse<String> deleteById(@PathVariable Integer id) {
    try {
      pokemonService.deleteById(id);
      return HttpResponse.ok("Deleted");
    } catch (InvalidDataException e) {
      return HttpResponse.badRequest(e.getMessage());
    }
  }
}
