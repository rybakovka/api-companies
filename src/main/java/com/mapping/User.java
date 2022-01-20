package com.mapping;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class User {
    @NonNull
    private String email;
    @NonNull
    private String name;
    @NonNull
    private List<Integer> tasks;
    @NonNull
    private List<Integer> companies;
    private String hobby;
    private String adres;
    private String name1;
    private String surname1;
    private String fathername1;
    private String cat;
    private String dog;
    private String parrot;
    private String cavy;
    private String hamster;
    private String squirrel;
    private String phone;
    private String inn;
    private String gender;
    private String birthday;
    private String date_start;
}
