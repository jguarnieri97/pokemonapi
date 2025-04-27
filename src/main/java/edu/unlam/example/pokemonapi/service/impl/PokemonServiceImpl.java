package edu.unlam.example.pokemonapi.service.impl;

import edu.unlam.example.pokemonapi.client.PokeapiClient;
import edu.unlam.example.pokemonapi.dto.PokemonResponse;
import edu.unlam.example.pokemonapi.service.PokemonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PokemonServiceImpl implements PokemonService {

    private final PokeapiClient pokeapiClient;

    @Override
    public PokemonResponse getPokemon(String name) {
        log.info("Obteniendo pokemon con el nombre: {}", name);
        var response = pokeapiClient.getPokemonByName(name);
        log.info("Pokemon obtenido con éxito!: {}", name);
        return response;
    }


}
