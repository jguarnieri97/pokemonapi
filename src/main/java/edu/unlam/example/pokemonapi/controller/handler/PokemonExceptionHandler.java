package edu.unlam.example.pokemonapi.controller.handler;

import edu.unlam.example.pokemonapi.dto.ErrorResponseDto;
import edu.unlam.example.pokemonapi.exceptions.PokeapiClientException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

}
