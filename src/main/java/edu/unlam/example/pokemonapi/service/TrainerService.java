package edu.unlam.example.pokemonapi.service;

import edu.unlam.example.pokemonapi.dto.TrainerResponse;

import java.util.List;

public interface TrainerService {

    TrainerResponse createTrainer(String name);
    List<TrainerResponse> getAllTrainers();
    TrainerResponse getTrainer(Long id);
    void deleteTrainer(Long id);

}
