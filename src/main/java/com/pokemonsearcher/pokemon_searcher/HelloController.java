package com.pokemonsearcher.pokemon_searcher;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "¡Spring Boot está funcionando!";
    }
}
