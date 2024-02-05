package com.superHero.Repository;

import com.superHero.DTO.SuperHeroDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface SuperHeroRepository extends JpaRepository<SuperHeroDTO, String>{
    List<SuperHeroDTO> findByNameContaining(String name);
}
