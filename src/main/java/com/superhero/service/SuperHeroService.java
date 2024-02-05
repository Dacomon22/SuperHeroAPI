package com.superhero.service;

import com.superhero.dtos.SuperHeroDTO;
import com.superhero.models.SuperHero;
import com.superhero.repository.SuperHeroRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class SuperHeroService {

    private final SuperHeroRepository repository;

    public SuperHeroService(SuperHeroRepository repository) {
        this.repository = repository;
    }

    public Optional<SuperHeroDTO> getSuperHeroById(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("El parametro es invalido");
        }
        Optional<SuperHero> entity = repository.findById(id);
        if (entity.isPresent()) {
            return Optional.of(new SuperHeroDTO(entity.get().getId(), entity.get().getName()));
        } else {
            return Optional.empty();
        }
    }
    public List<SuperHero> getSuperHeroesByName(String name){
        if(name==null||name.isEmpty()){
            throw new IllegalArgumentException("El parametro es invalido");
        }
        List<SuperHero> list = repository.findByNameContainingIgnoreCase(name.toLowerCase());
        return list;
    }

    public List<SuperHero> getAllSuperHeroes(){
        List<SuperHero> list =repository.findAll();
        return list;
    }

    public void saveSuperHero(String id, SuperHero hero){
    if(id ==null || id.isEmpty()|| id!=hero.getId()){
        throw new IllegalArgumentException("el Id es invalido");
    }
        try {
            repository.save(hero);
        }catch (DataIntegrityViolationException e){
            throw new IllegalArgumentException("Los valores del SuperHero son invalidos");
        }
    }


    public void deleteSuperHero(String id){
        if(id ==null || id.isEmpty()){
            throw new IllegalArgumentException("el Id es invalido");
        }
        repository.deleteById(id);
    }


}
