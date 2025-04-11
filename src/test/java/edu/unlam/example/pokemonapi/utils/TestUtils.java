package edu.unlam.example.pokemonapi.utils;

import edu.unlam.example.pokemonapi.domain.PokemonEntity;
import edu.unlam.example.pokemonapi.domain.TrainerEntity;
import edu.unlam.example.pokemonapi.dto.PokemonResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestUtils {

    public static final int ID = 1;
    public static final String POKEMON_NAME = "Squirtle";
    public static final int POKEMON_ORDER = 1;

    public static PokemonResponse createPokemonResponse(String name) {
        PokemonResponse pokemonResponse = new PokemonResponse();
        pokemonResponse.setName(name);
        pokemonResponse.setOrder(POKEMON_ORDER);
        return pokemonResponse;
    }

    public static PokemonEntity createPokemonEntity() {
        return new PokemonEntity(ID, POKEMON_NAME, POKEMON_ORDER);
    }

    public static TrainerEntity createTrainerEntity(String name) {
        return new TrainerEntity(name);
    }

}
