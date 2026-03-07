package org.example.test;

import org.example.model.Student;

public class Main {
    public static void main(String[] args) {
        Student student1 = new Student.Builder()
                .name("Артем")
                .gradebookNumber(12345)
                .grade(4.0)
                .build();

        System.out.println(student1.getName());
        System.out.println(student1.toString());
        System.out.println(student1.getGrade());

        Student student2 = new Student.Builder()
                .name("Артем")
                .gradebookNumber(12345)
                .grade(10)
                .build();


    }
}
