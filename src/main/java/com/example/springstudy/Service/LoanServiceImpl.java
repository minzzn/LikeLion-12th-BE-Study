package com.example.springstudy.Service;

import com.example.springstudy.DTO.BookDTO;
import com.example.springstudy.DTO.LoanDTO;
import com.example.springstudy.Entity.BookEntity;
import com.example.springstudy.Entity.LoanEntity;
import com.example.springstudy.Entity.UserEntity;
import com.example.springstudy.Repository.BookRepository;
import com.example.springstudy.Repository.LoanRepository;
import com.example.springstudy.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoanServiceImpl implements LoanService {

  private final LoanRepository loanRepository;
  private final BookRepository bookRepository;
  private final UserRepository userRepository;

  @Autowired
  public LoanServiceImpl(LoanRepository loanRepository, BookRepository bookRepository, UserRepository userRepository) {
    this.loanRepository = loanRepository;
    this.bookRepository = bookRepository;
    this.userRepository = userRepository;
  }

  @Override
//  public LoanDTO createLoan(LoanDTO loanDTO) {
//    BookEntity bookEntity = bookRepository.getReferenceById(loanDTO.getBook());
//    UserEntity userEntity = userRepository.getReferenceById(loanDTO.getUser());
//    LocalDateTime now = LocalDateTime.now();
//    LoanEntity loanEntity = LoanEntity.builder()
//        .book(bookEntity)
//        .user(userEntity)
//        .loanDate(now)
//        .returnDate(now.plusDays(7))
//        .build();
//    loanRepository.save(loanEntity);
//    return null;
//  }
  public LoanDTO createLoan(LoanDTO loanDTO) {
    // 대출 가능 여부를 체크
    if (isBookAvailableForLoan(loanDTO.getUser(), loanDTO.getBook())) {
      // 대출 가능한 경우 대출 생성 로직 실행
      BookEntity bookEntity = bookRepository.getReferenceById(loanDTO.getBook());
      UserEntity  userEntity = userRepository.getReferenceById(loanDTO.getUser());
      LocalDateTime now = LocalDateTime.now();
      LoanEntity loanEntity = LoanEntity.builder()
          .book(bookEntity)
          .user(userEntity)
          .loanDate(now)
          .returnDate(now.plusDays(7))
          .build();
      loanRepository.save(loanEntity);
      // 대출이 성공적으로 생성되었음을 나타내는 메시지를 반환하거나, 생성된 대출 정보를 반환할 수 있음
      return mapLoanEntityToDTO(loanEntity); // 예시로 LoanEntity를 LoanDTO로 매핑하여 반환
    } else {
      // 대출 불가능한 경우 에러 처리 로직 실행
      throw new IllegalStateException("이미 동일한 도서를 대출한 이력이 있습니다.");
    }
  }

  private LoanDTO mapLoanEntityToDTO(LoanEntity loanEntity) {
    LoanDTO loanDTO = new LoanDTO();
    loanDTO.setBook(loanEntity.getBook().getId());
    loanDTO.setUser(loanEntity.getUser().getId());
    loanDTO.setLoanDate(loanEntity.getLoanDate());
    loanDTO.setReturnDate(loanEntity.getReturnDate());
    return loanDTO;
  }

  private boolean isBookAvailableForLoan(Long userId, Long bookId) {
    // 사용자가 동일한 도서를 이미 대출한 이력이 있는지 확인
    boolean isBookAlreadyLoaned = loanRepository.existsByUserIdAndBookId(userId, bookId);

    // 동일한 도서를 이미 대출한 경우 대출 불가
    if (isBookAlreadyLoaned) {
      return false;
    }

    // 동일한 도서를 대출한 이력이 없는 경우 대출 가능
    return true;
  }

  @Override
  public List<LoanDTO> readAll() {
    List<LoanEntity> loanEntities = loanRepository.findAll();
    List<LoanDTO> loanDTOs = new ArrayList<>();

    for (LoanEntity loanEntity : loanEntities) {
      LoanDTO loanDTO = new LoanDTO();
      loanDTO.setBook(loanEntity.getBook().getId());
      loanDTO.setUser(loanEntity.getUser().getId());
      loanDTO.setLoanDate(loanEntity.getLoanDate());
      loanDTO.setReturnDate(loanEntity.getReturnDate());
      loanDTOs.add(loanDTO);
    }

    return loanDTOs;
  }

  @Override
  public List<BookDTO> getBooksByUserId(Long userId) {
    UserEntity user = userRepository.getReferenceById(userId);

    List<LoanEntity> loans = user.getLoans();
    List<BookDTO> bookDTOs = new ArrayList<>();

    // 각 대출 기록에 대해
    for (LoanEntity loanEntity : loans) {
      // 대출된 책 정보를 가져옵니다.
      BookEntity book = loanEntity.getBook();
      if (book != null) {
        // 새로운 BookDTO 객체를 생성하고 책 정보를 설정합니다.
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setPublishedDate(book.getPublishedDate());
        // 리스트에 추가합니다.
        bookDTOs.add(bookDTO);
      }
    }

    return bookDTOs;
  }
  public boolean checkAvailability(Long userId, Long bookId) {
    // 한 사용자가 동일한 도서를 대출한 이력이 있는지 확인
    List<LoanEntity> loans = loanRepository.findByUserIdAndBookIdAndReturnedFalse(userId, bookId);
    return loans.isEmpty();
  }

  public boolean returnBook(Long id) {
    // 대출 ID로 대출 정보를 조회
    Optional<LoanEntity> loanOptional = loanRepository.findById(id);
    if (loanOptional.isPresent()) {
      LoanEntity loanEntity = loanOptional.get();

      // 책 반납 전에 대출 가능 여부를 체크
      if (isBookReturned(loanEntity.getBook())) {
        // 대출 가능한 경우 책을 반납하고 대출 상태를 업데이트
        loanRepository.delete(loanEntity);

        // 책 반납 후에 대출 가능한 상태로 업데이트
        BookEntity bookEntity = loanEntity.getBook();
        bookEntity.setBorrowed(false);
        bookRepository.save(bookEntity);

        return true;
      } else {
        // 대출 불가능한 경우 반납 실패 처리
        return false;
      }
    } else {
      // 대출 정보가 없는 경우 반납 실패 처리
      return false;
    }
  }


  private boolean isBookReturned(BookEntity bookEntity) {
    // 책이 이미 반납되었는지 여부를 확인하여 반환
    return !bookEntity.isBorrowed();
  }
}
