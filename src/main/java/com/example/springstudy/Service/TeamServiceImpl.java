package com.example.springstudy.Service;

import com.example.springstudy.DTO.TeamDTO;
import com.example.springstudy.Entity.Team;
import com.example.springstudy.Entity.UserEntity;
import com.example.springstudy.Repository.TeamRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TeamServiceImpl implements TeamService {
  TeamRepository teamRepository;
  private UserEntity userEntity;

  @Autowired
  public TeamServiceImpl(TeamRepository teamRepository) {

    this.teamRepository=teamRepository;

  }


  @Override
  public TeamDTO Team_create(TeamDTO teamDTO) {
    Team team = Team.builder()
        .teamName(teamDTO.getTeamName())
        .build();

    //save -> 디비에 저장
    teamRepository.save(team);

    return null;
  }



  @Override
  public TeamDTO Team_read(Long id) {
    Team team = teamRepository.findById(id).orElseThrow();
    TeamDTO teamDTO = TeamDTO.builder()
        .teamName(team.getTeamName())
        .build();
    return teamDTO;
  }


  @Override
  public void Team_update(Long id, TeamDTO teamDTO) {
    Team team = teamRepository.findById(id).orElseThrow();
    team.setTeamName(teamDTO.getTeamName());

    teamRepository.save(team);
  }


  @Override
  public void Team_delete(Long id) {
    teamRepository.deleteById(id);
  }
}
