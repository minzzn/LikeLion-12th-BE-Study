package com.example.springstudy.Service;

import com.example.springstudy.DTO.TeamDTO;



public interface TeamService {
  public TeamDTO Team_create(TeamDTO teamDTO);

  public TeamDTO Team_read(Long id);

  public void Team_update(Long id, TeamDTO teamDTO);

  public void Team_delete(Long id);
}
