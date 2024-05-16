package org.example.springstudy.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Getter
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team", cascade = CascadeType.REMOVE)
    private List<User> users = new ArrayList<User>();


    @Builder
    public Team(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addUser(User user) {
        users.add(user);
        user.updateTeam(this);
    }
}