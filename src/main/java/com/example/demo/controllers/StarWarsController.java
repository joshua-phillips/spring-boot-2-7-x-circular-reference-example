package com.example.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@CrossOrigin
@RestController
@RequestMapping("starwars")
public class StarWarsController {
    private final RestTemplate restTemplate;

    public StarWarsController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }   

    @GetMapping(value = "/planets/{id}",
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getPlanets(@PathVariable int id) {
        var response = restTemplate.getForEntity("https://swapi.dev/api/planets/" + id, String.class);    
        
        return new ResponseEntity<String>(response.getBody(), HttpStatus.OK);
    }
}
