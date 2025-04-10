package edu.unlam.example.pokemonapi.client;

import edu.unlam.example.pokemonapi.dto.PokemonResponse;

public interface PokeapiClient {

    PokemonResponse getPokemonByName(String name);

}
