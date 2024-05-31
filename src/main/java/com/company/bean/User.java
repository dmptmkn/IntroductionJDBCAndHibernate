package com.company.bean;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String lastname;

    @Column
    private byte age;

    public User(String name, String lastname, byte age) {
        this.name = name;
        this.lastname = lastname;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id && age == user.age && Objects.equals(name, user.name) && Objects.equals(lastname, user.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastname, age);
    }
}
