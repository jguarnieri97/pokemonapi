package edu.unlam.example.pokemonapi.controller;

import edu.unlam.example.pokemonapi.dto.PokemonResponse;
import edu.unlam.example.pokemonapi.service.PokemonService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/pokemon")
@RequiredArgsConstructor
public class PokemonController {

    private final PokemonService pokemonService;

    @GetMapping("{name}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Obtener pokemon por nombre")
    public PokemonResponse getPokemon(@PathVariable("name") String name) {
        return pokemonService.getPokemon(name);
    }

}
