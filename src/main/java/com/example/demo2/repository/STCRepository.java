package com.example.demo2.repository;

import com.example.demo2.model.StudentToCourse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface STCRepository extends CrudRepository<StudentToCourse, Long>  {
}
