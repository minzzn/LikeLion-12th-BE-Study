package org.example.springstudy.Service;

import org.example.springstudy.DTO.TeamDTO;
import org.example.springstudy.DTO.TeamResponseDTO;
import org.example.springstudy.Entity.Team;
import org.example.springstudy.Repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService{
    private final TeamRepository teamRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public TeamResponseDTO createTeam(TeamDTO teamDTO) {
        Team team = toEntity(teamDTO);

        teamRepository.save(team);

        return tpTeamResponseDTO(team);
    }

    @Override
    public TeamResponseDTO readTeam(Long id) {
        Team team = teamRepository.getReferenceById(id);

        return tpTeamResponseDTO(team);
    }

    @Override
    public Team readTeamEntity(Long id) {
        return teamRepository.getReferenceById(id);
    }

    @Override
    public TeamResponseDTO updateTeam(TeamDTO teamDTO) {
        return null;
    }

    @Override
    public String deleteTeam(Long id) {
        teamRepository.deleteById(id);
        return "success";
    }

    public Team toEntity(TeamDTO teamDTO){
        return Team.builder()
                .name(teamDTO.getName())
                .build();
    }

    @Override
    public TeamResponseDTO tpTeamResponseDTO(Team team){
        if(team == null)
            return null;
        return TeamResponseDTO.builder()
                .id(team.getId())
                .name(team.getName())
                .build();
    }
}
