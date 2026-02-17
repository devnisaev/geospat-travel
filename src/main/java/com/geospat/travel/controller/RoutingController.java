package com.geospat.travel.controller;

import com.geospat.travel.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/routing")
public class RoutingController {

    private final CountryService countryService;

    public RoutingController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/{origin}/{destination}")
    public ResponseEntity<Map<String, List<String>>> getRoute(@PathVariable String origin, @PathVariable String destination) {
        List<String> route = countryService.findRoute(origin, destination);
        return ResponseEntity.ok(Map.of("route", route));
    }
}
