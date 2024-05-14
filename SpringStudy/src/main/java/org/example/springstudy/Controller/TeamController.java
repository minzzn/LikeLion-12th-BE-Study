package org.example.springstudy.Controller;

import org.example.springstudy.DTO.TeamDTO;
import org.example.springstudy.DTO.TeamResponseDTO;
import org.example.springstudy.Service.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/team")
public class TeamController {
    private final TeamService teamService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping(value = "/create")
    public TeamResponseDTO createTeam(@RequestBody TeamDTO teamDTO){
        logger.info("TeamController-CreateTeam [Data]: " + teamDTO);
        return teamService.createTeam(teamDTO);
    }

    @GetMapping(value = "/read/{id}")
    public TeamResponseDTO readTeam(@PathVariable(value = "id") Long id){
        logger.info("TeamController-ReadTeam [Data]: " + id);
        return teamService.readTeam(id);
    }

    @PutMapping(value = "/update")
    public TeamResponseDTO updateTeam(@RequestBody TeamDTO teamDTO){
        logger.info("TeamController-UpdateTeam [Data]: " + teamDTO);
        return teamService.updateTeam(teamDTO);
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteTeam(@PathVariable(value = "id") Long id){
        logger.info("TeamController-DeleteTeam [Data]: " + id);
        return teamService.deleteTeam(id);
    }
}
