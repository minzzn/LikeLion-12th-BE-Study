package com.example.springstudy.DAO;

import com.example.springstudy.DTO.TeamDTO;
import com.example.springstudy.Entity.Team;

public interface TeamDAO {
  public Team Team_create(Team team);

  public Team Team_read(Long id);

  public void Team_update(Long id, TeamDTO teamDTO);

  public void Team_delete(Long id);
}
