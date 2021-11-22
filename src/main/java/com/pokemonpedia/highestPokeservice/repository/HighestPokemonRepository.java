package com.pokemonpedia.highestPokeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pokemonpedia.highestPokeservice.model.HighestPokemon;

@Repository
public interface HighestPokemonRepository extends JpaRepository<HighestPokemon, Long>{

}
