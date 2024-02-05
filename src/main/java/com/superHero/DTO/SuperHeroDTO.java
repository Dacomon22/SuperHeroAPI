package com.superHero.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class SuperHeroDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String id;

    @NotEmpty
    @NotNull
    String name;
}
