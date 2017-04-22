package com.bakoproductions.pokemoncleanexample.domain.models;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Michael on 22/04/2017.
 */
public class PokemonDetailsTest {
    @Test
    public void testNullId() {
        PokemonDetails details = new PokemonDetails();
        details.setId(null);

        assertEquals("", details.getId());
    }

    @Test
    public void testValidId() {
        PokemonDetails details = new PokemonDetails();
        details.setId("1");

        assertEquals("1", details.getId());
    }

    @Test
    public void testGetWeightInt() {
        PokemonDetails details = new PokemonDetails();
        details.setWeight(100);

        assertEquals("10", details.getWeightKG());
    }

    @Test
    public void testGetWeightFloat() {
        PokemonDetails details = new PokemonDetails();
        details.setWeight(121);

        assertEquals("12.1", details.getWeightKG());
    }

    @Test
    public void testNotEquals() {
        PokemonDetails d1 = new PokemonDetails("1");
        PokemonDetails d2 = new PokemonDetails("2");

        assertFalse(d1.equals(d2));
    }

    @Test
    public void testNotEqualsNull() {
        PokemonDetails d1 = new PokemonDetails("1");

        assertFalse(d1.equals(null));
    }

    @Test
    public void testNotEqualsOther() {
        PokemonDetails d1 = new PokemonDetails("1");

        assertFalse(d1.equals(new Pokemon()));
    }

    @Test
    public void testEquals() {
        PokemonDetails d1 = new PokemonDetails("1");
        PokemonDetails d2 = new PokemonDetails("1");

        assertTrue(d1.equals(d2));
    }
}