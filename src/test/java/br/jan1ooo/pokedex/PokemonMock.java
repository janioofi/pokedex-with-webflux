package br.jan1ooo.pokedex;

import br.jan1ooo.pokedex.model.Pokemon;

import java.util.Collections;

public class PokemonMock {

    public static Pokemon pokemonMock(){
        return new Pokemon("3f63543f-6d18-45ca-8457-b7bd32c18ad2",
                "Charizard",
                "Fire",
                Collections.emptyList());
    }
}
