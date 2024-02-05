package com.superhero.service;

import com.superhero.dtos.SuperHeroDTO;
import com.superhero.models.SuperHero;
import com.superhero.HeroApplication;
import com.superhero.repository.SuperHeroRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
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

    @Test
    public void testGetSuperHeroByName(){
        SuperHeroService superHeroService = new SuperHeroService(repository);
        SuperHero superHero = new SuperHero();
        superHero.setId("1");
        superHero.setName("Superman");
        SuperHero superHero2 = new SuperHero();
        superHero2.setId("2");
        superHero2.setName("Spiderman");
        SuperHero superHero3 = new SuperHero();
        superHero3.setId("3");
        superHero3.setName("IronMan");
        SuperHero superHero4 = new SuperHero();
        superHero4.setId("4");
        superHero4.setName("WonderWomen");
        superHeroService.saveSuperHero(superHero.getId(), superHero);
        superHeroService.saveSuperHero(superHero2.getId(), superHero2);
        superHeroService.saveSuperHero(superHero3.getId(), superHero3);
        superHeroService.saveSuperHero(superHero4.getId(), superHero4);
        List<SuperHero> savedSuperHeroOptional = repository.findByNameContainingIgnoreCase("man");
        assertTrue(!savedSuperHeroOptional.isEmpty());
        assertEquals(3,savedSuperHeroOptional.size());
        assertEquals("1",savedSuperHeroOptional.get(0).getId());
        assertEquals("Spiderman", savedSuperHeroOptional.get(1).getName());
    }
}
