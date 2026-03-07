package org.example.model;

import org.example.validation.StudentValidator;


public class Student {
    private final String name;
    private final int gradebookNumber;
    private final double grade;

    public Student(Builder builder) {
        this.name = builder.name;
        this.gradebookNumber = builder.gradebookNumber;
        this.grade = builder.grade;
    }

    public Student(String name, int grade, int gradebookNumber) {
        this.name = name;
        this.grade = grade;
        this.gradebookNumber = gradebookNumber;
    }

    public String getName() {
        return name;
    }

    public int getGradebookNumber() {
        return gradebookNumber;
    }

    public double getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Имя студента: " +name + "; Номер зачетной книжки: " + gradebookNumber + "; Сред.бал: " + grade;

    }

    public static class Builder {
        private String name;
        private int gradebookNumber;
        private double grade;

        public String getName() {
            return name;
        }

        public double getGrade() {
            return grade;
        }

        public int getGradebookNumber() {
            return gradebookNumber;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder gradebookNumber(int gradebookNumber) {
            this.gradebookNumber = gradebookNumber;
            return this;
        }

        public Builder grade(double grade) {
            this.grade = grade;
            return this;
        }

        public Student build() {
            StudentValidator.validate(this);
            return new Student(this);
        }
    }
}
