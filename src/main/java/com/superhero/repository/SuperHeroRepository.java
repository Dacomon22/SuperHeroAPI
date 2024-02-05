package com.superhero.repository;

import com.superhero.models.SuperHero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuperHeroRepository extends JpaRepository<SuperHero, String>{
    List<SuperHero> findByNameContaining(String name);
}
