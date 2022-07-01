package com.example.security.student;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagerController {
    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1, "Hà Gia Kính"),
            new Student(2, "Nguyễn Thị Lan Anh"),
            new Student(3, "Đỗ Nhật Đức"),
            new Student(4, "Nguyễn Ngọc Hiếu")
    );

    @GetMapping
    public List<Student> getAllStudents(){
        System.out.println("List student");
        return STUDENTS;
    }

    @PostMapping
    public void newStudent(@RequestBody Student student){
        System.out.println("Create student");
        if (student != null) {
            student.setId(STUDENTS.get(STUDENTS.size()-1).getId()+1);
            System.out.println(student);
        }
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Integer studentId){
        System.out.println("Delete student");
        STUDENTS.removeIf(student -> student.getId().equals(studentId));
    }

    @PutMapping
    public void updateStudent(@RequestBody Student student){
        System.out.println("Update student");
        Student stu = STUDENTS.stream()
                .filter(item -> item.getId().equals(student.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Not found student"));
        stu.setName(student.getName());
    }
}
