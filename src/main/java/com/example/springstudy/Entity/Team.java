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


public class Team {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  private Long id;
  private String teamName;



  @Builder
  public Team(String teamName) {
    this.teamName = teamName;
  }
}