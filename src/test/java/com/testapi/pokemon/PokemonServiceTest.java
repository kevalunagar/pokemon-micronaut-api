package com.testapi.pokemon;

import com.testapi.power.Power;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class PokemonServiceTest {

    @Test
    public void testCreate(){
        PokemonRepository pokemonRepository = Mockito.mock(PokemonRepository.class);
        Power power = new Power(5, "Fire");
        Pokemon pokemon = new Pokemon(2, "Pikachu", power, null);
        Mockito.when(pokemonRepository.findByName("Pikachu")).thenReturn(Optional.of(pokemon));
    }
}
