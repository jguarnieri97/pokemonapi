package edu.unlam.example.pokemonapi.controller.handler;

import edu.unlam.example.pokemonapi.dto.ErrorResponseDto;
import edu.unlam.example.pokemonapi.exceptions.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Clase que catchea todas las excepciones que llegan al controlador para manejar la respuesta.
 *
 */
@ControllerAdvice
public class PokemonExceptionHandler {

    @ExceptionHandler(PokeapiClientException.class)
    public ResponseEntity<ErrorResponseDto> handlePokeApiClientException(PokeapiClientException ex) {
        return ResponseEntity
                .status(ex.getCode())
                .body(ErrorResponseDto.builder()
                        .code(ex.getCode())
                        .message(ex.getMessage())
                        .detail(ex.getDetail())
                        .build());
    }

    @ExceptionHandler(TrainerNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handlePokeApiClientException(TrainerNotFoundException ex) {
        return ResponseEntity
                .status(ex.getCode())
                .body(ErrorResponseDto.builder()
                        .code(ex.getCode())
                        .message(ex.getMessage())
                        .detail(ex.getDetail())
                        .build());
    }

    @ExceptionHandler(ConverterException.class)
    public ResponseEntity<ErrorResponseDto> converterException(ConverterException ex) {
        return ResponseEntity
                .status(ex.getCode())
                .body(ErrorResponseDto.builder()
                        .code(ex.getCode())
                        .message(ex.getMessage())
                        .detail(ex.getDetail())
                        .build());
    }

    @ExceptionHandler(PokemonDatabaseException.class)
    public ResponseEntity<ErrorResponseDto> pokemonDatabaseException(PokemonDatabaseException ex) {
        return ResponseEntity
                .status(ex.getCode())
                .body(ErrorResponseDto.builder()
                        .code(ex.getCode())
                        .message(ex.getMessage())
                        .detail(ex.getDetail())
                        .build());
    }

    @ExceptionHandler(TrainerDatabaseException.class)
    public ResponseEntity<ErrorResponseDto> pokemonDatabaseException(TrainerDatabaseException ex) {
        return ResponseEntity
                .status(ex.getCode())
                .body(ErrorResponseDto.builder()
                        .code(ex.getCode())
                        .message(ex.getMessage())
                        .detail(ex.getDetail())
                        .build());
    }

}
