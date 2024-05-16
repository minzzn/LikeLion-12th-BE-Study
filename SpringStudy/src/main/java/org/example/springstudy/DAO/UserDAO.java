package org.example.springstudy.DAO;

import org.example.springstudy.Entity.Team;
import org.example.springstudy.Entity.User;

public interface UserDAO {
    public User createUser(User userEntity);
    public User readUser(Long id);
    public User updateUser(Long id, User userEntity);
    public User setTeam(Long id, Team teamEntity);
    public String deleteUser(Long id);
}
