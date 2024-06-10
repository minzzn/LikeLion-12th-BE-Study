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

  //autowired -> 생성자를 통한 의존성을 주입
  @Autowired
  public UserServiceImpl(UserRepository userRepository, UserDAO userDAO, LoanRepository loanRepository) {

    this.userRepository = userRepository;

    this.userDAO = userDAO;
    this.loanRepository=loanRepository;
  }



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
      return false; // 사용자가 없는 경우 삭제 실패
    }

    // 사용자가 대출한 도서 목록을 가져옴
    List<LoanEntity> loans = loanRepository.findByUserId(userId);

    // 사용자가 대출 중인 도서가 있는지 확인
    for (LoanEntity loan : loans) {
      if (!loan.isReturned()) {
        return false; // 대출 중인 도서가 있으면 삭제 거부
      }
    }

    // 사용자가 대출한 도서 목록을 삭제
    loanRepository.deleteAll(loans);

    // 사용자 삭제
    userRepository.delete(user);
    return true; // 삭제 성공
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