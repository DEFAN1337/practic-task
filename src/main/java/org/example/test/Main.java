package org.example.test;

import org.example.model.Student;

public class Main {
    public static void main(String[] args) {
        Student student1 = new Student.Builder()
                .name("Артем")
                .gradebookNumber(12345)
                .addGrade("Математика",5)
                .addGrade("Физика", 4)
                .build();

        System.out.println(student1.getName());
        System.out.println(student1.toString());
        System.out.println(student1.getGrades());

        Student student2 = new Student.Builder()
                .name("Артем")
                .gradebookNumber(12345)
                .addGrade("Математика",5)
                //.addGrade("Физика", 10)
                .build();

        Student student3 = new Student.Builder()
                .name("Артем")
                .addGrade("Математика",5)
                .addGrade("Физика", 3)
                .build();

        System.out.println(student3.getName());


    }
}
