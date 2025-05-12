package com.pokemonsearcher.pokemon_searcher.controler;

import com.pokemonsearcher.pokemon_searcher.model.PokemonDTO;
import com.pokemonsearcher.pokemon_searcher.service.PokemonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {

    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    // Endpoint 1: Lista de 100 Pokémon
    @GetMapping("/list")
    public List<PokemonDTO> getList() {
        return pokemonService.getPokemonList(100);
    }

    // Endpoint 2: Buscar por nombre exacto
    @GetMapping("/{name}")
    public ResponseEntity<?> getPokemonByName(@PathVariable String name) {
        PokemonDTO pokemon = pokemonService.getPokemonByName(name.toLowerCase());
        if (pokemon == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Pokémon no encontrado: " + name);
        }
        return ResponseEntity.ok(pokemon);
    }

    // Endpoint 3: Buscar por coincidencia parcial
    @GetMapping("/search")
    public ResponseEntity<?> searchPokemon(@RequestParam String query,
                                           @RequestParam(defaultValue = "20") int maxResults) {
        List<PokemonDTO> result = pokemonService.searchPokemonByQuery(query, maxResults);
        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron Pokémon que coincidan con: " + query);
        }
        return ResponseEntity.ok(result);
    }
}

