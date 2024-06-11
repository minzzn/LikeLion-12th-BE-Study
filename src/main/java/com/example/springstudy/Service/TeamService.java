package com.example.springstudy.Service;

import com.example.springstudy.DTO.TeamDTO;
import com.example.springstudy.Entity.Team;


public interface TeamService {
  public Team Team_create(TeamDTO teamDTO);

  public Team Team_read(Long id);

  public void Team_update(Long id, TeamDTO teamDTO);

  public void Team_delete(Long id);
}
