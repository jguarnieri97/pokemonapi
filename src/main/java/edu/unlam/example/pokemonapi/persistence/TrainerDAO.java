package edu.unlam.example.pokemonapi.persistence;

import edu.unlam.example.pokemonapi.model.TrainerEntity;

import java.util.List;

public interface TrainerDAO {

    TrainerEntity getTrainerById(Long id);
    void saveTrainer(TrainerEntity trainer);
    List<TrainerEntity> findAll();
}
