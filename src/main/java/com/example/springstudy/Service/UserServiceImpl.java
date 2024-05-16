package com.example.springstudy.Service;

import com.example.springstudy.DTO.UserDTO;
import com.example.springstudy.Entity.UserEntity;
import com.example.springstudy.Repository.TeamRepository;
import com.example.springstudy.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
 UserRepository userRepository;

 //autowired -> 생성자를 통한 의존성을 주입
@Autowired
  public UserServiceImpl(UserRepository userRepository) {

  this.userRepository = userRepository;

  }

@Autowired
TeamRepository teamRepository;
  @Override
  //엔티티 -> DTO
  public UserDTO create(UserDTO userDTO) {
    UserEntity userEntity = UserEntity.builder()
        .name(userDTO.getName())
        .email(userDTO.getEmail())
        .address(userDTO.getAddress())
        .build();

    userRepository.save(userEntity);

    return null;
  }


  @Override
  //DTO->엔티티
  public UserDTO read(Long id) {
    UserEntity userEntity=userRepository.findById(id).orElseThrow();


    UserDTO userDTO = UserDTO.builder()
        .name(userEntity.getName())
        .email(userEntity.getEmail())
        .address(userEntity.getAddress())
        .build();
    return userDTO;
  }

@Override
  public void update(Long id, UserDTO userDTO) {
    UserEntity userEntity = userRepository.findById(id).orElseThrow();

    userEntity.setName(userDTO.getName());
    userEntity.setEmail(userDTO.getEmail());
    userEntity.setAddress(userDTO.getAddress());

    userRepository.save(userEntity);
  }



  @Override
  public void delete(Long id) {

    userRepository.deleteById(id);
  }
@Override
  public void userTeamUpdate(Long id, UserDTO userDTO){

  }
}
