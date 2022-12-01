package com.example.football_manager.models.dto;

import com.example.football_manager.models.entity.Team;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class TeamDTO {
    private int id;
    private String name;
    private double commission;
    private BigDecimal balance;
    private List<FootballerDTO> footballersOfTeam = new ArrayList<>();
    public TeamDTO(Team team) {
        this.id = team.getId();
        this.name = team.getName();
        this.commission = team.getCommission();
        this.balance = team.getBalance();
        this.footballersOfTeam = team.getFootballersOfTeam()
                .stream()
                .map(FootballerDTO::new)
                .collect(Collectors.toList());
    }
    public TeamDTO(int id, String name, double commission, BigDecimal balance) {
        this.id = id;
        this.name = name;
        this.commission = commission;
        this.balance = balance;
    }
}
