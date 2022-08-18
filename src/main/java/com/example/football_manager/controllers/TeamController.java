package com.example.football_manager.controllers;

import com.example.football_manager.models.dto.TeamDTO;
import com.example.football_manager.models.entity.Team;
import com.example.football_manager.services.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/teams")
@AllArgsConstructor
public class TeamController {
    TeamService teamService;

    @GetMapping
    public ResponseEntity<List<TeamDTO>> getAll() {
        if (teamService.getAll().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(teamService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamDTO> findById(@PathVariable int id) {
        if (teamService.getById(id) == null || id <= 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(teamService.getById(id), HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<TeamDTO> creat(@RequestBody @Valid Team team) {
        return new ResponseEntity<>(teamService.create(team), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamDTO> upDate(@PathVariable int id, @RequestBody @Valid Team team) {
        if (teamService.UpTeam(id, team) == null || id <= 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(teamService.UpTeam(id, team), HttpStatus.OK);
    }

    @GetMapping("/addPlayer/{teamId}/{playerId}")
    public ResponseEntity<TeamDTO> addPlayer(@PathVariable int teamId, @PathVariable int playerId) {
        if (teamService.addPlayer(teamId, playerId) == null || teamId <= 0 || playerId <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(teamService.addPlayer(teamId, playerId), HttpStatus.ACCEPTED);
    }

    @GetMapping("/removePlayer/{teamId}/{playerId}")
    public ResponseEntity<TeamDTO> removePlayer(@PathVariable int teamId, @PathVariable int playerId) {
        if (teamService.removePlayer(teamId, playerId) == null || teamId <= 0 || playerId <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(teamService.removePlayer(teamId, playerId), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<TeamDTO>> delete(@PathVariable int id) {
        if (teamService.deleteTeam(id).isEmpty() || id <= 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(teamService.deleteTeam(id), HttpStatus.OK);
    }

    @GetMapping("/transfer/{idTeamSeller}/{idPlayer}/{idTeamCustomer}")
    public ResponseEntity<String> transfer(@PathVariable int idTeamSeller, @PathVariable int idPlayer, @PathVariable int idTeamCustomer) {
        if (idTeamSeller <= 0 || idPlayer <= 0 || idTeamCustomer <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(teamService.transfer(idTeamSeller, idPlayer, idTeamCustomer), HttpStatus.ACCEPTED);
    }
}
