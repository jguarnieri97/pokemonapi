package edu.unlam.example.pokemonapi.repository;

import edu.unlam.example.pokemonapi.domain.TrainerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerRepository extends JpaRepository<TrainerEntity, Long> {
}
