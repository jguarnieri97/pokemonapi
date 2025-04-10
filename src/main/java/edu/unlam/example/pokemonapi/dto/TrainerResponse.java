package edu.unlam.example.pokemonapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class TrainerResponse {

    private Long id;
    private String name;
    private List<PokemonResponse> pokemons;

}
