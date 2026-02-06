package com.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

/**
 * REST Controller for handling HTTP requests
 */
@RestController
@RequestMapping("/api")
public class HelloController {

    /**
     * Simple GET endpoint
     */
    @GetMapping("/hello")
    public ResponseEntity<Map<String, String>> hello() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello from Spring Boot!");
        response.put("status", "success");
        return ResponseEntity.ok(response);
    }

    /**
     * GET endpoint with path variable
     */
    @GetMapping("/hello/{name}")
    public ResponseEntity<Map<String, String>> greet(@PathVariable String name) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello, " + name + "!");
        response.put("status", "success");
        return ResponseEntity.ok(response);
    }

    /**
     * GET endpoint for health check
     */
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("application", "Spring Boot App");
        return ResponseEntity.ok(response);
    }

    /**
     * POST endpoint for echo
     */
    @PostMapping("/echo")
    public ResponseEntity<Map<String, Object>> echo(@RequestBody Map<String, Object> payload) {
        Map<String, Object> response = new HashMap<>();
        response.put("echo", payload);
        response.put("timestamp", System.currentTimeMillis());
        return ResponseEntity.ok(response);
    }

}
