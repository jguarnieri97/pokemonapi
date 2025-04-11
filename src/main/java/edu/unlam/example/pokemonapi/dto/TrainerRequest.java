package edu.unlam.example.pokemonapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrainerRequest {

    @NotBlank
    @NotNull
    private String name;
}
