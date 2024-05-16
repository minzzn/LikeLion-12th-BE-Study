package org.example.springstudy.Service;

import org.example.springstudy.DTO.UserDTO;
import org.example.springstudy.DTO.UserResponseDTO;
import org.example.springstudy.Entity.User;

public interface UserService {
    public UserResponseDTO createUser(UserDTO userDTO);
    public UserResponseDTO readUser(Long id);
    public UserResponseDTO updateUser(Long id, UserDTO userDTO);
    public UserResponseDTO setTeam(Long userId, Long teamId);
    public String deleteUser(Long id);
    public UserResponseDTO toUserResponseDTO(User user);
}
