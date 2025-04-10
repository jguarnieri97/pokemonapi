package edu.unlam.example.pokemonapi.service;

import edu.unlam.example.pokemonapi.dto.PokemonResponse;

public interface PokemonService {

    public PokemonResponse getPokemon(String name);

}
