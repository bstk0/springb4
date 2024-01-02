package br.com.dbengine.springb4.entity;

import java.util.*;

//@Entity
//@Table(name = "student")
public class Student {

    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String major;

    public Student(){}

    public Student(int id, String firstName, String lastName, int age, String major) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.major = major;
    }

    //getters and setters for all fields

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", major='" + major + '\'' +
                '}';
    }
}