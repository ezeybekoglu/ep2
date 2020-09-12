package com.example.demo2.model;

import javax.persistence.*;

@Entity
@Table(name="student_to_course")
public class CustomSTC {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="course_id")
    private Course course;

    @OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="student_id")
    private Student student;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
