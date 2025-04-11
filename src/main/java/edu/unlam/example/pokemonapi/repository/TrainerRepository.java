package edu.unlam.example.pokemonapi.repository;

import edu.unlam.example.pokemonapi.domain.TrainerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainerRepository extends JpaRepository<TrainerEntity, Long> {

    @Query("SELECT t FROM TrainerEntity t WHERE t.isActive = true")
    List<TrainerEntity> findAllActive();
}
