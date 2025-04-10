package edu.unlam.example.pokemonapi.exceptions;

import org.springframework.http.HttpStatus;

public class TrainerDatabaseException extends GenericException {

    private static final String TRAINER_SERVICE_EXCEPTION = "TRAINER_SERVICE_EXCEPTION";

    public TrainerDatabaseException(String detail) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), TRAINER_SERVICE_EXCEPTION, detail);
    }
}
