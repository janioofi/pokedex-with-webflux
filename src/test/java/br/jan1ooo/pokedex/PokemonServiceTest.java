package br.jan1ooo.pokedex;

import br.jan1ooo.pokedex.model.Pokemon;
import br.jan1ooo.pokedex.repository.PokemonRepository;
import br.jan1ooo.pokedex.service.PokemonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
public class PokemonServiceTest {
    @InjectMocks
    private PokemonService pokemonService;

    @Mock
    private PokemonRepository pokemonRepository;

    private final Pokemon pokemon = PokemonMock.pokemonMock();

    @BeforeEach
    public void setUp(){
        BDDMockito.when(pokemonRepository.save(pokemon)).thenReturn(Mono.just(pokemon));
        BDDMockito.when(pokemonRepository.findAll()).thenReturn(Flux.just(pokemon));
        BDDMockito.when(pokemonRepository.findById(ArgumentMatchers.anyString())).thenReturn(Mono.just(pokemon));
        BDDMockito.when(pokemonRepository.delete(ArgumentMatchers.any(Pokemon.class))).thenReturn(Mono.empty());
    }

    @Test
    @DisplayName("Deve criar um novo pokemon")
    public void deveCriarUmPokemonComSucesso(){
        StepVerifier.create(pokemonService.salvarPokemon(pokemon))
                .expectFusion()
                .expectNext(pokemon)
                .verifyComplete();
    }

    @Test
    @DisplayName("Deve listar todos os pokemons cadastrados")
    public void deveListarPokemonsComSucesso(){
        StepVerifier.create(pokemonService.listarPokemons())
                .expectFusion()
                .expectNext(pokemon)
                .verifyComplete();
    }

    @Test
    @DisplayName("Deve buscar um pokemon pelo id com sucesso")
    public void deveBuscarUmPokemonPeloIdComSucesso() {
        StepVerifier.create(pokemonService.buscarPorId("3f63543f-6d18-45ca-8457-b7bd32c18ad2"))
                .expectSubscription()
                .expectNext(pokemon)
                .verifyComplete();

    }

    @Test
    @DisplayName("Deve retornar um erro 404 ao buscar um pokemon")
    public void deveRetornar404AoBuscarUmPokemon() {
        BDDMockito.when(pokemonRepository.findById(ArgumentMatchers.anyString())).thenReturn(Mono.empty());
        StepVerifier.create(pokemonService.buscarPorId("23123123"))
                .expectSubscription()
                .expectError(ResponseStatusException.class)
                .verify();
    }

    @Test
    @DisplayName("Deve excluir um pokemon com sucesso")
    public void deveExcluirUmPokemonComSucesso(){
        StepVerifier.create(pokemonService.excluirPokemon("3f63543f-6d18-45ca-8457-b7bd32c18ad2"))
                .expectSubscription()
                .verifyComplete();
    }
}
