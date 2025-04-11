package edu.unlam.example.pokemonapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "POKEMON")
@NoArgsConstructor
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
    @JsonIgnore
    private List<TrainerEntity> trainers;

    public PokemonEntity(int id, String name, int order) {
        this.id = id;
        this.name = name;
        this.order = order;
        this.trainers = new ArrayList<>();
    }

    public void addTrainer(TrainerEntity trainer) {
        trainers.add(trainer);
    }

}
