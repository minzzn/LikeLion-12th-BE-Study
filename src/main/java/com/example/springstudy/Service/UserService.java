package com.example.springstudy.Service;

import com.example.springstudy.DTO.UserDTO;
import com.example.springstudy.Entity.UserEntity;

import java.util.List;

public interface UserService {


  //엔티티 -> DTO


  //엔티티 -> DTO
  UserDTO create(UserDTO userDTO);

  public UserEntity read(Long id);

  void update(Long id, UserDTO userDTO);

  public void delete(Long id);

  List<UserEntity> readAll();
}
