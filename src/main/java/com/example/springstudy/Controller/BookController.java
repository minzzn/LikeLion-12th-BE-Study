package com.example.springstudy.Controller;

import com.example.springstudy.DTO.BookDTO;
import com.example.springstudy.Entity.BookEntity;
import com.example.springstudy.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// https://localhost:port/books
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
  public List<BookEntity> readAll() {

    return bookService.readAll();
  }

  @GetMapping("/read/{id}")
  public BookEntity read(@PathVariable(value = "id") Long id) {

    return bookService.read(id);
  }

  @PutMapping(value = "/update/{id}")
  public void update(@PathVariable(value = "id") Long id, @RequestBody BookDTO bookDTO) {
    bookService.update(id, bookDTO);
  }

  @DeleteMapping(value = "/delete/{id}")
  public void delete(@PathVariable(value = "id") Long id) {
    bookService.delete(id);
  }
}