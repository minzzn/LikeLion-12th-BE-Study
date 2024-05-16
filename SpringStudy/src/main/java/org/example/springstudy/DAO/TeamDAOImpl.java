package org.example.springstudy.DAO;

import org.checkerframework.checker.units.qual.A;
import org.example.springstudy.Entity.Team;
import org.example.springstudy.Repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TeamDAOImpl implements TeamDAO{
    private final TeamRepository teamRepository;

    @Autowired
    public TeamDAOImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public Team createTeam(Team teamEntity) {
        teamRepository.save(teamEntity);

        return teamEntity;
    }

    @Override
    public Team readTeam(Long id) {
        Team teamEntity = teamRepository.getReferenceById(id);

        return teamEntity;
    }

    @Override
    public Team updateTeam(Long id, Team teamEntity) {
        return null;
    }

    @Override
    public String deleteTeam(Long id) {
        teamRepository.deleteById(id);

        return "success";
    }
}
