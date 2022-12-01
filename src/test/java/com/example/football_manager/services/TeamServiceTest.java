package com.example.football_manager.services;

import com.example.football_manager.dao.TeamDAO;
import com.example.football_manager.models.dto.TeamDTO;
import com.example.football_manager.models.entity.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TeamServiceTest {
    @Mock
    TeamDAO teamDAO;
    @InjectMocks
    TeamService teamService;
    Team team;
    @BeforeEach
    public void prepareData() {
        team = new Team(1, "Real Madrid", 5, BigDecimal.valueOf(100000000));
    }

    @Test
    void getAll() {
        ArrayList<Team> listTeam = new ArrayList<>();
        listTeam.add(new Team(1, "Real Madrid", 5, BigDecimal.valueOf(100000000)));
        listTeam.add(new Team(2, "Manchester City", 5, BigDecimal.valueOf(80000000)));

        ArrayList<TeamDTO> listTeamDTOs = new ArrayList<>();
        listTeamDTOs.add(new TeamDTO(1, "Real Madrid", 5, BigDecimal.valueOf(100000000)));
        listTeamDTOs.add(new TeamDTO(2, "Manchester City", 5, BigDecimal.valueOf(80000000)));

        when(teamDAO.findAll()).thenReturn(listTeam);
        List<TeamDTO> result = teamService.getAll();

        assertEquals(listTeamDTOs, result);
        verify(teamDAO).findAll();
    }

    @Test
    void getById() {
        when(teamDAO.findById(1)).thenReturn(Optional.of(team));
        TeamDTO result = teamService.getById(1);

        assertEquals(team.getId(), result.getId());
        assertEquals(team.getName(), result.getName());
        assertEquals(team.getCommission(), result.getCommission());
        assertEquals(team.getBalance(), result.getBalance());

        verify(teamDAO).findById(1);
    }

    @Test
    void create() {
        when(teamDAO.save(team)).thenReturn(team);
        Team result = teamService.create(team);

        assertEquals(team.getId(), result.getId());
        assertEquals(team.getName(), result.getName());
        assertEquals(team.getCommission(), result.getCommission());
        assertEquals(team.getBalance(), result.getBalance());

        verify(teamDAO).save(team);
    }

    @Test
    void update() {
        when(teamDAO.save(team)).thenReturn(team);
        Team result = teamService.update(1, team);

        assertEquals(team.getId(), result.getId());
        assertEquals(team.getName(), result.getName());
        assertEquals(team.getCommission(), result.getCommission());
        assertEquals(team.getBalance(), result.getBalance());
    }

}