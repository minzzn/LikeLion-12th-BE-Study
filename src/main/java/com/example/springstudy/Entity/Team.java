package com.example.springstudy.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@Table(name = "team")

public class Team {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  private Long id;
  private String teamName;

@OneToMany(mappedBy = "team")

  private List<UserEntity> members = new ArrayList<>();

  public void addMember(UserEntity userEntity){
    this.members.add(userEntity);

    if (this != userEntity.getTeam()){
      userEntity.setTeam(this);
    }
  }




  @Builder
  public Team(String teamName) {
    this.teamName = teamName;
  }
}