package org.example.model;

import org.example.validation.StudentValidator;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private final String name;
    private final int gradebookNumber;
    private final List<Grade> grades;

    public Student(Builder builder) {
        this.name = builder.name;
        this.gradebookNumber = builder.gradebookNumber;
        this.grades = builder.grades == null ? List.of() : List.copyOf(builder.grades);
    }

    public String getName() {
        return name;
    }

    public int getGradebookNumber() {
        return gradebookNumber;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    @Override
    public String toString() {
        return "Имя студента: " +name + "; Номер зачетной книжки: " + gradebookNumber + "; Оценки: " + grades;

    }

    public static class Builder {
        private String name;
        private int gradebookNumber;
        private List<Grade> grades;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder gradebookNumber(int gradebookNumber) {
            this.gradebookNumber = gradebookNumber;
            return this;
        }

        //Добавить одну оценку
        public Builder addGrade(String subject, int value) {
            if (this.grades == null) {
                this.grades = new ArrayList<>();
            }
            Grade gradeStudent = new Grade.Builder()
                    .subject(subject)
                    .value(value)
                    .build();
            this.grades.add(gradeStudent);
            return this;
        }

        //Добавить несколько оценок
        public Builder addGrades(List<Grade> grades) {
            if (this.grades == null) {
                this.grades = new ArrayList<>();
            }
            this.grades.addAll(grades);
            return this;
        }

        //Ссылка на список оценок
        public Builder grades(List<Grade> grades) {
            this.grades = new ArrayList<>(grades);
            return this;
        }

        public Student build() {
            StudentValidator.validate(this);
            return new Student(this);
        }
    }
}
