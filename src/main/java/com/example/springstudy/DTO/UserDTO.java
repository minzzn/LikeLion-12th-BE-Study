package com.example.springstudy.DTO;

import com.example.springstudy.Entity.UserEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
  private Long id;
  private  String username;
  private  String email;
  private  Long password;


  @Builder
  public UserDTO(String username, String email, Long password,Long id) {
    this.id=id;
    this.username = username;
    this.email = email;
    this.password = password;
  }





}
