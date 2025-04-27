package edu.unlam.example.pokemonapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TRAINER")
@NoArgsConstructor
@Getter
public class TrainerEntity {

    @Id
    @Column(name = "trainer_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "trainer_pokemon",
            joinColumns = @JoinColumn(name = "trainer_id"),
            inverseJoinColumns = @JoinColumn(name = "pokemon_id")
    )
    private Set<PokemonEntity> pokemons;

    @Setter
    @Column(name = "is_active")
    private boolean isActive;

    public TrainerEntity(String name) {
        this.name = name;
        this.isActive = true;
        this.pokemons = new HashSet<>();
    }

    public void addPokemon(PokemonEntity pokemon) {
        pokemons.add(pokemon);
    }
}
