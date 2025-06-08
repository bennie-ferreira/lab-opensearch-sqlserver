package com.opensearch.opensearch_spring.repository;


import org.springframework.stereotype.Repository;
import com.opensearch.opensearch_spring.model.Livro;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

@Repository
public interface LivroRepository extends ElasticsearchRepository<Livro, String> {
    List<Livro> findByTitulo(String titulo);
}