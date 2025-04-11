package edu.unlam.example.pokemonapi.service.impl;

import edu.unlam.example.pokemonapi.client.PokeapiClient;
import edu.unlam.example.pokemonapi.domain.PokemonEntity;
import edu.unlam.example.pokemonapi.exceptions.PokemonDatabaseException;
import edu.unlam.example.pokemonapi.repository.PokemonRepository;
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

    private PokemonRepository pokemonRepository;

    private PokemonService pokemonService;

    @Captor
    private ArgumentCaptor<PokemonEntity> captor;

    private static final String POKEMON_NAME = "pikachu";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pokeapiClient = Mockito.mock(PokeapiClient.class);
        pokemonRepository = Mockito.mock(PokemonRepository.class);
        pokemonService = new PokemonServiceImpl(pokeapiClient, pokemonRepository);
    }

    @Test
    void shouldGetPokemonByName() {
        var pokemon = TestUtils.createPokemonResponse(POKEMON_NAME);

        Mockito.when(pokeapiClient.getPokemonByName(POKEMON_NAME))
                .thenReturn(pokemon);

        var resp = pokemonService.getPokemon(POKEMON_NAME);

        assertEquals(pokemon, resp);
    }

    @Test
    void shouldSavePokemonEntity() {
        var pokemon = TestUtils.createPokemonEntity();

        Mockito.when(pokemonRepository.save(captor.capture())).thenReturn(pokemon);

        pokemonService.savePokemon(pokemon);

        assertEquals(pokemon, captor.getValue());
    }

    @Test
    void whenSavePokemon_ifFail_thenThrowException() {
        var pokemon = TestUtils.createPokemonEntity();

        Mockito.when(pokemonRepository.save(any())).thenThrow(RuntimeException.class);

        assertThrows(PokemonDatabaseException.class, () -> pokemonService.savePokemon(pokemon));
    }

}