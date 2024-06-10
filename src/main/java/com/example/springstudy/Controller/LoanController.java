package com.example.springstudy.Controller;

import com.example.springstudy.DTO.BookDTO;
import com.example.springstudy.DTO.LoanDTO;
import com.example.springstudy.Entity.LoanEntity;
import com.example.springstudy.Service.BookService;
import com.example.springstudy.Service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/loans")
public class LoanController {
  private LoanService loanService;
  private BookService bookService;
  private LoanDTO loan;


  @Autowired
  public LoanController(LoanService loanService, BookService bookService) {

    this.loanService = loanService;
    this.bookService=bookService;
  }


  @PostMapping("/create")
  public LoanDTO createLoan(@RequestBody LoanDTO loanDTO) {
    this.loan=loanDTO;
    loanService.createLoan(loanDTO);
    return this.loan;
  }


  @GetMapping("/read")
  public List<LoanDTO> readAll() {

    return loanService.readAll();
  }


  //대출 사용자 조회
  @GetMapping("/users/{userId}/loans")
  public List<BookDTO> getLoansByUserId(@PathVariable(value = "userId") Long userId) {
    return loanService.getBooksByUserId(userId);
  }

  @PutMapping("/{id}/return")
  public ResponseEntity<String> returnBook(@PathVariable(value = "id") Long id) {
    try {
      // 책을 반납하려고 시도
      boolean returned = loanService.returnBook(id);
      if (returned) {
        return ResponseEntity.ok("책이 성공적으로 반납되었습니다.");
      } else {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("해당 ID의 책이 대출 중이 아니거나 반납할 수 없습니다.");
      }
    } catch (DataIntegrityViolationException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("책 반납 중 오류가 발생했습니다.");
    }
  }



}
