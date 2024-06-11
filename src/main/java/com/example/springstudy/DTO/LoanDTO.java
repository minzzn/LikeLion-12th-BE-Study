package com.example.springstudy.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class LoanDTO {

  private Long book;
  private Long user;
  private LocalDateTime loanDate;
  private LocalDateTime returnDate;
  private String message;

  @Builder
  public LoanDTO(Long book, Long user, LocalDateTime loanDate, LocalDateTime returnDate) {
    this.book = book;
    this.user = user;
    this.loanDate = loanDate;
    this.returnDate = returnDate;
  }


  public LocalDateTime getLoanDate() {
    return loanDate;
  }

  public void setLoanDate(LocalDateTime loanDate) {
    this.loanDate = loanDate;
  }

  public LocalDateTime getReturnDate() {
    return returnDate;
  }

  public void setReturnDate(LocalDateTime returnDate) {
    this.returnDate = returnDate;
  }
  public LoanDTO(String message) {
    this.message = message;
  }
}
