package edu.unlam.example.pokemonapi.persistence.impl;

import edu.unlam.example.pokemonapi.exceptions.TrainerDatabaseException;
import edu.unlam.example.pokemonapi.exceptions.TrainerNotFoundException;
import edu.unlam.example.pokemonapi.model.TrainerEntity;
import edu.unlam.example.pokemonapi.persistence.TrainerDAO;
import edu.unlam.example.pokemonapi.persistence.repository.TrainerRepository;
import edu.unlam.example.pokemonapi.utils.Converter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class TrainerDAOImpl implements TrainerDAO {

    private final TrainerRepository trainerRepository;

    @Override
    public TrainerEntity getTrainerById(Long id) {
        log.info("Consultando entrenador con el id: {}", id );

        Optional<TrainerEntity> trainer = trainerRepository.findById(id);

        if (trainer.isEmpty()) {
            log.error("Entrenador con id {} no encontrado", id);
            throw new TrainerNotFoundException();
        }
        var trainerFound = trainer.get();
        log.info("Se encontro el entrenador con el id: {}, {}", id, Converter.convertToJson(trainerFound));

        return trainerFound;
    }

    @Override
    public void saveTrainer(TrainerEntity trainer) {
        try {
            trainerRepository.save(trainer);
        } catch (Exception e) {
            log.error("Error de base de datos: {}", e.getMessage());
            throw new TrainerDatabaseException(e.getMessage());
        }
    }

    @Override
    public List<TrainerEntity> findAll() {
        try {
            return trainerRepository.findAllActive();
        } catch (Exception e) {
            log.error("Error de base de datos: {}", e.getMessage());
            throw new TrainerDatabaseException(e.getMessage());
        }
    }
}
