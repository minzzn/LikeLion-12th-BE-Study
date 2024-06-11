package com.example.springstudy.Service;

import com.example.springstudy.DAO.UserDAO;
import com.example.springstudy.DTO.UserDTO;
import com.example.springstudy.Entity.LoanEntity;
import com.example.springstudy.Entity.UserEntity;
import com.example.springstudy.Repository.LoanRepository;
import com.example.springstudy.Repository.TeamRepository;
import com.example.springstudy.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
  UserRepository userRepository;
  LoanRepository loanRepository;
  private final UserDAO userDAO;

  @Autowired
  public UserServiceImpl(UserRepository userRepository, UserDAO userDAO, LoanRepository loanRepository) {

    this.userRepository = userRepository;

    this.userDAO = userDAO;
    this.loanRepository=loanRepository;
  }



  @Override
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
  public UserDTO read(Long id) {

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


  public UserDTO toUserResponseDTO(UserEntity userEntity) {
    UserDTO userDTO = new UserDTO();
    userDTO.setId(userEntity.getId());
    userDTO.setUsername(userEntity.getUsername());
    userDTO.setEmail(userEntity.getEmail());
    return userDTO;
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
  public boolean delete(Long userId) {
    UserEntity user = userRepository.findById(userId).orElse(null);
    if (user == null) {
      return false;
    }

    List<LoanEntity> loans = loanRepository.findByUserId(userId);

    for (LoanEntity loan : loans) {
      if (!loan.isReturned()) {
        return false;
      }
    }

    loanRepository.deleteAll(loans);

    userRepository.delete(user);
    return true;
  }


  public List<UserDTO> readAll() {
    List<UserEntity> userEntities = userRepository.findAll();
    List<UserDTO> userDTOS = new ArrayList<>();

    for (UserEntity userEntity : userEntities) {
      UserDTO userDTO = new UserDTO();
      userDTO.setId(userEntity.getId());
      userDTO.setEmail(userEntity.getEmail());
      userDTO.setUsername(userEntity.getUsername());
      userDTO.setPassword(userEntity.getPassword());
      userDTOS.add(userDTO);
    }

    return userDTOS;
  }



}