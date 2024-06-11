package com.example.springstudy.Controller;

import com.example.springstudy.DTO.DTO;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/controller")
public class Controller {
  private DTO student;

  @PostMapping(value = "/create")
  public  String create(@RequestBody DTO dto){
    student=dto;
    return "success";
  }

  @GetMapping(value = "/read")
  public DTO get(){
    return student;
  }

  @PutMapping(value = "/update")
  public String update(@RequestBody DTO dto){
    student =dto;
    return "success";
  }
  @DeleteMapping(value = "/delete")
  public String Delete(){
    student=null;
    return "success";
  }
}



