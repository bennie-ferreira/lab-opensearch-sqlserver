package com.opensearch.opensearch_spring.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(Healthcheck.class)
public class HealthcheckTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void healthCheck_ShouldReturnOK() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/healthcheck"))
                .andExpect(status().isOk())
                .andExpect(content().string("OK"));
    }
}
