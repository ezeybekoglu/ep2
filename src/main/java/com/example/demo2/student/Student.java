package com.example.demo2.student;



import javax.persistence.*;

@Entity
@Table(name= "student")
public class Student {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) @Column(name="id")
    private long id;
    @Column(name="name", nullable = false)
    private String name;




    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }



    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


}
