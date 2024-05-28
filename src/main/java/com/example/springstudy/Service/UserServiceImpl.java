package com.example.springstudy.Service;

import com.example.springstudy.DAO.UserDAO;
import com.example.springstudy.DTO.UserDTO;
import com.example.springstudy.Entity.Team;
import com.example.springstudy.Entity.UserEntity;
import com.example.springstudy.Repository.TeamRepository;
import com.example.springstudy.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
  UserRepository userRepository;
  private final UserDAO userDAO;

  //autowired -> 생성자를 통한 의존성을 주입
  @Autowired
  public UserServiceImpl(UserRepository userRepository, UserDAO userDAO) {

    this.userRepository = userRepository;

    this.userDAO = userDAO;
  }

  @Autowired
  TeamRepository teamRepository;

  @Override
  //DTO -> 엔티티
  public UserDTO create(UserDTO userDTO) {
    UserEntity userEntity = UserEntity.builder()
        .username(userDTO.getUsername())
        .email(userDTO.getEmail())
        .password(userDTO.getPassword())
        .build();
    userDAO.create(userEntity);
    return null;
  }


  @Override
  //엔티티 -> DTO
  public UserEntity read(Long id) {

    UserEntity userEntity = userDAO.read(id);
    return toUserResponseDTO(userEntity);
  }

  public UserEntity touserEntity(UserDTO userDTO) {
    return UserEntity.builder()
        .username(userDTO.getUsername())
        .email(userDTO.getEmail())
        .password(userDTO.getPassword())
        .build();
  }

  public UserEntity toUserResponseDTO(UserEntity userEntity) {
    return UserEntity.builder()
        .username(userEntity.getUsername())
        .email(userEntity.getEmail())
        .password(userEntity.getPassword())
        .build();
  }

  @Override
  public void update(Long id, UserDTO userDTO) {
    UserEntity userEntity = UserEntity.builder()
        .username(userDTO.getUsername())
        .email(userDTO.getEmail())
        .password(userDTO.getPassword())
        .build();
    userDAO.update(id, userEntity);

  }

  @Override
  public void delete(Long id) {
    userDAO.delete(id);
  }
  public List<UserEntity> readAll() {
    return userRepository.findAll();
  }
}