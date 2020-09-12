package com.example.demo2.services;

import com.example.demo2.model.Student;
import com.example.demo2.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentService implements IStudentService {

    @Autowired
    private StudentRepository repository;

    @Override
    public List<Student> findAll() {

        var students = (List<Student>) repository.findAll();

        return students;
    }
    public Student findByName(String name) {

        Student student = (Student) repository.findByName(name);

        return student;
    }


}
