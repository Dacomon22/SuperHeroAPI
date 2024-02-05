package com.superHero.Controller;

import com.superHero.DTO.SuperHeroDTO;
import com.superHero.service.SuperHeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/superheroes")
public class SuperHeroController {

    @Autowired
    private SuperHeroService superHeroService;

    @GetMapping("/{id}")
    public ResponseEntity<SuperHeroDTO>getSuperHeroById(@PathVariable String id){
        SuperHeroDTO superHero = superHeroService.getSuperHeroById(id);
        if (superHero != null) {
            return new ResponseEntity<>(superHero, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List>getSuperHeroByName(@RequestParam String name){
        List<SuperHeroDTO> superHero = superHeroService.getSuperHeroesByName(name);
        if (superHero != null) {
            return new ResponseEntity<>(superHero, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List>getAllSuperHero(){
        List<SuperHeroDTO> superHero = superHeroService.getAllSuperHeroes();
        if (superHero != null) {
            return new ResponseEntity<>(superHero, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<List>CreateSuperHero(){
        List<SuperHeroDTO> superHero = superHeroService.getAllSuperHeroes();
        if (superHero != null) {
            return new ResponseEntity<>(superHero, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List>deleteSuperHero(){
        List<SuperHeroDTO> superHero = superHeroService.getAllSuperHeroes();
        if (superHero != null) {
            return new ResponseEntity<>(superHero, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
