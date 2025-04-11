package edu.unlam.example.pokemonapi.repository;

import edu.unlam.example.pokemonapi.domain.PokemonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends JpaRepository<PokemonEntity, Integer> {

}
