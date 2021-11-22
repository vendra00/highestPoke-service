package com.pokemonpedia.highestPokeservice.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.pokemonpedia.highestPokeservice.dto.Pokemon;
import com.pokemonpedia.highestPokeservice.model.HighestPokemon;
import com.pokemonpedia.highestPokeservice.repository.HighestPokemonRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HighestPokemonServiceImpl implements HighestPokemonService {

	@Autowired
	private HighestPokemonRepository highestPokemonRepository;

	@Autowired
	private RestTemplate restTemplate;

	private static final String GET_ALL_POKEMONS_DB = "http://localhost:8090/pokemon/getAllPokemonsDb";

	@Override
	public HighestPokemon saveHighPokemon(HighestPokemon p) {
		log.info("Save Highest Pokemon Service Call");
		return highestPokemonRepository.save(p);
	}

	@Override
	public List<Pokemon> getAllHighestPokemons() {
		ResponseEntity<Pokemon[]> response = getPokemonsFromPokeService();
		List<Pokemon> list = Arrays.asList(response.getBody());

		findHighestFivePokemons(list);

		return list;
	}

	@Override
	public List<HighestPokemon> getFiveHighestPokemons() {
		if (highestPokemonRepository.findAll().isEmpty()) {
			getAllHighestPokemons();
		}
		return highestPokemonRepository.findAll().stream().collect(Collectors.toList());
	}

	private ResponseEntity<Pokemon[]> getPokemonsFromPokeService() throws RestClientException {
		log.info("Get All Highest Pokemons Service Call");
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
		ResponseEntity<Pokemon[]> response = restTemplate.exchange(GET_ALL_POKEMONS_DB, HttpMethod.GET, entity,
				Pokemon[].class);
		return response;
	}

	private void findHighestFivePokemons(List<Pokemon> list) {
		log.info("Find The 5 Highest Pokemons Service Call");
		sortPokemonsByHeight(list);
		getTheFiveHighestPokemonsAndSave(list);

	}

	private void getTheFiveHighestPokemonsAndSave(List<Pokemon> list) {
		log.info("Find The 5 Highest Pokemons And Save Service Call");
		if (highestPokemonRepository.findAll().isEmpty()) {
			saveHighPokemon(list);
		} 
	}

	private void saveHighPokemon(List<Pokemon> list) {
		log.info("Save A High Pokemon And Save On DB Service Call");
		for (int i = 0; i <= 4; i++) {
			HighestPokemon p = new HighestPokemon();
			p.setName(list.get(i).getName());
			p.setWeight(list.get(i).getWeight());
			p.setHeight(list.get(i).getHeight());
			p.setBase_experience(list.get(i).getBase_experience());
			saveHighPokemon(p);
		}
	}

	private void sortPokemonsByHeight(List<Pokemon> list) {
		log.info("Sort All Pokemons By Wheight Service Call");
		Collections.sort(list, new Comparator<Pokemon>() {
			@Override
			public int compare(Pokemon o1, Pokemon o2) {
				return Integer.valueOf(o2.getHeight()).compareTo(o1.getHeight());
			}
		});
	}

}
