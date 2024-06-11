package com.example.springstudy.DAO;

import com.example.springstudy.Repository.LoanRepository;

public class LoanDAOImpl implements  LoanDAO{


  private final LoanRepository loanRepository;

  public LoanDAOImpl(LoanRepository loanRepository) {
    this.loanRepository = loanRepository;
  }


  public boolean isBookAvailableForLoan(Long userId, Long bookId) {
    // 사용자가 동일한 도서를 이미 대출한 이력이 있는지 확인
    boolean isBookAlreadyLoaned = loanRepository.existsByUserIdAndBookId(userId, bookId);

    // 동일한 도서를 이미 대출한 경우 대출 불가
    if (isBookAlreadyLoaned) {
      return false;
    }

    // 동일한 도서를 대출한 이력이 없는 경우 대출 가능
    return true;
  }
}
