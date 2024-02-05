package com.superHero.service;

import com.superHero.DTO.SuperHeroDTO;
import com.superHero.Repository.SuperHeroRepository;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

public class SuperHeroService {

    private final SuperHeroRepository repository;
    SuperHeroDTO dto;

    public SuperHeroService(SuperHeroRepository repository) {
        this.repository = repository;
    }
    public SuperHeroDTO getSuperHeroById(String id){
        if(id==null||id.isEmpty()){
            throw new IllegalArgumentException("El parametro es invalido");
        }
        dto = repository.getReferenceById(id);
        return dto;
    }

    public List<SuperHeroDTO> getSuperHeroesByName(String name){
        if(name==null||name.isEmpty()){
            throw new IllegalArgumentException("El parametro es invalido");
        }
        List<SuperHeroDTO> list = repository.findByNameContaining(name);
        return list;
    }

    public List<SuperHeroDTO> getAllSuperHeroes(){
        List<SuperHeroDTO> list =repository.findAll();
        return list;
    }

    public void saveSuperHero(String id,SuperHeroDTO hero){
    if(id ==null || id.isEmpty()){
        throw new IllegalArgumentException("el Id es invalido");
    }
        try {
            repository.save(hero);
        }catch (DataIntegrityViolationException e){
            throw new IllegalArgumentException("Los valores del SuperHero son invalidos");
        }
    }


    public void deleteSuperHero(String id){

        throw new IllegalArgumentException("El parametro es invalido");

    }


}
