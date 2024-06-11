package com.example.springstudy.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class BookDTO {
  private String title;
  private String author;
  private Long isbn;
  private LocalDate publishedDate;

  @Builder
  public BookDTO(String title, String author, Long isbn, LocalDate publishedDate) {
    this.title = title;
    this.author = author;
    this.isbn = isbn;
    this.publishedDate = publishedDate;
  }
}
