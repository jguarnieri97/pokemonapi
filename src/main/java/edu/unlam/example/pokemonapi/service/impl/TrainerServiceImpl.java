package edu.unlam.example.pokemonapi.service.impl;

import edu.unlam.example.pokemonapi.model.PokemonEntity;
import edu.unlam.example.pokemonapi.model.TrainerEntity;
import edu.unlam.example.pokemonapi.dto.TrainerRequest;
import edu.unlam.example.pokemonapi.dto.TrainerResponse;
import edu.unlam.example.pokemonapi.persistence.PokemonDAO;
import edu.unlam.example.pokemonapi.persistence.TrainerDAO;
import edu.unlam.example.pokemonapi.service.PokemonService;
import edu.unlam.example.pokemonapi.service.TrainerService;
import edu.unlam.example.pokemonapi.utils.Converter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrainerServiceImpl implements TrainerService {

    private final TrainerDAO trainerRepository;
    private final PokemonDAO pokemonRepository;
    private final PokemonService pokemonService;

    @Override
    public TrainerResponse createTrainer(String name) {
        log.info("Creando entranador con el nombre: {}", name );
        var trainer = new TrainerEntity(name);

        trainerRepository.saveTrainer(trainer);
        log.info("Se creó correctamente el entrenador: {}", Converter.convertToJson(trainer));

        return Converter.convertToResponse(trainer);
    }

    @Override
    public List<TrainerResponse> getAllTrainers() {
        log.info("Consultando todos los entrenadores");

        var trainers = trainerRepository.findAll();

        log.info("Cantidad de entrenadores encontrados: {}", trainers.size());

        return Converter.convertToResponseList(trainers);
    }

    @Override
    public TrainerResponse getTrainer(Long id) {
        var trainer = trainerRepository.getTrainerById(id);
        return Converter.convertToResponse(trainer);
    }

    @Override
    public void deleteTrainer(Long id) {
        log.info("Eliminando entrenador con el id: {}", id);
        var trainer = trainerRepository.getTrainerById(id);

        trainer.setActive(false);
        trainerRepository.saveTrainer(trainer);

        log.info("Se eliminó correctamente el entrenador con el id: {}", id);
    }

    @Override
    public TrainerResponse addPokemon(Long id, TrainerRequest trainerRequest) {
        log.info("Agregando pokemon {} al entrenador {}", trainerRequest.getName(), id);

        //Obtenemos el pokemon y el entrenador
        var response = pokemonService.getPokemon(trainerRequest.getName());
        var trainer = trainerRepository.getTrainerById(id);

        //Creamos el pokemon obtenido y lo guardamos en la base de datos
        var pokemon = new PokemonEntity(response.getId(), response.getName(), response.getOrder());
        pokemonRepository.savePokemon(pokemon);

        //Relacionamos las entidades
        pokemon.addTrainer(trainer);
        trainer.addPokemon(pokemon);

        //Guardamos las relaciones
        trainerRepository.saveTrainer(trainer);
        log.info("Se agregó correctamente el pokemon {} al entrenador {}", trainerRequest.getName(), id);

        return Converter.convertToResponse(trainer);
    }





}
