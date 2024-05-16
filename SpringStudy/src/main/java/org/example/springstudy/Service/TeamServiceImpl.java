package org.example.springstudy.Service;

import org.example.springstudy.DAO.TeamDAO;
import org.example.springstudy.DTO.TeamDTO;
import org.example.springstudy.DTO.TeamResponseDTO;
import org.example.springstudy.DTO.UserResponseDTO;
import org.example.springstudy.Entity.Team;
import org.example.springstudy.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService{
    private final TeamDAO teamDAO;
    private final UserService userService;

    @Autowired
    public TeamServiceImpl(TeamDAO teamDAO, @Lazy UserService userService) {
        this.teamDAO = teamDAO;
        this.userService = userService;
    }

    @Override
    public TeamResponseDTO createTeam(TeamDTO teamDTO) {
        Team teamEntity = toEntity(teamDTO);

        Team resultEntity = teamDAO.createTeam(teamEntity);

        return toTeamResponseDTO(resultEntity);
    }

    @Override
    public TeamResponseDTO readTeam(Long id) {
        Team teamEntity = teamDAO.readTeam(id);

        return toTeamResponseDTO(teamEntity);
    }

    @Override
    public Team readTeamEntity(Long id) {
        return teamDAO.readTeam(id);
    }

    @Override
    public TeamResponseDTO updateTeam(TeamDTO teamDTO) {
        return null;
    }

    @Override
    public String deleteTeam(Long id) {
        String result = teamDAO.deleteTeam(id);

        return result;
    }

    public Team toEntity(TeamDTO teamDTO){
        return Team.builder()
                .name(teamDTO.getName())
                .build();
    }

    public TeamResponseDTO toTeamResponseDTO(Team team){
        if(team.getUsers().isEmpty()) {
            return TeamResponseDTO.builder()
                    .id(team.getId())
                    .name(team.getName())
                    .users(null)
                    .build();
        }
        else {
            List<UserResponseDTO> users = new ArrayList<UserResponseDTO>();

            for(User user : team.getUsers()){
                UserResponseDTO userResponseDTO = userService.toUserResponseDTO(user);
                users.add(userResponseDTO);
            }

            return TeamResponseDTO.builder()
                    .id(team.getId())
                    .name(team.getName())
                    .users(users)
                    .build();
        }
    }

    @Override
    public TeamDTO toTeamDTO(Team team){
        if(team == null)
            return null;
        return TeamDTO.builder()
                .id(team.getId())
                .name(team.getName())
                .build();
    }
}
