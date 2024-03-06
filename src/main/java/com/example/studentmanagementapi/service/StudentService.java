package com.example.studentmanagementapi.service;

import com.example.studentmanagementapi.dao.AddStudent;
import com.example.studentmanagementapi.dto.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    public List<Student> getAllStudents();
    public Optional<Student> getStudentById(Long id);
    public Student saveOrUpdateStudent(AddStudent addStudent);
    public void deleteStudentById(Long id);
    public List<Student> findStudentsByName(String name);
    public List<Student> sortByGPA();
    public List<Student> sortByName();
}
