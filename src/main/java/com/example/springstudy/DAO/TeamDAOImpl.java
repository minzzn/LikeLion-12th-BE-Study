package com.example.springstudy.DAO;


import com.example.springstudy.DTO.TeamDTO;
import com.example.springstudy.Entity.Team;
import com.example.springstudy.Repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TeamDAOImpl implements TeamDAO {
  private final TeamRepository teamRepository;
  @Autowired
  public TeamDAOImpl(TeamRepository teamRepository) {
    this.teamRepository = teamRepository;
  }

  @Override
  public Team Team_create(Team team) {
    //save -> 디비에 저장
    teamRepository.save(team);

    return team;
  }

  @Override
  public Team Team_read(Long id) {
    Team team = teamRepository.getReferenceById(id);
    return team;
  }

  @Override
  public void Team_update(Long id, TeamDTO teamDTO) {

    Team team = teamRepository.getReferenceById(id);
    team.setTeamName(teamDTO.getTeamName());

    teamRepository.save(team);
  }

  @Override
  public void Team_delete(Long id) {
    teamRepository.deleteById(id);
  }
}
