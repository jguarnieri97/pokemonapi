package edu.unlam.example.pokemonapi.dto;

import lombok.Data;

@Data
public class PokemonResponse {

    private Long id;
    private String name;
    private Integer order;


}
