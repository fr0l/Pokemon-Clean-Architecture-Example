/*
 * Copyright 2017 Michael Bakogiannis, Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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