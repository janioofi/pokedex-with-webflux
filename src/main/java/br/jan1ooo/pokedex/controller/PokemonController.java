package br.jan1ooo.pokedex.controller;

import br.jan1ooo.pokedex.model.Pokemon;
import br.jan1ooo.pokedex.service.PokemonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/pokedex")
public class PokemonController {
    private final PokemonService service;

    public PokemonController(PokemonService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Pokemon> criarPokemon(@RequestBody Pokemon pokemon){
        return service.salvarPokemon(pokemon);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Pokemon> listarPokemons(){
        return service.listarPokemons();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Pokemon> buscarPokemon(@PathVariable String id){
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> excluirPokemon(@PathVariable String id){
        return service.excluirPokemon(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Pokemon>> atualizarPokemon(@PathVariable String id, @RequestBody Pokemon pokemon){
        return service.atualizarPokemon(pokemon, id);
    }
}
