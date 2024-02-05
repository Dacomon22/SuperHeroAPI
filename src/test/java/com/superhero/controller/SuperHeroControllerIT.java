package com.superhero.controller;

import com.superhero.dtos.SuperHeroDTO;
import com.superhero.models.SuperHero;
import com.superhero.repository.SuperHeroRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.mock;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SuperHeroControllerIT {

    @Autowired
    private SuperHeroRepository repository;
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void testGetSuperHeroById_NotFound() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/superheroes/1", String.class);
        assertEquals(HttpStatusCode.valueOf(404), response.getStatusCode());
    }

    @Test
    public void testGetSuperHeroById_Correct() throws Exception {
        SuperHero hero = new SuperHero();
        hero.setId("2");
        hero.setName("SuperMan");
        SuperHeroDTO expected = new SuperHeroDTO("2","SuperMan");
        repository.save(hero);
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/superheroes/2", String.class);
        assertEquals(expected, response.getBody());
    }


}
