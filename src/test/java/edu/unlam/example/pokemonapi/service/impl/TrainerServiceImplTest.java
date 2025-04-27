package edu.unlam.example.pokemonapi.service.impl;

import edu.unlam.example.pokemonapi.model.TrainerEntity;
import edu.unlam.example.pokemonapi.dto.TrainerRequest;
import edu.unlam.example.pokemonapi.exceptions.TrainerDatabaseException;
import edu.unlam.example.pokemonapi.exceptions.TrainerNotFoundException;
import edu.unlam.example.pokemonapi.persistence.PokemonDAO;
import edu.unlam.example.pokemonapi.persistence.TrainerDAO;
import edu.unlam.example.pokemonapi.service.PokemonService;
import edu.unlam.example.pokemonapi.service.TrainerService;
import edu.unlam.example.pokemonapi.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TrainerServiceImplTest {

    private TrainerDAO trainerRepository;

    private PokemonDAO pokemonRepository;

    private PokemonService pokemonService;

    private TrainerService trainerService;

    @Captor
    private ArgumentCaptor<TrainerEntity> captor;

    private static final String TRAINER_NAME = "Ash";
    private static final String POKEMON_NAME = "Pikachu";
    public static final long ID = 1L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        trainerRepository = mock(TrainerDAO.class);
        pokemonRepository = mock(PokemonDAO.class);
        pokemonService = mock(PokemonService.class);
        trainerService = new TrainerServiceImpl(trainerRepository, pokemonRepository, pokemonService);
    }

    @Test
    void shouldCreateTrainer() {
        var trainer = trainerService.createTrainer(TRAINER_NAME);

        verify(trainerRepository).saveTrainer(any());
        assertEquals(TRAINER_NAME, trainer.getName());
        assertEquals(0, trainer.getPokemons().size());
    }

    @Test
    void shouldGetTrainerById() {
        var trainer = TestUtils.createTrainerEntity(TRAINER_NAME);

        when(trainerRepository.getTrainerById(ID)).thenReturn(trainer);

        var response = trainerService.getTrainer(ID);

        assertEquals(trainer.getName(), response.getName());
        assertEquals(trainer.getId(), response.getId());
    }



    @Test
    void shouldDeleteTrainer() {
        var trainer = TestUtils.createTrainerEntity(TRAINER_NAME);

        when(trainerRepository.getTrainerById(ID)).thenReturn(trainer);
        doNothing().when(trainerRepository).saveTrainer(captor.capture());

        trainerService.deleteTrainer(ID);

        var trainerEntity = captor.getValue();

        assertFalse(trainerEntity.isActive());
    }

    @Test
    void whenDeleteTrainer_ifDatabaseException_thenThrowException() {
        var trainer = TestUtils.createTrainerEntity(TRAINER_NAME);

        when(trainerRepository.getTrainerById(ID)).thenReturn(trainer);
        doThrow(TrainerDatabaseException.class).when(trainerRepository).saveTrainer(any());

        assertThrows(TrainerDatabaseException.class, () -> trainerService.deleteTrainer(ID));
    }

    @Test
    void shouldAddPokemonToTrainer() {
        var trainer = TestUtils.createTrainerEntity(TRAINER_NAME);
        var pokemon = TestUtils.createPokemonResponse(POKEMON_NAME);

        when(pokemonService.getPokemon(POKEMON_NAME)).thenReturn(pokemon);
        when(trainerRepository.getTrainerById(ID)).thenReturn(trainer);

        var response = trainerService.addPokemon(ID, TrainerRequest.builder().name(POKEMON_NAME).build());
        var pokemonResponse = response.getPokemons().get(0);

        assertEquals(POKEMON_NAME, pokemonResponse.getName());
        assertEquals(0, pokemonResponse.getId());
    }
}