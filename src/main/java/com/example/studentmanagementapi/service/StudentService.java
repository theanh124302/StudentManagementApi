package com.example.studentmanagementapi.service;

import com.example.studentmanagementapi.dao.AddStudent;
import com.example.studentmanagementapi.dto.Student;
import com.example.studentmanagementapi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    // Implement business logic for your menu options here

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student saveOrUpdateStudent(AddStudent addStudent) {
        Student student = new Student();
        student.setName(addStudent.getName());
        student.setAge(addStudent.getAge());
        student.setGender(addStudent.getGender());
        student.setMathScore(addStudent.getMathScore());
        student.setPhysicsScore(addStudent.getPhysicsScore());
        student.setChemistryScore(addStudent.getChemistryScore());
        double averageScore = (student.getMathScore()+student.getPhysicsScore()+student.getChemistryScore())/3;
        student.setAverageScore(averageScore);
        if (averageScore >= 8) {
            student.setAcademicPerformance("Excellent");
        } else if (averageScore >= 6.5) {
            student.setAcademicPerformance("Good");
        } else if (averageScore >= 5) {
            student.setAcademicPerformance("Average");
        } else {
            student.setAcademicPerformance("Weak");
        }
        return studentRepository.save(student);
    }

    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> findStudentsByName(String name) {
        return studentRepository.findByName(name);
    }

    public List<Student> sortByGPA() {
        List<Student> students = studentRepository.findAll();

        // Sắp xếp danh sách sinh viên theo GPA giảm dần
        students.sort(Comparator.comparingDouble(Student::getAverageScore).reversed());

        return students;
    }

    public List<Student> sortByName() {
        List<Student> students = studentRepository.findAll();

        // Sắp xếp danh sách sinh viên theo tên
        students.sort(Comparator.comparing(Student::getName));

        return students;
    }
}
