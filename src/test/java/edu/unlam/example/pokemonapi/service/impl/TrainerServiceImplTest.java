package edu.unlam.example.pokemonapi.service.impl;

import edu.unlam.example.pokemonapi.domain.TrainerEntity;
import edu.unlam.example.pokemonapi.dto.TrainerRequest;
import edu.unlam.example.pokemonapi.exceptions.TrainerDatabaseException;
import edu.unlam.example.pokemonapi.exceptions.TrainerNotFoundException;
import edu.unlam.example.pokemonapi.repository.TrainerRepository;
import edu.unlam.example.pokemonapi.service.PokemonService;
import edu.unlam.example.pokemonapi.service.TrainerService;
import edu.unlam.example.pokemonapi.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TrainerServiceImplTest {

    private TrainerRepository trainerRepository;

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
        trainerRepository = mock(TrainerRepository.class);
        pokemonService = mock(PokemonService.class);
        trainerService = new TrainerServiceImpl(trainerRepository, pokemonService);
    }

    @Test
    void shouldCreateTrainer() {
        var trainer = trainerService.createTrainer(TRAINER_NAME);

        verify(trainerRepository).save(any());
        assertEquals(TRAINER_NAME, trainer.getName());
        assertEquals(0, trainer.getPokemons().size());
    }

    @Test
    void shouldGetTrainerById() {
        var trainer = TestUtils.createTrainerEntity(TRAINER_NAME);

        when(trainerRepository.findById(ID)).thenReturn(Optional.of(trainer));

        var response = trainerService.getTrainer(ID);

        assertEquals(trainer.getName(), response.getName());
        assertEquals(trainer.getId(), response.getId());
    }

    @Test
    void whenGetTrainer_ifNotExist_thenThrowException() {

        when(trainerRepository.findById(ID)).thenReturn(Optional.empty());

        assertThrows(TrainerNotFoundException.class, () -> trainerService.getTrainer(ID));
    }

    @Test
    void shouldDeleteTrainer() {
        var trainer = TestUtils.createTrainerEntity(TRAINER_NAME);

        when(trainerRepository.findById(ID)).thenReturn(Optional.of(trainer));
        when(trainerRepository.save(captor.capture())).thenReturn(trainer);

        trainerService.deleteTrainer(ID);

        var trainerEntity = captor.getValue();

        assertFalse(trainerEntity.isActive());
    }

    @Test
    void whenDeleteTrainer_ifDatabaseException_thenThrowException() {
        var trainer = TestUtils.createTrainerEntity(TRAINER_NAME);

        when(trainerRepository.findById(ID)).thenReturn(Optional.of(trainer));
        when(trainerRepository.save(any())).thenThrow(RuntimeException.class);

        assertThrows(TrainerDatabaseException.class, () -> trainerService.deleteTrainer(ID));
    }

    @Test
    void shouldAddPokemonToTrainer() {
        var trainer = TestUtils.createTrainerEntity(TRAINER_NAME);
        var pokemon = TestUtils.createPokemonResponse(POKEMON_NAME);

        when(pokemonService.getPokemon(POKEMON_NAME)).thenReturn(pokemon);
        when(trainerRepository.findById(ID)).thenReturn(Optional.of(trainer));

        var response = trainerService.addPokemon(ID, TrainerRequest.builder().name(POKEMON_NAME).build());
        var pokemonResponse = response.getPokemons().get(0);

        assertEquals(POKEMON_NAME, pokemonResponse.getName());
        assertEquals(0, pokemonResponse.getId());
    }
}