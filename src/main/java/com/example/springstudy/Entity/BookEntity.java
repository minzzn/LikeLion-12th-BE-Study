package com.example.springstudy.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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
  private LocalDate publishedDate;
  private boolean isBorrowed;
  private int currentLoans = 0;
  private static final int MAX_LOANS = 3;

  @Builder
  public BookEntity(String title, String author, Long isbn, LocalDate publishedDate) {
    this.title = title;
    this.author = author;
    this.isbn = isbn;
    this.publishedDate = publishedDate;
  }

  public void updateTitle(String title) {
    this.title = title;
  }

  public void updateAuthor(String author) {
    this.author = author;
  }

  public void updateIsbn(Long isbn) {
    this.isbn = isbn;
  }

  public boolean isBorrowed() {
    return this.isBorrowed;
  }

  public int getCurrentLoans() {
    return currentLoans;
  }

  public void setCurrentLoans(int currentLoans) {
    this.currentLoans = currentLoans;
  }

  public int getMaxLoans() {
    return MAX_LOANS;
  }
}
