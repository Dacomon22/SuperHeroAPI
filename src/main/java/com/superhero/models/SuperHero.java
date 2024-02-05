package com.superhero.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "SuperHero", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class SuperHero {

    @Id
    String id;

    @NotEmpty
    @NotNull
    String name;

}
