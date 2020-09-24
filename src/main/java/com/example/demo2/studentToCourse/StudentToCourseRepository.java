package com.example.demo2.studentToCourse;

import org.springframework.data.repository.CrudRepository;

public interface StudentToCourseRepository extends CrudRepository<StudentToCourse, Long> {
  boolean existsByCourse_idAndStudent_id(Long course,Long student);
}
