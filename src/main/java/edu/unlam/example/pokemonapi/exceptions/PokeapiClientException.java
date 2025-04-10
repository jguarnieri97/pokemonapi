package edu.unlam.example.pokemonapi.exceptions;

public class PokeapiClientException extends GenericException {

    private static final String MESSAGE = "POKEAPI_EXCEPTION";

    public PokeapiClientException(int code, String detail) {
        super(code, MESSAGE, detail);
    }
}
