package com.example.springstudy.Controller;


import com.example.springstudy.DTO.TeamDTO;
import com.example.springstudy.DTO.UserDTO;
import com.example.springstudy.Service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
// https://localhost:port/team
@RequestMapping("/team")
public class TeamController {
  private TeamDTO team;
  private final TeamService teamService;

  @Autowired
  public TeamController(TeamService teamService) {
    this.teamService = teamService;
  }

  @PostMapping(value = "/create")
  public TeamDTO Team_create(@RequestBody TeamDTO team){
    this.team = team;
    teamService.Team_create(team);
    return this.team;
  }


  @GetMapping("/read/{id}")
  public TeamDTO Team_read(@PathVariable(value = "id") Long id) {

    TeamDTO teamDTO = teamService.Team_read(id);
    return teamDTO;
  }


  @PutMapping(value = "/update/{id}")
  public void Team_update(@PathVariable(value = "id") Long id, @RequestBody TeamDTO team){

    teamService.Team_update(id,team);

  }

  @DeleteMapping(value = "/{id}")
  public void Team_delete(@PathVariable(value = "id") Long id){
    teamService.Team_delete(id);
  }
}
