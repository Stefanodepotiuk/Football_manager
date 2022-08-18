package com.example.football_manager.models.entity;

import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "name shouldn't be null")
    private String name;
    @Min(0)
    @Max(10)
    private double commission;
    @Min(100000)
    private double balance;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id")
    @ToString.Exclude
    private List<Footballer> team_players = new ArrayList<>();

}
