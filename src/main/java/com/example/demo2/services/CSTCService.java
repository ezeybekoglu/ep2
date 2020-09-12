package com.example.demo2.services;


import com.example.demo2.model.CustomSTC;

import com.example.demo2.repository.CustomSTCRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CSTCService implements ICSTCService {

    @Autowired
    private CustomSTCRepo repository;

    @Override
    public List<CustomSTC> findAll() {

        var asd = (List<CustomSTC>) repository.findAll();

        return asd;
    }
}
