package com.example.springstudy.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.List;

//import 단축키 com + enter
@Entity
@Table(name = "user")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column
  private String username;
  @Column
  private String email;
  @Column
  private Long password;
  private int currentLoans = 0;


  @OneToMany(mappedBy = "user")
  private List<LoanEntity> loans;


  @Builder
  public UserEntity(String username, String email, Long password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }


  public void updateName(String username) {

    this.username = username;
  }

  public void updateEmail(String email) {

    this.email = email;
  }

  public void updatePassword(Long password) {

    this.password=password;
  }

  public boolean hasBooksBorrowed() {
    return !loans.isEmpty();
  }

  public int getCurrentLoans() {
    return currentLoans;
  }

  public void setCurrentLoans(int currentLoans) {
    this.currentLoans = currentLoans;
  }


}