package org.example.springstudy.DTO;

import lombok.Builder;
import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String address;
    private TeamResponseDTO team;

    @Builder
    public UserResponseDTO(Long id, String name, String email, String address, TeamResponseDTO team) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.team = team;
    }
}
