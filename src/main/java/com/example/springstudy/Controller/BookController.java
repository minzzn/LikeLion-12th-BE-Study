package com.example.springstudy.Controller;

import com.example.springstudy.DTO.BookDTO;
import com.example.springstudy.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

  private BookDTO book;
  private final BookService bookService;

  @Autowired
  public BookController(BookService bookService) {

    this.bookService = bookService;
  }

  @PostMapping(value = "/create")
  public BookDTO create(@RequestBody BookDTO book) {

    this.book = book;
    bookService.create(book);
    return this.book;
  }


  @GetMapping("/read")
  public List<BookDTO> readAll() {

    return bookService.readAll();
  }

  @GetMapping("/read/{id}")
  public BookDTO read(@PathVariable(value = "id") Long id) {

    return bookService.read(id);
  }

  @PutMapping(value = "/update/{id}")
  public void update(@PathVariable(value = "id") Long id, @RequestBody BookDTO bookDTO) {
    bookService.update(id, bookDTO);
  }

  @DeleteMapping(value = "/delete/{id}")
  public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
    try {
      boolean deleted = Boolean.parseBoolean(bookService.delete(id));
      if (deleted) {
        return ResponseEntity.ok("책이 삭제됨");
      } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID의 책을 찾을 수 없음");
      }
    } catch (DataIntegrityViolationException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("대출 중 삭제 불가");
    }
  }

}