package com.example.springstudy.Controller;


import com.example.springstudy.DTO.UserDTO;

import com.example.springstudy.Entity.UserEntity;
import com.example.springstudy.Repository.TeamRepository;
import com.example.springstudy.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// https://localhost:port/user
@RequestMapping("/users")
public class UserController {



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


  @GetMapping("/read")
  public List<UserEntity> readAll() {
    return userService.readAll();
  }

  @GetMapping( "/read/{id}")
  public UserEntity read(@PathVariable(value = "id") Long id) {
    return userService.read(id);
  }

  @PutMapping(value = "/update/{id}")
  public void update(@PathVariable(value = "id")Long id, @RequestBody UserDTO userDTO){
    userService.update(id, userDTO);
  }

  @DeleteMapping( value = "/delete/{id}")
  public void delete(@PathVariable(value = "id") Long id){

    userService.delete(id);
  }


}

