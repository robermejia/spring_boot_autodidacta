package com.robermejia.crud_alumno.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.robermejia.crud_alumno.model.Student;

@RestController
@RequestMapping("/alumnos")

public class StudentController {

    private List<Student> students = new ArrayList<>(Arrays.asList(
        new Student(1,"Roberto", 25,"roberto@gmail.com", "Programación"),
        new Student(2,"Manuel", 23,"manuel@gmail.com", "Redes"),
        new Student(3,"Daniel", 20,"daniel@gmail.com", "Algoritmos"),
        new Student(4,"Miguel", 30,"miguel@gmail.com", "Redes 2"),
        new Student(5,"Axel", 19,"axel@gmail.com", "Base de datos")
        ) 
    );

    @GetMapping
    public List<Student> getStudent(){
        return students;
    }

    @GetMapping("/{name}")
    public Student getStudent(@PathVariable String name) {
        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name)) {
                return s;
            }
        }
        return null;
    }

    @PostMapping
    public Student postStudent(@RequestBody Student student) {
        students.add(student);
        return student;
    }

    @PutMapping
    public Student putStudent(@RequestBody Student student) {
    for (Student s : students) {
            if (s.getId() == student.getId()) {
                s.setName(student.getName());
                s.setAge(student.getAge());
                s.setEmail(student.getEmail());
                s.setCourse(student.getCourse());
                return s;          }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Student deleteStudent(@PathVariable int id){
        for (Student s : students) {
            if (s.getId() == id) {
                students.remove(s);
                return s;
            }
        }
        return null;
    }
    
}
