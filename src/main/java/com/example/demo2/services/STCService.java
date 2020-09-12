package com.example.demo2.services;

import com.example.demo2.model.StudentToCourse;
import com.example.demo2.repository.STCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class STCService implements ISTCService {

    @Autowired
    private STCRepository repository;

    @Override
    public List<StudentToCourse> findAll() {

        var std = (List<StudentToCourse>) repository.findAll();

        return std;
    }
}
