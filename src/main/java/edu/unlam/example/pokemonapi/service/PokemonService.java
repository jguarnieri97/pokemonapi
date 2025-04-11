package edu.unlam.example.pokemonapi.service;

import edu.unlam.example.pokemonapi.domain.PokemonEntity;
import edu.unlam.example.pokemonapi.dto.PokemonResponse;

public interface PokemonService {

    PokemonResponse getPokemon(String name);
    void savePokemon(PokemonEntity pokemon);

}
