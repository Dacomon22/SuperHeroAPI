package com.superhero.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.ArrayList;

import static org.mockito.Mockito.mock;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SuperHeroControllerIT {

    ObjectMapper mapper = new ObjectMapper();
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
        repository.save(hero);
        String expected = mapper.writeValueAsString(hero);
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/superheroes/2", String.class);
        assertEquals(expected, response.getBody());
    }

    @Test
    public void testGetAllSuperHero_NotFound() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/superheroes/", String.class);
        assertEquals(HttpStatusCode.valueOf(404), response.getStatusCode());
    }

    @Test
    public void testGetAllSuperHero_Correct() throws Exception {
        ArrayList<SuperHero> list = new ArrayList<>();
        SuperHero hero = new SuperHero();
        hero.setId("1");
        hero.setName("SuperMan");
        repository.save(hero);
        SuperHero hero2 = new SuperHero();
        hero2.setId("2");
        hero2.setName("Spiderman");
        repository.save(hero2);
        SuperHero hero3 = new SuperHero();
        hero3.setId("3");
        hero3.setName("IronMan");
        repository.save(hero3);
        list.add(hero);
        list.add(hero2);
        list.add(hero3);
        String expected = mapper.writeValueAsString(list);
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/superheroes/", String.class);
        assertEquals(expected, response.getBody());
    }
    @Test
    public void testGetSuperHeroByName_notFound() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/superheroes/byName?name=man", String.class);
        assertEquals(HttpStatusCode.valueOf(404), response.getStatusCode());
    }

    @Test
    public void testGetSuperHeroByName_Correct() throws Exception {
        ArrayList<SuperHero> list = new ArrayList<>();
        SuperHero hero = new SuperHero();
        hero.setId("1");
        hero.setName("SuperMan");
        repository.save(hero);
        SuperHero hero2 = new SuperHero();
        hero2.setId("2");
        hero2.setName("Spiderman");
        repository.save(hero2);
        SuperHero hero3 = new SuperHero();
        hero3.setId("3");
        hero3.setName("IronMan");
        repository.save(hero3);
        SuperHero hero4 = new SuperHero();
        hero4.setId("4");
        hero4.setName("SpiderWoman");
        repository.save(hero4);
        list.add(hero2);
        list.add(hero4);
        String expected = mapper.writeValueAsString(list);
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/superheroes/byName?name=spider", String.class);
        assertEquals(expected, response.getBody());
    }


}
