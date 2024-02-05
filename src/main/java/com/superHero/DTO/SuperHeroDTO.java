package com.superHero.DTO;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class SuperHeroDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String id;
    String name;
}
