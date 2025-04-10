package edu.unlam.example.pokemonapi.service.impl;

import edu.unlam.example.pokemonapi.domain.TrainerEntity;
import edu.unlam.example.pokemonapi.dto.TrainerResponse;
import edu.unlam.example.pokemonapi.exceptions.TrainerDatabaseException;
import edu.unlam.example.pokemonapi.repository.TrainerRepository;
import edu.unlam.example.pokemonapi.service.TrainerService;
import edu.unlam.example.pokemonapi.utils.Converter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrainerServiceImpl implements TrainerService {

    private final TrainerRepository trainerRepository;

    @Override
    public TrainerResponse createTrainer(String name) {
        log.info("Creando entranador con el nombre: {}", name );
        var trainer = new TrainerEntity(name);

        try {
            trainerRepository.save(trainer);
        } catch (Exception e) {
            log.error("Error de base de datos: {}", e.getMessage());
            throw new TrainerDatabaseException(e.getMessage());
        }

        return Converter.convertToResponse(trainer);
    }
}
