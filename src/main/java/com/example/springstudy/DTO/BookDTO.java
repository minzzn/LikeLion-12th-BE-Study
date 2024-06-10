package com.example.springstudy.DTO;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookDTO {
  private String title;

  private String author;

  private Long isbn;

  private String publishedDate;

  @Builder
  public BookDTO(String title, String author, Long isbn, String publishedDate) {
    this.title=title;
    this.author=author;
    this.isbn=isbn;
    this.publishedDate=publishedDate;
  }



}

