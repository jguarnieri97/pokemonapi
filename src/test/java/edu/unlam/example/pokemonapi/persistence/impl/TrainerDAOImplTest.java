package edu.unlam.example.pokemonapi.persistence.impl;

import edu.unlam.example.pokemonapi.exceptions.TrainerNotFoundException;
import edu.unlam.example.pokemonapi.persistence.TrainerDAO;
import edu.unlam.example.pokemonapi.persistence.repository.TrainerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TrainerDAOImplTest {

    private TrainerRepository trainerRepository;

    private TrainerDAO trainerDAO;

    public static final long ID = 1L;


    @BeforeEach
    void setUp() {
        trainerRepository = mock(TrainerRepository.class);
        trainerDAO = new TrainerDAOImpl(trainerRepository);
    }

    @Test
    void whenGetTrainer_ifNotExist_thenThrowException() {

        when(trainerRepository.findById(ID)).thenReturn(Optional.empty());

        assertThrows(TrainerNotFoundException.class, () -> trainerDAO.getTrainerById(ID));
    }
}