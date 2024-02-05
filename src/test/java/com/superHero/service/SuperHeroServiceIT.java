package com.superHero.service;

import com.superHero.DTO.SuperHeroDTO;
import com.superHero.HeroApplication;
import com.superHero.Repository.SuperHeroRepository;
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
        SuperHeroDTO superHeroDTO = new SuperHeroDTO();
        superHeroDTO.setId("1");
        superHeroDTO.setName("Superman");
        superHeroService.saveSuperHero(superHeroDTO.getId(), superHeroDTO);
        Optional<SuperHeroDTO> savedSuperHeroOptional = repository.findById(superHeroDTO.getId());
        assertTrue(savedSuperHeroOptional.isPresent());
        SuperHeroDTO savedSuperHero = savedSuperHeroOptional.get();
        assertEquals("Superman", savedSuperHero.getName());
    }

    @Test
    public void testSaveSuperHero_nullParameters() {
        SuperHeroService superHeroService = new SuperHeroService(repository);
        SuperHeroDTO superHeroDTO = new SuperHeroDTO();
        superHeroDTO.setId("1");
        superHeroDTO.setName(null);
        assertThrows(IllegalArgumentException.class,()->superHeroService.saveSuperHero(superHeroDTO.getId(), superHeroDTO));
    }

    @Test
    public void testSaveSuperHero_updateTest(){
        SuperHeroService superHeroService = new SuperHeroService(repository);
        SuperHeroDTO superHeroDTO = new SuperHeroDTO();
        superHeroDTO.setId("1");
        superHeroDTO.setName("Superman");
        SuperHeroDTO superHeroDTOUpdate = new SuperHeroDTO();
        superHeroDTOUpdate.setId("1");
        superHeroDTOUpdate.setName("Spiderman");
        superHeroService.saveSuperHero(superHeroDTO.getId(), superHeroDTO);
        superHeroService.saveSuperHero(superHeroDTOUpdate.getId(), superHeroDTOUpdate);
        Optional<SuperHeroDTO> savedSuperHeroOptional = repository.findById(superHeroDTO.getId());
        assertTrue(savedSuperHeroOptional.isPresent());
        SuperHeroDTO savedSuperHero = savedSuperHeroOptional.get();
        assertEquals("1",savedSuperHero.getId());
        assertEquals("Spiderman", savedSuperHero.getName());
    }
}
