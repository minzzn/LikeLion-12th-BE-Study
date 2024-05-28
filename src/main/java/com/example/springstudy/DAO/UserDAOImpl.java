package com.example.springstudy.DAO;

import com.example.springstudy.Entity.UserEntity;
import com.example.springstudy.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {
  private final UserRepository userRepository;

  @Autowired
  public UserDAOImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserEntity create(UserEntity userEntity) {
    userRepository.save(userEntity);
    return userEntity;
  }

  @Override
  public UserEntity read(Long id) {
    UserEntity userEntity = userRepository.getReferenceById(id);

    return userEntity;
  }

  @Override
  public void update(Long id, UserEntity userEntity) {
    UserEntity userEntity1 = userRepository.getReferenceById(id);

    userEntity1.updateName(userEntity.getUsername());
    userEntity1.updateEmail(userEntity.getEmail());
    userEntity1.updatePassword(userEntity.getPassword());

    userRepository.save(userEntity1);


  }

  @Override
  public void delete(Long id) {

    userRepository.deleteById(id);
  }

}
