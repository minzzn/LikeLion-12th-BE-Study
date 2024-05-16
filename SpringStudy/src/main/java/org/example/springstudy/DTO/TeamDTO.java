package org.example.springstudy.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeamDTO {
    private Long id;
    private String name;

    @Builder
    public TeamDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
