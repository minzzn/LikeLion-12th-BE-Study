package com.example.springstudy.Controller;

import com.example.springstudy.DTO.BookDTO;
import com.example.springstudy.DTO.LoanDTO;
import com.example.springstudy.Entity.LoanEntity;
import com.example.springstudy.Service.BookService;
import com.example.springstudy.Service.LoanService;
import com.example.springstudy.Service.LoanServiceImpl;
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
  public ResponseEntity<LoanDTO> createLoan(@RequestBody LoanDTO loanDTO) {
    try {
      LoanDTO createdLoan = loanService.createLoan(loanDTO);
      return ResponseEntity.ok(createdLoan);
    } catch (LoanServiceImpl.AlreadyLoanedException e) {
      return ResponseEntity.badRequest().body(new LoanDTO("중복 대출 불가"));
    } catch (LoanServiceImpl.MaxLoansExceededException e) {
      return ResponseEntity.status(409).body(new LoanDTO("최대 대출 권수 초과"));
    } catch (Exception e) {
      return ResponseEntity.status(500).body(new LoanDTO("서버 오류"));
    }
  }

  @GetMapping("/read")
  public List<LoanDTO> readAll() {

    return loanService.readAll();
  }


  @GetMapping("/users/{userId}/loans")
  public List<BookDTO> getLoansByUserId(@PathVariable(value = "userId") Long userId) {
    return loanService.getBooksByUserId(userId);
  }

  @PutMapping("/{id}/return")
  public ResponseEntity<String> returnBook(@PathVariable(value = "id") Long id) {
    try {
      boolean returned = loanService.returnBook(id);
      if (returned) {
        return ResponseEntity.ok("반납 성공");
      } else {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("해당 책이 대출 중이 아님");
      }
    } catch (DataIntegrityViolationException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("반납 중 오류");
    }
  }



}
