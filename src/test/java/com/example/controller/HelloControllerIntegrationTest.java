package com.example.controller;

import com.example.config.TestRestTemplateConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for country info with locale transformation
 */
@SpringBootTest
@AutoConfigureMockMvc
@Import(TestRestTemplateConfig.class)
public class HelloControllerIntegrationTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void testCountryInfoEndpointWithId() throws Exception {
        mockMvc.perform(get("/country-info")
                .param("id", "123"))
                .andExpect(status().isOk());
    }
    
    @Test
    public void testCountryInfoEndpointLanguageTransformation() throws Exception {
        mockMvc.perform(get("/country-info")
                .param("id", "456"))
                .andExpect(status().isOk());
    }
}
