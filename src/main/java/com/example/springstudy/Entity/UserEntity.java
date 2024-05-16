package com.example.springstudy.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

//import 단축키 com + enter
@Entity
@Table(name = "user4")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) //시퀀스랑 동일 개념 , 마리아 디비 : 인크리???????
  private Long id;
  @Column
  private String name;
  @Column
  private String email;
  @Column
  private String address;


  @ManyToOne
  @JoinColumn(name = "TEAM_ID")
  private Team team;

  public void setTeam(Team team){
    this.team=team;

    if(!team.getMembers().contains(this)){
      team.getMembers().add(this);
    }
  }

  @Builder
  public UserEntity(String name, String email, String address) {
    this.name = name;
    this.email = email;
    this.address = address;
  }


}