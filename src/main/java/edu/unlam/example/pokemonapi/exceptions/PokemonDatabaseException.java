package edu.unlam.example.pokemonapi.exceptions;

import org.springframework.http.HttpStatus;

public class PokemonDatabaseException extends GenericException {

    private static final String POKEMON_SERVICE_EXCEPTION = "POKEMON_SERVICE_EXCEPTION";

    public PokemonDatabaseException(String detail) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), POKEMON_SERVICE_EXCEPTION, detail);
    }
}
