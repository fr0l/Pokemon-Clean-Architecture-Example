package com.bakoproductions.pokemoncleanexample.domain.models;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Michael on 22/04/2017.
 */
public class PokemonTest {
    @Test
    public void testGetId() {
        Pokemon pokemon = new Pokemon();
        Link link = new Link();
        link.setLinkUrl("http://pokeapi.co/api/v2/pokemon/100/");
        pokemon.setUrl(link);

        assertEquals("100", pokemon.getId());
    }

    @Test
    public void testGetName() {
        Pokemon pokemon = new Pokemon();
        pokemon.setName("rotom-wash");

        assertEquals("rotom-wash", pokemon.getName());
        assertEquals("Rotom Wash", pokemon.getNameFormatted());
    }
}