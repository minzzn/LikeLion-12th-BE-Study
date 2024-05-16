package org.example.springstudy.Service;

import org.example.springstudy.DAO.UserDAO;
import org.example.springstudy.DTO.TeamResponseDTO;
import org.example.springstudy.DTO.UserDTO;
import org.example.springstudy.DTO.UserResponseDTO;
import org.example.springstudy.Entity.Team;
import org.example.springstudy.Entity.User;
import org.example.springstudy.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private final UserDAO userDAO;
    private final TeamService teamService;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, TeamService teamService) {
        this.userDAO = userDAO;
        this.teamService = teamService;
    }

    @Override
    public UserResponseDTO createUser(UserDTO userDTO) {
        User userEntity = toEntity(userDTO);

        User resultEntity = userDAO.createUser(userEntity);

        return toUserResponseDTO(resultEntity);
    }

    @Override
    public UserResponseDTO readUser(Long id) {
        User userEntity = userDAO.readUser(id);
        return toUserResponseDTO(userEntity);
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserDTO userDTO) {
        User userEntity = toEntity(userDTO);

        User resultEntity = userDAO.updateUser(id, userEntity);

        return toUserResponseDTO(resultEntity);
    }

    @Override
    public UserResponseDTO setTeam(Long userId, Long teamId){
        Team teamEntity = teamService.readTeamEntity(teamId);
        User userEntity = userDAO.setTeam(userId, teamEntity);

        return toUserResponseDTO(userEntity);
    }

    @Override
    public String deleteUser(Long id) {
        String result = userDAO.deleteUser(id);

        return result;
    }

    // DTO -> User
    public User toEntity(UserDTO userDTO){
        return User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .address(userDTO.getAddress())
                .build();
    }

    // User -> DTO
    @Override
    public UserResponseDTO toUserResponseDTO(User user){
        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .address(user.getAddress())
                .team(teamService.toTeamDTO(user.getTeam()))
                .build();
    }
}
