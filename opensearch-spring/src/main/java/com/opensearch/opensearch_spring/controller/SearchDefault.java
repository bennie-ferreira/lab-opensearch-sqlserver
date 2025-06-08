package com.opensearch.opensearch_spring.controller;

import com.opensearch.opensearch_spring.model.Livro;
import com.opensearch.opensearch_spring.repository.LivroRepository;
import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.opensearch._types.FieldValue;
import org.opensearch.client.opensearch.core.SearchRequest;
import org.opensearch.client.opensearch.core.SearchResponse;
import org.opensearch.client.opensearch.core.search.Hit;
import org.opensearch.client.opensearch.core.search.HitsMetadata;
import org.opensearch.client.opensearch._types.query_dsl.MatchQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class SearchDefault {

    private final OpenSearchClient client;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    public SearchDefault(OpenSearchClient client) {
        this.client = client;
    }

    @GetMapping("/search")
    public String searchDefault(@RequestParam String termo) {
        try {
            MatchQuery matchQuery = MatchQuery.of(m -> m.field("conteudo").query(FieldValue.of(termo)));

            SearchRequest searchRequest = new SearchRequest.Builder()
                    .index("pipeline-livro-textos")
                    .query(q -> q.match(matchQuery))
                    .build();

            SearchResponse<Object> searchResponse = client.search(searchRequest, Object.class);

            HitsMetadata<Object> hits = searchResponse.hits();
            List<Hit<Object>> hitList = hits.hits();

            StringBuilder result = new StringBuilder();
            for (Hit<Object> hit : hitList) {
                result.append(hit.source().toString()).append("\n");
            }
            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping("/livros")
    public List<Livro> procurarLivros(){
        return livroRepository.findByTitulo("Dom Casmurro");
    }

    @GetMapping("/todos-livros")
    public Iterable<Livro> listarLivros(){
        return livroRepository.findAll();
    }

    @QueryMapping
    public List<Livro> livrosPorTitulo(@Argument String titulo) {
        return livroRepository.findByTitulo(titulo);
    }

    @QueryMapping
    public Iterable<Livro> todosLivros() {
        return livroRepository.findAll();
    }


}
