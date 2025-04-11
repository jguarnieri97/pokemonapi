package edu.unlam.example.pokemonapi.service.impl;

import edu.unlam.example.pokemonapi.client.PokeapiClient;
import edu.unlam.example.pokemonapi.domain.PokemonEntity;
import edu.unlam.example.pokemonapi.dto.PokemonResponse;
import edu.unlam.example.pokemonapi.exceptions.PokemonDatabaseException;
import edu.unlam.example.pokemonapi.repository.PokemonRepository;
import edu.unlam.example.pokemonapi.service.PokemonService;
import edu.unlam.example.pokemonapi.utils.Converter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PokemonServiceImpl implements PokemonService {

    private final PokeapiClient pokeapiClient;
    private final PokemonRepository pokemonRepository;

    @Override
    public PokemonResponse getPokemon(String name) {
        log.info("Obteniendo pokemon con el nombre: {}", name);
        var response = pokeapiClient.getPokemonByName(name);
        log.info("Pokemon obtenido con éxito!: {}", name);
        return response;
    }

    @Override
    public void savePokemon(PokemonEntity pokemon) {
        log.info("Guardando pokemon: {}", Converter.convertToJson(pokemon));

        try {
            pokemonRepository.save(pokemon);
        } catch (Exception e) {
            throw new PokemonDatabaseException(e.getMessage());
        }

        log.info("Pokemon guardado con éxito!");
    }
}
