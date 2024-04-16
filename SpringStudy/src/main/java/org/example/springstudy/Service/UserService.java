package org.example.springstudy.Service;

import org.example.springstudy.DTO.UserDTO;
import org.example.springstudy.DTO.UserResponseDTO;

public interface UserService {
    public UserResponseDTO createUser(UserDTO userDTO);
    public UserResponseDTO readUser(Long id);
    public UserResponseDTO updateUser(Long id, UserDTO userDTO);
    public String deleteUser(Long id);
}
