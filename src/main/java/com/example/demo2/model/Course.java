package com.example.demo2.model;

import javax.persistence.*;

@Entity
@Table(name="course")
public class Course {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="name", nullable = false)
    private String name;

    public Course() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
