package com.example.springstudy.DAO;

import com.example.springstudy.DTO.UserDTO;
import com.example.springstudy.Entity.UserEntity;

public interface UserDAO {
  UserEntity create(UserEntity userEntity);

  public UserEntity read(Long id);

  void update(Long id, UserEntity userEntity);

  public void delete(Long id);

}
