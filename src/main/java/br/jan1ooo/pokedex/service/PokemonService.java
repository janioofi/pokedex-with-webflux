package br.jan1ooo.pokedex.service;

import br.jan1ooo.pokedex.model.Pokemon;
import br.jan1ooo.pokedex.repository.PokemonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PokemonService {
    private final PokemonRepository repository;

    public PokemonService(PokemonRepository pokemonRepository){
        this.repository = pokemonRepository;
    }

    public Mono<Pokemon> salvarPokemon(Pokemon pokemon){
        return repository.save(pokemon);
    }

    public Flux<Pokemon> listarPokemons(){
        return repository.findAll();
    }

    public Mono<Pokemon> buscarPorId(String id){
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

}
