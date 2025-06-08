package com.opensearch.opensearch_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;

@SpringBootApplication(exclude = {ElasticsearchDataAutoConfiguration.class})
public class OpensearchSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpensearchSpringApplication.class, args);
	}

}
