package com.example.springstudy.DAO;



import com.example.springstudy.DTO.BookDTO;
import com.example.springstudy.Entity.BookEntity;
import com.example.springstudy.Entity.UserEntity;
import com.example.springstudy.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Repository
public class BookDAOImpl implements BookDAO{
  private final BookRepository bookRepository;

  @Autowired
  public BookDAOImpl(BookRepository bookRepository) {

    this.bookRepository = bookRepository;
  }


  public BookEntity create(BookEntity bookEntity) {
    if (bookEntity.getPublishedDate() == null) {
      bookEntity.setPublishedDate(getCurrentDate());
    }
    bookRepository.save(bookEntity);
    return bookEntity;
  }
  private String getCurrentDate() {
    LocalDate today = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    return today.format(formatter);
  }
  @Override
  public BookEntity read(Long id) {
    BookEntity bookEntity = bookRepository.getReferenceById(id);
    return bookEntity;
  }

  @Override
  public void update(Long id, BookEntity bookEntity) {
    BookEntity bookEntity1 = bookRepository.getReferenceById(id);

    bookEntity1.updateTitle(bookEntity.getTitle());
    bookEntity1.updateAuthor(bookEntity.getAuthor());
    bookEntity1.updateIsbn(bookEntity1.getIsbn());

    bookRepository.save(bookEntity1);

  }

  @Override
  public void delete(Long id) {

    bookRepository.deleteById(id);
  }

  @Override
  public List<BookEntity> readAll() {
    return bookRepository.findAll();
  }
}
