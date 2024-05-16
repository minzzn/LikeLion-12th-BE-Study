package org.example.springstudy.Service;

import org.example.springstudy.DTO.TeamDTO;
import org.example.springstudy.DTO.TeamResponseDTO;
import org.example.springstudy.Entity.Team;

public interface TeamService {
    public TeamResponseDTO createTeam(TeamDTO teamDTO);
    public TeamResponseDTO readTeam(Long id);
    public Team readTeamEntity(Long id);
    public TeamResponseDTO updateTeam(TeamDTO teamDTO);
    public String deleteTeam(Long id);
    public TeamDTO toTeamDTO(Team team);
}
