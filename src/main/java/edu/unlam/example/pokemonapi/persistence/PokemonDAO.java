package edu.unlam.example.pokemonapi.persistence;

import edu.unlam.example.pokemonapi.model.PokemonEntity;

public interface PokemonDAO {
    void savePokemon(PokemonEntity pokemon);
}
