package com.pokemonpedia.highestPokeservice.service;

import java.util.List;

import com.pokemonpedia.highestPokeservice.dto.Pokemon;
import com.pokemonpedia.highestPokeservice.model.HighestPokemon;

public interface HighestPokemonService {

	HighestPokemon saveHighPokemon(HighestPokemon p);
	
	List<Pokemon> getAllHighestPokemons();
	
	List<HighestPokemon> getFiveHighestPokemons();
}
