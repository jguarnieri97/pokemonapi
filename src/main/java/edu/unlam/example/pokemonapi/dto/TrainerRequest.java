package edu.unlam.example.pokemonapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TrainerRequest {

    @NotBlank
    @NotNull
    private String name;
}
