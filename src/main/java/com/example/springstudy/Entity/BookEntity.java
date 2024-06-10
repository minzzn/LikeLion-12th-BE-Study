package com.example.springstudy.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "book")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BookEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column
  private String title;
  @Column
  private String author;
  @Column
  private Long isbn;
  @Column
  private String publishedDate;
  private boolean isBorrowed;

  @Builder
  public BookEntity(String title, String author, Long isbn, String publishedDate) {
   this.title=title;
   this.author=author;
   this.isbn=isbn;
   this.publishedDate=publishedDate;
  }

  public void updateTitle(String title) {

      this.title = title;
  }

  public void updateAuthor(String author) {

    this.author = author;
  }

  public void updateIsbn(Long isbn) {

    this.isbn=isbn;
  }



  public String getPublishedDate() {

    return publishedDate;
  }


  public void setPublishedDate(String publishedDate) {
    this.publishedDate = publishedDate;
  }

  public boolean isBorrowed() {

    return this.isBorrowed;
  }
}



