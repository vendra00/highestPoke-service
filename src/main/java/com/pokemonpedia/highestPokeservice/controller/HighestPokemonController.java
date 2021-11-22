package com.pokemonpedia.highestPokeservice.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokemonpedia.highestPokeservice.dto.Pokemon;
import com.pokemonpedia.highestPokeservice.exception.PokemonException;
import com.pokemonpedia.highestPokeservice.model.HighestPokemon;
import com.pokemonpedia.highestPokeservice.service.HighestPokemonService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/highPokemon")
@Slf4j
public class HighestPokemonController {
	
	@Autowired
	private HighestPokemonService highestPokemonService;
	
	@PostMapping("saveHighPokemon/")
	private HighestPokemon saveHighPokemon(@RequestBody HighestPokemon p) throws PokemonException {
		log.info("Save High Pokemon Controller Call");
		try {
			return highestPokemonService.saveHighPokemon(p);
		} catch (Exception e) {
			throw new PokemonException("There was a problem when saving this Pokemon");
		}
	}
	
	@GetMapping("/getAllHighestPokemons")
	public List<Pokemon> getAllHighestPokemons() throws PokemonException {
		log.info("Get All Highest Pokemons Controller Call");
		try {
			return highestPokemonService.getAllHighestPokemons();
		} catch (Exception e) {
			throw new PokemonException("There was a problem at loading the Pokemons list from DB");
		}
	}
	
	@GetMapping("/getFiveHighestPokemons")
	public ResponseEntity<Collection<HighestPokemon>> getFiveHeaviestPokemons() throws PokemonException {
		log.info("Get The 5 Highest Pokemons Controller Call");
		try {
			return ResponseEntity.ok(highestPokemonService.getFiveHighestPokemons());
		} catch (Exception e) {
			throw new PokemonException("There was a problem at loading the 5 Pokemons from DB");
		}	
	}
}
