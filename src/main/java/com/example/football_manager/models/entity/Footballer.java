package com.example.football_manager.models.entity;

import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Footballer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "name shouldn't be null")
    private String name;
    @Min(16)
    @Max(40)
    private int age;
    @NonNull
    private double experience;

    @ManyToOne
    private Team team;
}
