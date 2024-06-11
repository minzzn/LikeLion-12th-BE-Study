package com.example.springstudy.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TeamDTO {
  private  String teamName;


  @Builder
  public TeamDTO(String teamName) {

    this.teamName = teamName;
  }
}

