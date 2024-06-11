package com.example.springstudy.Service;

import com.example.springstudy.DTO.UserDTO;

import java.util.List;

public interface UserService {


  //엔티티 -> DTO


  //엔티티 -> DTO
  UserDTO create(UserDTO userDTO);

  public UserDTO read(Long id);

  void update(Long id, UserDTO userDTO);

  public boolean delete(Long id);

  List<UserDTO> readAll();


}
