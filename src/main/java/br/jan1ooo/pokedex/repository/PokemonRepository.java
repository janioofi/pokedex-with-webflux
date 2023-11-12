package br.jan1ooo.pokedex.repository;

import br.jan1ooo.pokedex.model.Pokemon;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends ReactiveMongoRepository<Pokemon, String> {
}
