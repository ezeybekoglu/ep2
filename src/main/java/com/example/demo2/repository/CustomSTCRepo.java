package com.example.demo2.repository;

import com.example.demo2.model.CustomSTC;
import org.springframework.data.repository.CrudRepository;

public interface CustomSTCRepo extends CrudRepository<CustomSTC, Long> {
  boolean existsByStudent_idAndCourse_id(Long student,Long course);
}
