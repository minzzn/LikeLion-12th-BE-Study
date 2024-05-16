package com.example.springstudy.DTO;

import com.example.springstudy.Entity.UserEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

  private  String name;
  private  String email;
  private  String address;
  private String teamName;

  @Builder
  public UserDTO(String name, String email, String address, String teamName) {
    this.name = name;
    this.email = email;
    this.address = address;
    this.teamName=teamName;
  }





}
