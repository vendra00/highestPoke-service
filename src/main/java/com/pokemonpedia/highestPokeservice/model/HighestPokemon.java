package com.pokemonpedia.highestPokeservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class HighestPokemon {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long highestPokemonId;
	private String name;
	private int height;
	private int weight;
	private int base_experience;

}
