package br.com.dbengine.springb4.interfaces;

import br.com.dbengine.springb4.entity.Student;

import java.util.*;

public interface StudentDAO {

    //method to create a new student
    public Student createStudent(Student student);

    //method to get a student by its id
    public Student getStudentById(int id);

    //method to get all students
    public List<Student> getAllStudents();

    //method to update a student
    public Student updateStudent(Student student);

    //method to delete a student
    public void deleteStudent(int id);
}
