package org.example.springstudy.DAO;

import org.example.springstudy.Entity.Team;

public interface TeamDAO {
    public Team createTeam(Team teamEntity);
    public Team readTeam(Long id);
    public Team updateTeam(Long id, Team teamEntity);
    public String deleteTeam(Long id);
}
