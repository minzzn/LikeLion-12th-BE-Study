package com.example.springstudy.DAO;



import com.example.springstudy.DTO.BookDTO;
import com.example.springstudy.Entity.BookEntity;
import com.example.springstudy.Entity.LoanEntity;
import com.example.springstudy.Repository.BookRepository;
import com.example.springstudy.Repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
public class BookDAOImpl implements BookDAO{
  private final BookRepository bookRepository;
  private final LoanRepository loanRepository;

  @Autowired
  public BookDAOImpl(BookRepository bookRepository, LoanRepository loanRepository) {

    this.bookRepository = bookRepository;
    this.loanRepository = loanRepository;
  }


  public BookEntity create(BookEntity bookEntity) {

    bookRepository.save(bookEntity);
    return bookEntity;
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
  public String delete(Long id) {
    Optional<BookEntity> bookEntityOptional = bookRepository.findById(id);
    if (bookEntityOptional.isPresent()) {
      BookEntity bookEntity = bookEntityOptional.get();
      if (bookEntity.isBorrowed()) {
        return "책이 현재 대출 중입니다."; // 대출 중인 책은 삭제할 수 없음
      }
      bookRepository.delete(bookEntity);
      return "책이 삭제되었습니다."; // 삭제 성공
    }
    return "해당 ID의 책을 찾을 수 없습니다."; // 책이 없는 경우
  }



  @Override
  public List<BookEntity> readAll() {
    List<BookEntity> bookEntities = bookRepository.findAll();
    return bookEntities;
  }

}
