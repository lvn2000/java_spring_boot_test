package com.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO for external API response containing language and country parameters
 */
public class ExternalApiResponse {
    
    @JsonProperty("lang")
    private String lang;
    
    @JsonProperty("country")
    private String country;
    
    @JsonProperty("id")
    private String id;
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("description")
    private String description;

    public ExternalApiResponse() {
    }

    public ExternalApiResponse(String lang, String country) {
        this.lang = lang;
        this.country = country;
    }

    public ExternalApiResponse(String lang, String country, String id, String name) {
        this.lang = lang;
        this.country = country;
        this.id = id;
        this.name = name;
    }

    // Getters and Setters
    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ExternalApiResponse{" +
                "lang='" + lang + '\'' +
                ", country='" + country + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
