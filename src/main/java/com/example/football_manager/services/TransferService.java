package com.example.football_manager.services;

import com.example.football_manager.dao.TeamDAO;
import com.example.football_manager.models.dto.FootballerDTO;
import com.example.football_manager.models.dto.TeamDTO;
import com.example.football_manager.models.entity.Team;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Service
@AllArgsConstructor
public class TransferService {
    FootballerService footballerService;
    TeamService teamService;
    TeamDAO teamDAO;
    private BigDecimal priseTransfer(int idPlayer) {
        LocalDate now = LocalDate.now();
        FootballerDTO footballer = footballerService.getById(idPlayer);
        long experience = Period.between(footballer.getCareerStartDate(), now).toTotalMonths();
        int age = Period.between(footballer.getDateOfBirth(), now).getYears();
        return BigDecimal.valueOf(100000 * experience / age);
    }
    private BigDecimal fullPrizeTransfer(int idPlayer, int idTeam) {
        TeamDTO team = teamService.getById(idTeam);
        BigDecimal priseTransfer = priseTransfer(idPlayer);
        BigDecimal commission = BigDecimal.valueOf((team.getCommission() / 100) + 1);
        return priseTransfer.multiply(commission);
    }
    @Transactional
    public String transfer(int idTeamSeller, int idPlayer, int idTeamCustomer) {

        Team teamSeller = teamDAO.findById(idTeamSeller)
                .orElseThrow(() -> new NullPointerException("This team isn't exist"));
        Team teamCustomer = teamDAO.findById(idTeamCustomer)
                .orElseThrow(() -> new NullPointerException("This team isn't exist"));

        BigDecimal fullPrizeTransfer = fullPrizeTransfer(idPlayer, idTeamSeller);

        if (teamCustomer.getBalance().compareTo(fullPrizeTransfer) <= 0) {
            return "Customer don't have enough money";
        }

        BigDecimal spentManyOnTransfer = teamCustomer.getBalance().subtract(fullPrizeTransfer);
        teamCustomer.setBalance(spentManyOnTransfer);
        BigDecimal earnedManyOnTransfer = teamSeller.getBalance().add(fullPrizeTransfer);
        teamSeller.setBalance(earnedManyOnTransfer);

        teamService.removePlayer(idTeamSeller, idPlayer);
        teamService.addPlayer(idTeamCustomer, idPlayer);

        teamDAO.save(teamSeller);
        teamDAO.save(teamCustomer);

        String teamSellerName = teamSeller.getName();
        String teamCustomerName = teamCustomer.getName();
        String playerName = footballerService.getById(idPlayer).getName();

        return playerName + " was transferred from " + teamSellerName + " to " + teamCustomerName
                + " Transfer price " + fullPrizeTransfer;
    }
}
