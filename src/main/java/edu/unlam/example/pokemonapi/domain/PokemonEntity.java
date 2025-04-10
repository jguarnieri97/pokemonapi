package edu.unlam.example.pokemonapi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "POKEMON")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class PokemonEntity {

    @Id
    @Column(name = "pokemon_id")
    private int id;

    private String name;

    @Column(name = "pokemon_order")
    private int order;

    @ManyToMany(mappedBy = "pokemons")
    private List<TrainerEntity> trainers;

    public void addTrainer(TrainerEntity trainer) {
        trainers.add(trainer);
    }

}
