package com.example.springstudy.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LoanEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  //다:1
 @ManyToOne
 @JoinColumn(name = "book")
 private BookEntity book;

 @ManyToOne
 @JoinColumn(name = "user")
 private UserEntity user;


 //datetime으로 데이터타입 변경
  private LocalDateTime loanDate;
  private LocalDateTime returnDate;
  private boolean returned;

  @Builder
  public LoanEntity(BookEntity book, UserEntity user, LocalDateTime loanDate, LocalDateTime returnDate) {
    this.book=book;
    this.user=user;
    this.loanDate=loanDate;
    this.returnDate=returnDate;
  }

  @PrePersist
  protected void onCreate() {
    if (this.loanDate == null) {
      this.loanDate = LocalDateTime.parse(getCurrentDate());
    }
  }

  private String getCurrentDate() {
    LocalDate today = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    return today.format(formatter);
  }


  public LocalDateTime getLoanDate() {
    return loanDate;
  }

  public void setLoanDate(LocalDateTime loanDate) {
    this.loanDate = loanDate;
    this.returnDate = loanDate.plus(7, ChronoUnit.DAYS);
  }

  public LocalDateTime getReturnDate() {
    return returnDate;
  }

  public void setReturnDate(LocalDateTime returnDate) {
    this.returnDate = returnDate;
  }

  public void setReturnDate(String returnDate) {

    this.returnDate = LocalDateTime.parse(returnDate);
  }

  public void setBook(Optional<BookEntity> book) {
  }

  public void setUser(Optional<UserEntity> user) {
  }

  public boolean isReturned() {
    return this.returned;
  }
}
