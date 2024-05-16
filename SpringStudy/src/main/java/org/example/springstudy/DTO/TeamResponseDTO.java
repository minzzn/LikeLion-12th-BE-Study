package org.example.springstudy.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class TeamResponseDTO {
    private Long id;
    private String name;
    private List<UserResponseDTO> users;

    @Builder
    public TeamResponseDTO(Long id, String name, List<UserResponseDTO> users) {
        this.id = id;
        this.name = name;
        this.users = users;
    }
}
