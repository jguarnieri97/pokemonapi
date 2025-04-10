package edu.unlam.example.pokemonapi.controller;

import edu.unlam.example.pokemonapi.dto.TrainerRequest;
import edu.unlam.example.pokemonapi.dto.TrainerResponse;
import edu.unlam.example.pokemonapi.service.TrainerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

}
