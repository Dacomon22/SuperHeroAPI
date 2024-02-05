package com.superHero.service;

import com.superHero.DTO.SuperHeroDTO;
import com.superHero.Repository.SuperHeroRepository;

import java.util.List;

public class SuperHeroService {

    private final SuperHeroRepository repository;
    SuperHeroDTO dto;

    public SuperHeroService(SuperHeroRepository repository) {
        this.repository = repository;
    }
    public SuperHeroDTO getSuperHeroById(String id){

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

    public void createOrUpdateSuperHero(String id,SuperHeroDTO hero){

        repository.save(hero);
    }


    public void deleteSuperHero(SuperHeroDTO hero){
        repository.delete(hero);
    }


}
