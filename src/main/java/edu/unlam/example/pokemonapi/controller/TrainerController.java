package edu.unlam.example.pokemonapi.controller;

import edu.unlam.example.pokemonapi.dto.TrainerRequest;
import edu.unlam.example.pokemonapi.dto.TrainerResponse;
import edu.unlam.example.pokemonapi.service.TrainerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/trainer")
@RequiredArgsConstructor
public class TrainerController {

    private final TrainerService trainerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrainerResponse createTrainer(@Valid @RequestBody TrainerRequest request) {
        return trainerService.createTrainer(request.getName());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TrainerResponse> getAllTrainers() {
        return trainerService.getAllTrainers();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public TrainerResponse getTrainer(@PathVariable Long id) {
        return trainerService.getTrainer(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrainer(@PathVariable Long id) {
        trainerService.deleteTrainer(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public TrainerResponse addPokemon(@PathVariable Long id,@Valid @RequestBody TrainerRequest request) {
        return trainerService.addPokemon(id, request);
    }
}
