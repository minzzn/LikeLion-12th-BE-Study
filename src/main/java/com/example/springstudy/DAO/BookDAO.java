package com.example.springstudy.DAO;

import com.example.springstudy.DTO.BookDTO;
import com.example.springstudy.Entity.BookEntity;

import java.awt.print.Book;
import java.util.List;

public interface BookDAO {
  BookEntity create(BookEntity bookEntity);

  public BookEntity read(Long id);

  void update(Long id, BookEntity bookEntity);

  public String delete(Long id);

  List<BookEntity> readAll();

}
