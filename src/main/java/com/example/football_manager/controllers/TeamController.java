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
    public ResponseEntity<List<TeamDTO>> getAllTeam() {
        if (teamService.getAll().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(teamService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TeamDTO> findByIdTeam(@PathVariable int id) {
        if (teamService.getById(id) == null || id <= 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(teamService.getById(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Team> creatTeam(@RequestBody @Valid Team team) {
        return new ResponseEntity<>(teamService.create(team), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable int id, @RequestBody @Valid Team team) {
        if (teamService.update(id, team) == null || id <= 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(teamService.update(id, team), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<List<TeamDTO>> deleteTeam(@PathVariable int id) {
        if (teamService.delete(id).isEmpty() || id <= 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(teamService.delete(id), HttpStatus.OK);
    }
    @GetMapping("/addPlayer/{teamId}/{playerId}")
    public ResponseEntity<TeamDTO> addPlayerToTeam(@PathVariable int teamId, @PathVariable int playerId) {
        if (teamService.addPlayer(teamId, playerId) == null || teamId <= 0 || playerId <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(teamService.addPlayer(teamId, playerId), HttpStatus.OK);
    }
    @GetMapping("/removePlayer/{teamId}/{playerId}")
    public ResponseEntity<TeamDTO> removePlayerFromTeam(@PathVariable int teamId, @PathVariable int playerId) {
        if (teamService.removePlayer(teamId, playerId) == null || teamId <= 0 || playerId <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(teamService.removePlayer(teamId, playerId), HttpStatus.OK);
    }

}
