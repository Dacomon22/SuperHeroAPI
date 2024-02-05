package com.superhero.controller;

import com.superhero.dtos.SuperHeroDTO;
import com.superhero.models.SuperHero;
import com.superhero.service.SuperHeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/superheroes")
public class SuperHeroController {

    @Autowired
    private SuperHeroService superHeroService;

    @GetMapping("/{id}")
    public ResponseEntity<SuperHeroDTO>getSuperHeroById(@PathVariable String id){
        try {
            Optional<SuperHeroDTO> superHero = superHeroService.getSuperHeroById(id);

            if (superHero.isPresent()) {
                return new ResponseEntity<>(superHero.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (RuntimeException ex){
            System.err.println("Error: " + ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }
    }

    @GetMapping("/")
    public ResponseEntity<List>getSuperHeroByName(@RequestParam String name){
        List<SuperHero> superHero = superHeroService.getSuperHeroesByName(name);
        if (superHero != null) {
            return new ResponseEntity<>(superHero, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/test")
    public ResponseEntity<List>getAllSuperHero(){
        List<SuperHero> superHero = superHeroService.getAllSuperHeroes();
        if (superHero != null) {
            return new ResponseEntity<>(superHero, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<List>CreateSuperHero(@PathVariable String id){
        List<SuperHero> superHero = superHeroService.getAllSuperHeroes();
        if (superHero != null) {
            return new ResponseEntity<>(superHero, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List>deleteSuperHero(){
        List<SuperHero> superHero = superHeroService.getAllSuperHeroes();
        if (superHero != null) {
            return new ResponseEntity<>(superHero, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
