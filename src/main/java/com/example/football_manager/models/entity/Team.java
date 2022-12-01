package com.example.football_manager.models.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @Max(value = 10,message = "This commission is too high, the maximum value is 10")
    private double commission;
    @Min(100000)
    private BigDecimal balance;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id")
    @ToString.Exclude
    private List<Footballer> footballersOfTeam = new ArrayList<>();
    public Team(Team team) {
        this.id = team.getId();
        this.name = team.getName();
        this.commission = team.getCommission();
        this.balance = team.getBalance();
        this.footballersOfTeam = team.getFootballersOfTeam()
                .stream()
                .map(Footballer::new)
                .collect(Collectors.toList());
    }
    public Team(int id, String name, double commission, BigDecimal balance) {
        this.id = id;
        this.name = name;
        this.commission = commission;
        this.balance = balance;
    }

}
