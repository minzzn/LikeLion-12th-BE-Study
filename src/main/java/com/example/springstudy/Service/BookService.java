package com.example.springstudy.Service;

import com.example.springstudy.DTO.BookDTO;

import java.util.List;

public interface BookService {


  //엔티티 -> DTO


  //엔티티 -> DTO
  BookDTO create(BookDTO bookDTO);

  public BookDTO read(Long id);

  void update(Long id, BookDTO bookDTO);

  public String delete(Long id);

  List<BookDTO> readAll();

}
