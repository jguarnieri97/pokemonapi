package edu.unlam.example.pokemonapi.exceptions;

import org.springframework.http.HttpStatus;

public class TrainerNotFoundException extends GenericException {

    private static final String TRAINER_SERVICE_EXCEPTION = "TRAINER_SERVICE_EXCEPTION";
    private static final String TRAINER_DETAIL = "Entrenador no encontrado";

    public TrainerNotFoundException() {
        super(HttpStatus.NOT_FOUND.value(), TRAINER_SERVICE_EXCEPTION, TRAINER_DETAIL);
    }
}
