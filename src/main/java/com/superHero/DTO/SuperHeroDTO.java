package com.superHero.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class SuperHeroDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String id;
    String name;
}
