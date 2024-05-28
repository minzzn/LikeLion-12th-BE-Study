package com.example.springstudy.Service;


import com.example.springstudy.DAO.BookDAO;
import com.example.springstudy.DAO.UserDAO;
import com.example.springstudy.DTO.BookDTO;
import com.example.springstudy.DTO.UserDTO;
import com.example.springstudy.Entity.BookEntity;
import com.example.springstudy.Entity.UserEntity;
import com.example.springstudy.Repository.BookRepository;
import com.example.springstudy.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{
  BookRepository bookRepository;
  private final BookDAO bookDAO;

  @Autowired
  public BookServiceImpl(BookRepository bookRepository, BookDAO bookDAO) {

    this.bookRepository = bookRepository;

    this.bookDAO = bookDAO;
  }

  @Override
  public BookDTO create(BookDTO bookDTO) {
    BookEntity bookEntity = BookEntity.builder()
        .title(bookDTO.getTitle())
        .author((bookDTO.getAuthor()))
        .isbn(bookDTO.getIsbn())
        .publishedDate(bookDTO.getPublishedDate())
        .build();
    bookDAO.create(bookEntity);
    return null;
  }

  @Override
  public BookEntity read(Long id) {

    BookEntity bookEntity = bookDAO.read(id);
    return toBookResponseDTO(bookEntity);
  }

  @Override
  public void update(Long id, BookDTO bookDTO) {
    BookEntity bookEntity = BookEntity.builder()
        .title(bookDTO.getTitle())
        .author(bookDTO.getAuthor())
        .isbn(bookDTO.getIsbn())
        .build();
    bookDAO.update(id, bookEntity);
  }

  @Override
  public void delete(Long id) {
    bookDAO.delete(id);

  }

  @Override
  public List<BookEntity> readAll() {
     return bookDAO.readAll();
  }

  public BookEntity tobookEntity(BookEntity bookEntity) {
    return BookEntity.builder()
        .title(bookEntity.getTitle())
        .author(bookEntity.getAuthor())
        .isbn(bookEntity.getIsbn())
        .build();
  }

  public BookEntity toBookResponseDTO(BookEntity bookEntity) {
    return BookEntity.builder()
        .title(bookEntity.getTitle())
        .author(bookEntity.getAuthor())
        .isbn(bookEntity.getIsbn())
        .publishedDate(bookEntity.getPublishedDate())
        .build();
  }

}
