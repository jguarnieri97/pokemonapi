package edu.unlam.example.pokemonapi.persistence.repository;

import edu.unlam.example.pokemonapi.model.PokemonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends JpaRepository<PokemonEntity, Integer> {

}
