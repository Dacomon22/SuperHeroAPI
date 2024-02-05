package com.superHero.Repository;

import com.superHero.DTO.SuperHeroDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuperHeroRepository extends JpaRepository<SuperHeroDTO, String>{
    List<SuperHeroDTO> findByNameContaining(String name);
}
