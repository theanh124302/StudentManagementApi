package com.example.studentmanagementapi.controller;

import com.example.studentmanagementapi.dao.AddStudent;
import com.example.studentmanagementapi.dto.Student;
import com.example.studentmanagementapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    // Implement endpoints for your menu options here

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Optional<Student> getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

//    @PostMapping
//    public Student saveOrUpdateStudent(@RequestBody AddStudent student) {
//        return studentService.saveOrUpdateStudent(student);
//    }

    @PostMapping
    public ResponseEntity<?> saveOrUpdateStudent(@RequestBody AddStudent student) {
        // Kiểm tra giới tính
        if (!student.getGender().equalsIgnoreCase("male") && !student.getGender().equalsIgnoreCase("female")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid gender. Please enter 'male' or 'female'.");
        }

        // Kiểm tra tuổi
        if (student.getAge() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid age. Age must be greater than 0.");
        }

        // Kiểm tra điểm toán
        if (student.getMathScore() < 0 || student.getMathScore() > 10) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid math score. Score must be between 0 and 10.");
        }

        // Kiểm tra điểm vật lý
        if (student.getPhysicsScore() < 0 || student.getPhysicsScore() > 10) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid physics score. Score must be between 0 and 10.");
        }

        // Kiểm tra điểm hóa học
        if (student.getChemistryScore() < 0 || student.getChemistryScore() > 10) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid chemistry score. Score must be between 0 and 10.");
        }

        // Nếu thông tin sinh viên hợp lệ, lưu hoặc cập nhật và trả về sinh viên đã được lưu hoặc cập nhật
        Student savedStudent = studentService.saveOrUpdateStudent(student);
        return ResponseEntity.ok(savedStudent);
    }


    @DeleteMapping("/{id}")
    public void deleteStudentById(@PathVariable Long id) {
        studentService.deleteStudentById(id);
    }

    @GetMapping("/search")
    public List<Student> findStudentsByName(@RequestParam String name) {
        return studentService.findStudentsByName(name);
    }

    @GetMapping("/sort/gpa")
    public List<Student> sortByGPA() {
        return studentService.sortByGPA();
    }

    @GetMapping("/sort/name")
    public List<Student> sortByName() {
        return studentService.sortByName();
    }
}

