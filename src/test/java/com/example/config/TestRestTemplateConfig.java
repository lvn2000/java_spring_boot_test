package com.example.config;

import com.example.model.ExternalApiResponse;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;

/**
 * Test configuration that mocks the RestTemplate for external API calls
 */
@TestConfiguration
public class TestRestTemplateConfig {
    
    @Bean
    @Primary
    public RestTemplate mockRestTemplate() {
        RestTemplate restTemplate = mock(RestTemplate.class);
        
        // Mock response for any ID parameter
        ExternalApiResponse mockResponse = new ExternalApiResponse();
        mockResponse.setId("mock-id");
        mockResponse.setName("Mock Country");
        mockResponse.setLang("en");
        mockResponse.setCountry("US");
        
        when(restTemplate.getForObject(anyString(), eq(ExternalApiResponse.class)))
            .thenReturn(mockResponse);
        
        return restTemplate;
    }
}
