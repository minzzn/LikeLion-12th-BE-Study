package com.example.springstudy.Service;


import com.example.springstudy.DAO.BookDAO;
import com.example.springstudy.DTO.BookDTO;
import com.example.springstudy.Entity.BookEntity;
import com.example.springstudy.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
  public BookDTO read(Long id) {
    BookEntity bookEntity = bookRepository.getReferenceById(id);
    return convertToDTO(bookEntity);
  }

  private BookDTO convertToDTO(BookEntity bookEntity) {
    BookDTO bookDTO = new BookDTO();
    bookDTO.setIsbn(bookEntity.getIsbn());
    bookDTO.setTitle(bookEntity.getTitle());
    bookDTO.setAuthor(bookEntity.getAuthor());
    return bookDTO;
  }



  @Override
  public void update(Long id, BookDTO bookDTO) {
    BookEntity bookEntity = BookEntity.builder()
        .title(bookDTO.getTitle())
        .author(bookDTO.getAuthor())
        .isbn(bookDTO.getIsbn())
        .publishedDate(bookDTO.getPublishedDate())
        .build();
    bookDAO.update(id, bookEntity);
  }

  @Override
  public String delete(Long id) {
    bookDAO.delete(id);
    return null;
  }

  @Override
  public List<BookDTO> readAll() {
    List<BookEntity> bookEntities = bookRepository.findAll();
    return bookEntities.stream()
        .map(this::convertToDTO)
        .collect(Collectors.toList());
  }
}
