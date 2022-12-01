package com.example.football_manager.services;

import com.example.football_manager.dao.TeamDAO;
import com.example.football_manager.models.dto.TeamDTO;
import com.example.football_manager.models.entity.Team;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class TeamService {
    private TeamDAO teamDAO;
    FootballerService footballerService;
    public List<TeamDTO> getAll() {
        List<Team> all = teamDAO.findAll();
        return all.stream().map(TeamDTO::new).collect(toList());
    }
    public TeamDTO getById(int id) {
        Team team = teamDAO.findById(id).orElseThrow(() -> new NullPointerException("This team isn't exist"));
        return new TeamDTO(team);
    }
    public Team create(Team team) {
        return new Team(teamDAO.save(team));
    }
    public Team update(int id, Team team) {
        team.setId(id);
        return new Team(teamDAO.save(team));
    }
    public List<TeamDTO> delete(int id) {
        teamDAO.deleteById(id);
        List<Team> all = teamDAO.findAll();
        return all.stream().map(TeamDTO::new).collect(toList());
    }

    public TeamDTO addPlayer(int idTeam, int idPlayer) {
        Team team = teamDAO.findTeamById(idTeam);
        team.getFootballersOfTeam().add(footballerService.findOneFootballer(idPlayer));
        return new TeamDTO(teamDAO.save(team));
    }
    public TeamDTO removePlayer(int idTeam, int idPlayer) {
        Team team = teamDAO.findTeamById(idTeam);
        team.getFootballersOfTeam().remove(footballerService.findOneFootballer(idPlayer));
        return new TeamDTO(teamDAO.save(team));
    }
}
