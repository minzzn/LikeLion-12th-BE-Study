package org.example.springstudy.DTO;

import lombok.Data;

@Data
public class UserDTO {
    private String name;
    private String email;
    private String address;
    private TeamResponseDTO team;
}
