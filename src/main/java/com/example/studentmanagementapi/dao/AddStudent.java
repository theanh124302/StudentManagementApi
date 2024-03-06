package com.example.studentmanagementapi.dao;

import lombok.Data;

@Data
public class AddStudent {
    private String name;
    private String gender;
    private int age;
    private double mathScore;
    private double physicsScore;
    private double chemistryScore;
}
