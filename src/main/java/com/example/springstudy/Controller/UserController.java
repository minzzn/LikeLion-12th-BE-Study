package com.example.springstudy.Controller;


import com.example.springstudy.DTO.UserDTO;

import com.example.springstudy.Entity.Team;
import com.example.springstudy.Repository.TeamRepository;
import com.example.springstudy.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
// https://localhost:port/user
@RequestMapping("/user")
public class UserController {

  @Autowired
  TeamRepository teamRepository;

  private UserDTO user;
  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping(value = "/create")
  public UserDTO create(@RequestBody UserDTO user){

    this.user = user;
    userService.create(user);
    return this.user;
  }

  @GetMapping( "/read/{id}")
  public UserDTO read(@PathVariable(value = "id") Long id) {
    UserDTO userDTO = userService.read(id);
    return userDTO;
  }

  @PutMapping(value = "/update/{id}")
  public void update(@PathVariable(value = "id")Long id, @RequestBody UserDTO userDTO){
    userService.update(id, userDTO);
  }

  @DeleteMapping( value = "/delete/{id}")
  public void delete(@PathVariable(value = "id") Long id){
    userService.delete(id);
  }

  // 유저에 대한 팀을 업데이트하는 컨트롤러 생성(update)
  @PostMapping(value = "/{UserID}/{TeamID}")
  public UserDTO userTeamUpdate(@PathVariable(value = "UserID")Long UserID,
                                @PathVariable(value = "TeamID") Long TeamID){
    return userService.userTeamUpdate(UserID,TeamID);
  }
}

