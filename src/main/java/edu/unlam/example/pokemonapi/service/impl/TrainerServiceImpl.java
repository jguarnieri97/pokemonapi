package edu.unlam.example.pokemonapi.service.impl;

import edu.unlam.example.pokemonapi.domain.TrainerEntity;
import edu.unlam.example.pokemonapi.dto.TrainerResponse;
import edu.unlam.example.pokemonapi.exceptions.TrainerDatabaseException;
import edu.unlam.example.pokemonapi.exceptions.TrainerNotFoundException;
import edu.unlam.example.pokemonapi.repository.TrainerRepository;
import edu.unlam.example.pokemonapi.service.TrainerService;
import edu.unlam.example.pokemonapi.utils.Converter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrainerServiceImpl implements TrainerService {

    private final TrainerRepository trainerRepository;

    @Override
    public TrainerResponse createTrainer(String name) {
        log.info("Creando entranador con el nombre: {}", name );
        var trainer = new TrainerEntity(name);

        this.saveTrainer(trainer);
        log.info("Se creó correctamente el entrenador: {}", trainer);

        return Converter.convertToResponse(trainer);
    }

    @Override
    public List<TrainerResponse> getAllTrainers() {
        log.info("Consultando todos los entrenadores");

        var trainers = trainerRepository.findAllActive();

        log.info("Cantidad de entrenadores encontrados: {}", trainers.size());

        return Converter.convertToResponseList(trainers);
    }

    @Override
    public TrainerResponse getTrainer(Long id) {
        var trainer = this.getTrainerById(id);
        return Converter.convertToResponse(trainer);
    }

    @Override
    public void deleteTrainer(Long id) {
        log.info("Eliminando entrenador con el id: {}", id);
        var trainer = this.getTrainerById(id);

        trainer.setActive(false);
        this.saveTrainer(trainer);

        log.info("Se eliminó correctamente el entrenador con el id: {}", id);
    }

    private TrainerEntity getTrainerById(Long id) {
        log.info("Consultando entrenador con el id: {}", id );

        Optional<TrainerEntity> trainer = trainerRepository.findById(id);

        if (trainer.isEmpty()) {
            log.error("Entrenador con id {} no encontrado", id);
            throw new TrainerNotFoundException();
        }
        var trainerFound = trainer.get();
        log.info("Se encontro el entrenador con el id: {}, {}", id, trainerFound);

        return trainerFound;
    }

    private void saveTrainer(TrainerEntity trainer) {
        try {
            trainerRepository.save(trainer);
        } catch (Exception e) {
            log.error("Error de base de datos: {}", e.getMessage());
            throw new TrainerDatabaseException(e.getMessage());
        }
    }


}
