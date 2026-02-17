package com.geospat.travel.service;

import com.geospat.travel.model.Country;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CountryService {

    private final Map<String, List<String>> countriesMap = new HashMap<>();

    @PostConstruct
    public void loadCountries() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        Country[] countries = mapper.readValue(
                new ClassPathResource("countries.json").getInputStream(),
                Country[].class
        );

        for (Country country : countries) {
            countriesMap.put(country.getCca3(),
                    country.getBorders() == null ? new ArrayList<>() : country.getBorders()
            );
        }
    }

    public List<String> findRoute(String origin, String destination) {
        validateInput(origin, destination);

        return breadthSearch(origin, destination).orElseThrow(() -> new IllegalArgumentException("No land route found"));
    }

    private void validateInput(String origin, String destination) {
        if (!countriesMap.containsKey(origin) || !countriesMap.containsKey(destination)) {
            throw new IllegalArgumentException("Invalid country code");
        }
    }

    private Optional<List<String>> breadthSearch(String origin, String destination) {
        Queue<String> queue = new LinkedList<>();
        Map<String, String> parentMap = new HashMap<>();
        Set<String> visited = new HashSet<>();

        queue.add(origin);
        visited.add(origin);

        while (!queue.isEmpty()) {
            String current = queue.poll();

            if (current.equals(destination)) {
                return Optional.of(buildPath(parentMap, destination));
            }

            for (String neighbor : getNeighbors(current)) {
                if (visited.add(neighbor)) {
                    parentMap.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        return Optional.empty();
    }

    private List<String> getNeighbors(String country) {
        return countriesMap.getOrDefault(country, Collections.emptyList());
    }

    private List<String> buildPath(Map<String, String> parentMap, String destination) {
        LinkedList<String> path = new LinkedList<>();
        String step = destination;

        while (step != null) {
            path.addFirst(step);
            step = parentMap.get(step);
        }

        return path;
    }
}

