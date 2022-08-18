package com.example.football_manager.services;

import com.example.football_manager.dao.TeamDAO;
import com.example.football_manager.models.dto.TeamDTO;
import com.example.football_manager.models.entity.Team;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.*;

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
        Team team = teamDAO.findById(id).orElse(new Team());
        return new TeamDTO(team);
    }

    public TeamDTO create(Team team) {
        return new TeamDTO(teamDAO.save(team));
    }

    public TeamDTO UpTeam(int id, Team team) {
        team.setId(id);
        return new TeamDTO(teamDAO.save(team));
    }

    public List<TeamDTO> deleteTeam(int id) {
        teamDAO.deleteById(id);
        List<Team> all = teamDAO.findAll();
        return all.stream().map(TeamDTO::new).collect(toList());
    }

    public TeamDTO addPlayer(int idTeam, int idPlayer) {
        Team team = teamDAO.findTeamById(idTeam);
        team.getTeam_players().add(footballerService.findOneFootballer(idPlayer));
        return new TeamDTO(teamDAO.save(team));
    }

    public TeamDTO removePlayer(int idTeam, int idPlayer) {
        Team team = teamDAO.findTeamById(idTeam);
        team.getTeam_players().remove(footballerService.findOneFootballer(idPlayer));
        return new TeamDTO(teamDAO.save(team));
    }

    public double fullPrizeTransfer(int idPlayer, int idTeam) {
        Team team = teamDAO.findById(idTeam).orElse(new Team());
        double priseTransfer = footballerService.priseTransfer(idPlayer);
        double commission = team.getCommission() / 100;
        return priseTransfer * commission + priseTransfer;
    }

    public String transfer(int idTeamSeller, int idPlayer, int idTeamCustomer) {
        if (teamDAO.findTeamById(idTeamSeller) == null || teamDAO.findTeamById(idTeamCustomer) == null) {
            return "This teams or player isn't exist";
        }
        Team teamSeller = teamDAO.findById(idTeamSeller).orElse(new Team());
        Team teamCustomer = teamDAO.findById(idTeamCustomer).orElse(new Team());

        double fullPrizeTransfer = fullPrizeTransfer(idPlayer, idTeamSeller);

        if (teamCustomer.getBalance() <= fullPrizeTransfer(idPlayer, idTeamCustomer)) {
            return "Customer don't have enough money";
        }
        double spentManyOnTransfer = teamCustomer.getBalance() - fullPrizeTransfer;
        teamCustomer.setBalance(spentManyOnTransfer);
        double earnedManyOnTransfer = teamSeller.getBalance() + fullPrizeTransfer;
        teamSeller.setBalance(earnedManyOnTransfer);

        teamDAO.save(teamSeller);
        teamDAO.save(teamCustomer);

        removePlayer(idTeamSeller, idPlayer);
        addPlayer(idTeamCustomer, idPlayer);
        return "Prise Transfer is: " + fullPrizeTransfer(idPlayer, idTeamSeller) + "The transfer has been successful";

    }

}
