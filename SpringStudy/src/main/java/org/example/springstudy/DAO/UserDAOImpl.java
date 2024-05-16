package org.example.springstudy.DAO;

import org.checkerframework.checker.units.qual.A;
import org.example.springstudy.Entity.Team;
import org.example.springstudy.Entity.User;
import org.example.springstudy.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO{
    private final UserRepository userRepository;

    @Autowired
    public UserDAOImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User userEntity){
        userRepository.save(userEntity);
        return userEntity;
    }

    @Override
    public User readUser(Long id){
        User userEntity = userRepository.getReferenceById(id);
        return userEntity;
    }

    @Override
    public User updateUser(Long id, User userEntity){
        User oldUserEntity = userRepository.getReferenceById(id);

        oldUserEntity.updateName(userEntity.getName());
        oldUserEntity.updateEmail(userEntity.getEmail());
        oldUserEntity.updateAddress(userEntity.getAddress());

        userRepository.save(oldUserEntity);

        return oldUserEntity;
    }

    @Override
    public User setTeam(Long id, Team teamEntity){
        User userEntity = userRepository.getReferenceById(id);
        userEntity.updateTeam(teamEntity);
        userRepository.save(userEntity);

        return userEntity;
    }

    @Override
    public String deleteUser(Long id){
        userRepository.deleteById(id);
        return "success";
    }
}
