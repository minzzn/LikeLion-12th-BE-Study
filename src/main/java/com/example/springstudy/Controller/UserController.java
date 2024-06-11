package com.example.springstudy.Controller;


import com.example.springstudy.DTO.LoanDTO;
import com.example.springstudy.DTO.UserDTO;

import com.example.springstudy.Entity.UserEntity;
import com.example.springstudy.Service.LoanService;
import com.example.springstudy.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// https://localhost:port/user
@RequestMapping("/users")
public class UserController {

  private UserDTO user;
  private final UserService userService;
  private final LoanService loanService;

  @Autowired
  public UserController(UserService userService, LoanService loanService) {
    this.userService = userService;
    this.loanService = loanService;
  }

  @PostMapping(value = "/create")
  public UserDTO create(@RequestBody UserDTO user){

    this.user = user;
    userService.create(user);
    return this.user;
  }


  @GetMapping("/read")
  public List<UserDTO> readAll() {

    return userService.readAll();
  }

  @GetMapping( "/read/{id}")
  public UserDTO read(@PathVariable(value = "id") Long id) {

    return userService.read(id);
  }


  @PutMapping(value = "/update/{id}")
  public void update(@PathVariable(value = "id")Long id, @RequestBody UserDTO userDTO){
    userService.update(id, userDTO);
  }

  @DeleteMapping( value = "/delete/{id}")
  public ResponseEntity<String> delete(@PathVariable(value = "id")Long id) {
    boolean deleted = userService.delete(id);
    if (deleted) {
      return ResponseEntity.ok("사용자가 삭제됨");
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("사용자 삭제 불가 (대출 중 혹은 사용자가 없음)");
    }
  }

}

