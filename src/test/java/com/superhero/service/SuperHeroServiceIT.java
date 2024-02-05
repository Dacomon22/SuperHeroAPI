package com.superhero.service;

import com.superhero.dtos.SuperHeroDTO;
import com.superhero.models.SuperHero;
import com.superhero.HeroApplication;
import com.superhero.repository.SuperHeroRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;

@SpringBootTest(classes = {HeroApplication.class, SuperHeroRepository.class})
public class SuperHeroServiceIT {

    @Autowired
    private SuperHeroRepository repository;


    @Test
    public void testSaveSuperHero_ValidData() {
        SuperHeroService superHeroService = new SuperHeroService(repository);
        SuperHero superHero = new SuperHero();
        superHero.setId("1");
        superHero.setName("Superman");
        superHeroService.saveSuperHero(superHero.getId(), superHero);
        Optional<SuperHero> savedSuperHeroOptional = repository.findById(superHero.getId());
        assertTrue(savedSuperHeroOptional.isPresent());
        SuperHero savedSuperHero = savedSuperHeroOptional.get();
        assertEquals("Superman", savedSuperHero.getName());
    }

    @Test
    public void testGetSuperHeroById_NotFound() {
        SuperHeroService superHeroService = new SuperHeroService(repository);
        Optional<SuperHeroDTO> hero = superHeroService.getSuperHeroById("NoExists");
        assertTrue(hero.isEmpty());

    }

    @Test
    public void testSaveSuperHero_nullParameters() {
        SuperHeroService superHeroService = new SuperHeroService(repository);
        SuperHero superHero = new SuperHero();
        superHero.setId("1");
        superHero.setName(null);
        assertThrows(IllegalArgumentException.class,()->superHeroService.saveSuperHero(superHero.getId(), superHero));
    }

    @Test
    public void testSaveSuperHero_updateTest(){
        SuperHeroService superHeroService = new SuperHeroService(repository);
        SuperHero superHero = new SuperHero();
        superHero.setId("1");
        superHero.setName("Superman");
        SuperHero superHeroUpdate = new SuperHero();
        superHeroUpdate.setId("1");
        superHeroUpdate.setName("Spiderman");
        superHeroService.saveSuperHero(superHero.getId(), superHero);
        superHeroService.saveSuperHero(superHeroUpdate.getId(), superHeroUpdate);
        Optional<SuperHero> savedSuperHeroOptional = repository.findById(superHero.getId());
        assertTrue(savedSuperHeroOptional.isPresent());
        SuperHero savedSuperHero = savedSuperHeroOptional.get();
        assertEquals("1",savedSuperHero.getId());
        assertEquals("Spiderman", savedSuperHero.getName());
    }
}
