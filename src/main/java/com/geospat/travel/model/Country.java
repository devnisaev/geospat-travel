package com.geospat.travel.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {

    private String cca3;
    private List<String> borders;

    private Name name;  // maps the "name" object

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Name {
        private String common;  // "Aruba", "Afghanistan"
    }
}
