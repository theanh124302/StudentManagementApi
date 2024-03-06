package com.example.studentmanagementapi.dto;
import lombok.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name ="student")
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String gender;
    private int age;
    private double mathScore;
    private double physicsScore;
    private double chemistryScore;
    private double averageScore;
    private String academicPerformance;
}

