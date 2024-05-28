package com.example.springstudy.Service;

import com.example.springstudy.DAO.TeamDAO;
import com.example.springstudy.DTO.TeamDTO;
import com.example.springstudy.Entity.Team;

import org.springframework.stereotype.Service;


@Service
public class TeamServiceImpl implements TeamService {
  private final TeamDAO teamDAO;
  private final UserService userService;

  public TeamServiceImpl(TeamDAO teamDAO, UserService userService) {
    this.teamDAO = teamDAO;
    this.userService = userService;
  }


  @Override
  public Team Team_create(TeamDTO teamDTO) {
    Team team = Team.builder()
        .teamName(teamDTO.getTeamName())
        .build();
    teamDAO.Team_create(team);
    return team;
  }



  @Override
  public Team Team_read(Long id) {

    Team team = teamDAO.Team_read(id);
    return team;
  }


  @Override
  public void Team_update(Long id, TeamDTO teamDTO) {
    teamDAO.Team_update(id, teamDTO);
  }


  @Override
  public void Team_delete(Long id) {

    teamDAO.Team_delete(id);
  }
}
