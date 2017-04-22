package com.bakoproductions.pokemoncleanexample.domain.models;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Michael on 22/04/2017.
 */
public class LinkTest {
    @Test
    public void testEmptyLinkIsValid() {
        Link link = new Link();
        link.setLinkUrl("");
        assertFalse(link.isValid());
    }

    @Test
    public void testNullLinkIsValid() {
        Link link = new Link();
        link.setLinkUrl(null);
        assertFalse(link.isValid());
    }

    @Test
    public void testValidLink() {
        Link link = new Link();
        link.setLinkUrl("http://pokeapi.co/api/v2/pokemon/100/");

        assertTrue(link.isValid());
    }
}