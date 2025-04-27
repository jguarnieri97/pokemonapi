package edu.unlam.example.pokemonapi.persistence.impl;

import edu.unlam.example.pokemonapi.exceptions.PokemonDatabaseException;
import edu.unlam.example.pokemonapi.model.PokemonEntity;
import edu.unlam.example.pokemonapi.persistence.PokemonDAO;
import edu.unlam.example.pokemonapi.persistence.repository.PokemonRepository;
import edu.unlam.example.pokemonapi.utils.Converter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PokemonDAOImpl implements PokemonDAO {

    private final PokemonRepository pokemonRepository;

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
