package com.example.springstudy.Service;


import com.example.springstudy.DTO.BookDTO;
import com.example.springstudy.DTO.LoanDTO;

import java.util.List;

public interface LoanService {

  LoanDTO createLoan(LoanDTO loanDTO);

  List<LoanDTO> getLoansByUser(Long userId);

  List<LoanDTO> readAll();



  List<BookDTO> getBooksByUserId(Long userId);

  boolean returnBook(Long id);




}