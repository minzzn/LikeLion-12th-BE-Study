package org.example.springstudy.Controller;

import org.example.springstudy.DTO.UserDTO;
import org.example.springstudy.DTO.UserResponseDTO;
import org.example.springstudy.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/create")
    public UserResponseDTO createUser(@RequestBody UserDTO userDTO) {
        logger.info("UserController-CreateUser [Data]: " + userDTO);
        return userService.createUser(userDTO);
    }

    @PostMapping(value = "/{userid}/{teamid}")
    public UserResponseDTO setTeam(@PathVariable(value = "userid") Long userId,
                                   @PathVariable(value = "teamid") Long teamId) {
        return userService.setTeam(userId, teamId);
    }

    @GetMapping(value = "/{id}")
    public UserResponseDTO readUser(@PathVariable(value = "id") Long id){
        logger.info("UserController-ReadUser [Data]: " + id);
        return userService.readUser(id);
    }

    @PutMapping(value = "/update/{id}")
    public UserResponseDTO updateUser(@PathVariable(value = "id") Long id, @RequestBody UserDTO userDTO) {
        logger.info("UserController-UpdateUser [Data]: " + userDTO);
        return userService.updateUser(id, userDTO);
    }

    @DeleteMapping(value = "/{id}")
    public String deleteUser(@PathVariable(value = "id") Long id){
        logger.info("UserController-DeleteUser [Data]: " + id);
        return userService.deleteUser(id);
    }
}