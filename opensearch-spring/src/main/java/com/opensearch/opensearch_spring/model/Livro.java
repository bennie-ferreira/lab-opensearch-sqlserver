package com.opensearch.opensearch_spring.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


@Document(indexName = "pipeline-livro-textos")
public class Livro {

    @Id
    private String id;

    @Field(type = FieldType.Text, name = "titulo")
    private String titulo;

    @Field(type = FieldType.Text, name = "autor")
    private String autor;

    @Field(type = FieldType.Text, name = "conteudo")
    private String conteudo;

    @Field(type = FieldType.Text, name = "data_publicacao")
    private String dataPublicacao;

    public Livro(String id, String titulo, String autor, String conteudo, String dataPublicacao){
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.conteudo = conteudo;
        this.dataPublicacao = dataPublicacao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getTitulo(){
        return titulo;
    }

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(String dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }
}

