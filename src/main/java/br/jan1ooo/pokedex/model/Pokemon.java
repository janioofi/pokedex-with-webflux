package br.jan1ooo.pokedex.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "pokemon")
public record Pokemon(
        @Id String pokemoId,
        String nome,
        String tipo,
        List<String> evolucoes){
}
