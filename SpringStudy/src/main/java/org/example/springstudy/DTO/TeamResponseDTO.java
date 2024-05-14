package org.example.springstudy.DTO;

import lombok.Builder;
import lombok.Data;

@Data
public class TeamResponseDTO {
    private Long id;
    private String name;

    @Builder
    public TeamResponseDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
