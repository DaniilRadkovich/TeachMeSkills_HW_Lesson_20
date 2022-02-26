package com.teachmeskills.lesson_20.task_1.helpers;

import com.teachmeskills.lesson_20.task_1.model.Student;

import java.util.List;

public interface StudentsInterface {
    boolean addStudent(Student student);

    List<Student> getStudentList();

    Student getStudentById(Integer id);

    Student updateStudent(Student student);

    boolean deleteStudent(Student student);

    boolean deleteStudentById(Integer id);

}
