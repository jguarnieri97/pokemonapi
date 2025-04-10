package edu.unlam.example.pokemonapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponseDto {

    private int code;
    private String message;
    private String detail;
}
