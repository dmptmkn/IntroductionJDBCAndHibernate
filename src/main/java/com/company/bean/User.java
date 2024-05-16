package com.company.bean;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class User {
    private long id;
    private String name;
    private String lastName;
    private byte age;
}
