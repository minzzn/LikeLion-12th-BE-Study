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
import java.util.stream.Collectors;

@Service
public class LoanServiceImpl implements LoanService {
  private static final int MAX_LOANS_PER_BOOK = 3;
  private static final int MAX_LOANS_PER_USER = 3;

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
  public LoanDTO createLoan(LoanDTO loanDTO) {
    Long bookId = loanDTO.getBook();
    Long userId = loanDTO.getUser();

    if (isBookAlreadyLoanedByUser(userId, bookId)) {
      throw new AlreadyLoanedException("중복 대출 불가");
    }

    if (!isBookAvailableForLoan(bookId)) {
      throw new MaxLoansExceededException("대출 가능 인원 초과");
    }

    if (hasUserExceededMaxLoans(userId)) {
      throw new MaxLoansExceededException("최대 대출 권수 초과");
    }


    BookEntity bookEntity = bookRepository.getReferenceById(bookId);
    UserEntity userEntity = userRepository.getReferenceById(userId);
    LocalDateTime now = LocalDateTime.now();
    LoanEntity loanEntity = LoanEntity.builder()
        .book(bookEntity)
        .user(userEntity)
        .loanDate(now)
        .returnDate(now.plusDays(7))
        .build();
    loanRepository.save(loanEntity);

    bookEntity.setCurrentLoans(bookEntity.getCurrentLoans() + 1);
    userEntity.setCurrentLoans(userEntity.getCurrentLoans() + 1);
    bookRepository.save(bookEntity);
    userRepository.save(userEntity);

    return mapLoanEntityToDTO(loanEntity);
  }
  public class AlreadyLoanedException extends RuntimeException {
    public AlreadyLoanedException(String message) {
      super(message);
    }
  }

  public class MaxLoansExceededException extends RuntimeException {
    public MaxLoansExceededException(String message) {
      super(message);
    }
  }

  @Override
  public List<LoanDTO> getLoansByUser(Long userId) {
    List<LoanEntity> loanEntities = loanRepository.findByUserId(userId);
    return loanEntities.stream()
        .map(this::mapLoanEntityToDTO)
        .collect(Collectors.toList());
  }

  private boolean isBookAlreadyLoanedByUser(Long userId, Long bookId) {
    return loanRepository.existsByUserIdAndBookId(userId, bookId);
  }

  private boolean isBookAvailableForLoan(Long bookId) {
    BookEntity bookEntity = bookRepository.findById(bookId)
        .orElseThrow(() -> new RuntimeException("도서를 찾을 수 없습니다."));
    return bookEntity.getCurrentLoans() < bookEntity.getMaxLoans();
  }

  private boolean hasUserExceededMaxLoans(Long userId) {
    int currentLoans = loanRepository.countByUserId(userId);
    return currentLoans >= MAX_LOANS_PER_USER;
  }

  private LoanDTO mapLoanEntityToDTO(LoanEntity loanEntity) {
    return LoanDTO.builder()
        .book(loanEntity.getBook().getId())
        .user(loanEntity.getUser().getId())
        .loanDate(loanEntity.getLoanDate())
        .returnDate(loanEntity.getReturnDate())
        .build();

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


  public boolean returnBook(Long id) {
    // 대출 ID로 대출 정보를 조회
    Optional<LoanEntity> loanOptional = loanRepository.findById(id);
    if (loanOptional.isPresent()) {
      LoanEntity loanEntity = loanOptional.get();
      BookEntity bookEntity = loanEntity.getBook();
      UserEntity userEntity = loanEntity.getUser();

      // 책 반납 전에 대출 가능 여부를 체크
      if (isBookReturned(loanEntity.getBook())) {

        loanRepository.delete(loanEntity);

        // 책 반납 후에 대출 가능한 상태로 업데이트
        bookEntity.setCurrentLoans(bookEntity.getCurrentLoans() - 1);
        userEntity.setCurrentLoans(userEntity.getCurrentLoans() - 1);
        bookRepository.save(bookEntity);
        userRepository.save(userEntity);

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
