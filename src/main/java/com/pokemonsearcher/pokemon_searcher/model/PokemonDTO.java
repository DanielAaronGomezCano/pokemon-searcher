package com.pokemonsearcher.pokemon_searcher.model;

import java.util.List;

public class PokemonDTO {
    private String name;
    private int height;
    private String imageUrl;
    private List<String> abilities;
    private List<String> forms;

    // Getters y Setters

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public List<String> getAbilities() { return abilities; }
    public void setAbilities(List<String> abilities) { this.abilities = abilities; }

    public List<String> getForms() { return forms; }
    public void setForms(List<String> forms) { this.forms = forms; }
}
