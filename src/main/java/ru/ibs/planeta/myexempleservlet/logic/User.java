package ru.ibs.planeta.myexempleservlet.logic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private String name;
    private String surname;
    private double salary;
}
