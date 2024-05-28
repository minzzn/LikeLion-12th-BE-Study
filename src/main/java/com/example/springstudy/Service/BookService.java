package com.example.springstudy.Service;

import com.example.springstudy.DTO.BookDTO;
import com.example.springstudy.DTO.UserDTO;
import com.example.springstudy.Entity.BookEntity;
import com.example.springstudy.Entity.UserEntity;

import java.util.List;

public interface BookService {


  //엔티티 -> DTO


  //엔티티 -> DTO
  BookDTO create(BookDTO bookDTO);

  public BookEntity read(Long id);

  void update(Long id, BookDTO bookDTO);

  public void delete(Long id);

  List<BookEntity> readAll();

}
