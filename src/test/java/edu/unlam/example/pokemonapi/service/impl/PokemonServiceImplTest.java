package edu.unlam.example.pokemonapi.service.impl;

import edu.unlam.example.pokemonapi.client.PokeapiClient;
import edu.unlam.example.pokemonapi.model.PokemonEntity;
import edu.unlam.example.pokemonapi.exceptions.PokemonDatabaseException;
import edu.unlam.example.pokemonapi.persistence.PokemonDAO;
import edu.unlam.example.pokemonapi.persistence.repository.PokemonRepository;
import edu.unlam.example.pokemonapi.service.PokemonService;
import edu.unlam.example.pokemonapi.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class PokemonServiceImplTest {

    private PokeapiClient pokeapiClient;

    private PokemonService pokemonService;


    private static final String POKEMON_NAME = "pikachu";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pokeapiClient = Mockito.mock(PokeapiClient.class);
        pokemonService = new PokemonServiceImpl(pokeapiClient);
    }

    @Test
    void shouldGetPokemonByName() {
        var pokemon = TestUtils.createPokemonResponse(POKEMON_NAME);

        Mockito.when(pokeapiClient.getPokemonByName(POKEMON_NAME))
                .thenReturn(pokemon);

        var resp = pokemonService.getPokemon(POKEMON_NAME);

        assertEquals(pokemon, resp);
    }

}