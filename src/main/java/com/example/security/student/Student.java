package com.example.security.student;

public class Student {
    private final Integer Id;
    private final String name;

    public Student(Integer id, String name) {
        Id = id;
        this.name = name;
    }

    public Integer getId() {
        return Id;
    }

    public String getName() {
        return name;
    }
}
