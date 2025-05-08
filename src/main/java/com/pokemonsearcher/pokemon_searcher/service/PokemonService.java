package com.pokemonsearcher.pokemon_searcher.service;

import com.pokemonsearcher.pokemon_searcher.model.PokemonDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.*;

@Service
public class PokemonService {

    private final WebClient webClient;
    private final Map<String, PokemonDTO> cache = new HashMap<>();

    public PokemonService(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<PokemonDTO> getPokemonList(int count) {
        List<PokemonDTO> pokemonList = new ArrayList<>();

        for (int i = 1; i <= count; i++) {
            System.out.println("→ Obteniendo Pokémon #" + i);
            String name = String.valueOf(i);

            // Verificar en caché por ID
            if (cache.containsKey(name)) {
                pokemonList.add(cache.get(name));
                continue;
            }

            Mono<Map> response = webClient.get().uri("/pokemon/" + i).retrieve().bodyToMono(Map.class);
            Map data = response.block();

            PokemonDTO p = buildPokemonFromData(data);
            if (p != null) {
                cache.put(name, p);
                pokemonList.add(p);
            }
        }

        return pokemonList;
    }

    public PokemonDTO getPokemonByName(String name) {
        System.out.println("→ Buscando Pokémon por nombre: " + name);

        if (cache.containsKey(name)) return cache.get(name);

        try {
            Mono<Map> response = webClient.get().uri("/pokemon/" + name).retrieve().bodyToMono(Map.class);
            Map data = response.block();

            PokemonDTO p = buildPokemonFromData(data);
            if (p != null) {
                cache.put(name, p);
            }

            return p;
        } catch (Exception e) {
            System.err.println(" Error buscando Pokémon: " + e.getMessage());
            return null;
        }
    }

    public List<PokemonDTO> searchPokemonByQuery(String query, int maxResults) {
        List<PokemonDTO> matched = new ArrayList<>();
        int found = 0;

        for (int i = 1; i <= 200 && found < maxResults; i++) {
            System.out.println("→ Buscando coincidencia en Pokémon #" + i);
            try {
                String name = String.valueOf(i);

                // Si ya está en caché
                PokemonDTO p = cache.getOrDefault(name, null);
                if (p == null) {
                    Mono<Map> response = webClient.get().uri("/pokemon/" + i).retrieve().bodyToMono(Map.class);
                    Map data = response.block();
                    p = buildPokemonFromData(data);
                    if (p != null) cache.put(p.getName(), p);
                }

                if (p != null && p.getName().contains(query.toLowerCase())) {
                    matched.add(p);
                    found++;
                }
            } catch (Exception ignored) {
            }
        }

        return matched;
    }


    private PokemonDTO buildPokemonFromData(Map data) {
        if (data == null) return null;

        PokemonDTO p = new PokemonDTO();
        p.setName((String) data.get("name"));
        p.setHeight((Integer) data.get("height"));

        Map sprites = (Map) data.get("sprites");
        if (sprites != null) {
            p.setImageUrl((String) sprites.get("front_default"));
        }

        List<Map> abilitiesRaw = (List<Map>) data.get("abilities");
        List<String> abilities = new ArrayList<>();
        for (Map a : abilitiesRaw) {
            Map ability = (Map) a.get("ability");
            abilities.add((String) ability.get("name"));
        }
        p.setAbilities(abilities);

        List<Map> formsRaw = (List<Map>) data.get("forms");
        List<String> forms = new ArrayList<>();
        for (Map f : formsRaw) {
            forms.add((String) f.get("name"));
        }
        p.setForms(forms);

        return p;
    }
}
