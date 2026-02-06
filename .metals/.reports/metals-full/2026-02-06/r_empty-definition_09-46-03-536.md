error id: file://<WORKSPACE>/src/test/java/com/example/ApplicationTests.java:org/springframework/beans/factory/annotation/Autowired#
file://<WORKSPACE>/src/test/java/com/example/ApplicationTests.java
empty definition using pc, found symbol in pc: org/springframework/beans/factory/annotation/Autowired#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 109
uri: file://<WORKSPACE>/src/test/java/com/example/ApplicationTests.java
text:
```scala
package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.@@Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration tests for the Spring Boot application
 */
@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testHelloEndpoint() throws Exception {
        mockMvc.perform(get("/api/hello"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello from Spring Boot!"))
                .andExpect(jsonPath("$.status").value("success"));
    }

    @Test
    void testGreetEndpoint() throws Exception {
        mockMvc.perform(get("/api/hello/Alice"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello, Alice!"))
                .andExpect(jsonPath("$.status").value("success"));
    }

    @Test
    void testHealthEndpoint() throws Exception {
        mockMvc.perform(get("/api/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"));
    }

    @Test
    void testEchoEndpoint() throws Exception {
        mockMvc.perform(post("/api/echo")
                .contentType("application/json")
                .content("{\"test\": \"data\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.echo.test").value("data"));
    }

}

        mockMvc.perform(get("/api/hello/Alice"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello, Alice!"))
                .andExpect(jsonPath("$.status").value("success"));
    }

    @Test
    void testHealthEndpoint() throws Exception {
        mockMvc.perform(get("/api/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"));
    }

    @Test
    void testEchoEndpoint() throws Exception {
        mockMvc.perform(post("/api/echo")
                .contentType("application/json")
                .content("{\"test\": \"data\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.echo.test").value("data"));
    }

}

```


#### Short summary: 

empty definition using pc, found symbol in pc: org/springframework/beans/factory/annotation/Autowired#