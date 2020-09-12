package com.example.demo2.model;

import javax.persistence.*;

@Entity
@Table(name="student_to_course")
public class StudentToCourse {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) @Column(name="id")
    private long id;
    @Column(name="course_id")
    private long courseId;

    @Column(name="student_id")
    private long studentId;

    public StudentToCourse() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }
}
