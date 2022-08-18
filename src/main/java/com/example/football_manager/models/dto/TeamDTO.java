package com.example.football_manager.models.dto;

import com.example.football_manager.models.entity.Team;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class TeamDTO {
    private int id;
    private String name;
    private double commission;
    private double balance;
    private List<FootballerDTO> team_players = new ArrayList<>();

    public TeamDTO(Team team) {
        this.id = team.getId();
        this.name = team.getName();
        this.commission = team.getCommission();
        this.balance = team.getBalance();
        this.team_players = team.getTeam_players()
                .stream()
                .map(FootballerDTO::new)
                .collect(Collectors.toList());
    }

}
