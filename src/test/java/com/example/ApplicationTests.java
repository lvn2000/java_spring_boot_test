package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration tests for external API locale transformation
 */
@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testMockExternalApiEndpoint() throws Exception {
        mockMvc.perform(get("/api/external")
                .param("lang", "en")
                .param("country", "US"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lang").value("en"))
                .andExpect(jsonPath("$.country").value("US"));
    }
}

