package edu.unlam.example.pokemonapi.persistence.impl;

import edu.unlam.example.pokemonapi.exceptions.PokemonDatabaseException;
import edu.unlam.example.pokemonapi.model.PokemonEntity;
import edu.unlam.example.pokemonapi.persistence.PokemonDAO;
import edu.unlam.example.pokemonapi.persistence.repository.PokemonRepository;
import edu.unlam.example.pokemonapi.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class PokemonDAOImplTest {

    private PokemonDAO pokemonDAO;
    private PokemonRepository pokemonRepository;

    @Captor
    private ArgumentCaptor<PokemonEntity> captor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pokemonRepository = Mockito.mock(PokemonRepository.class);
        pokemonDAO = new PokemonDAOImpl(pokemonRepository);
    }

    @Test
    void shouldSavePokemonEntity() {
        var pokemon = TestUtils.createPokemonEntity();

        Mockito.when(pokemonRepository.save(captor.capture())).thenReturn(pokemon);

        pokemonDAO.savePokemon(pokemon);

        assertEquals(pokemon, captor.getValue());
    }

    @Test
    void whenSavePokemon_ifFail_thenThrowException() {
        var pokemon = TestUtils.createPokemonEntity();

        Mockito.when(pokemonRepository.save(any())).thenThrow(RuntimeException.class);

        assertThrows(PokemonDatabaseException.class, () -> pokemonDAO.savePokemon(pokemon));
    }
}