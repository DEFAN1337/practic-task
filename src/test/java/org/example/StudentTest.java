package org.example;

import org.example.model.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void testCreateStudentUsingBuilder() {
        Student student = new Student.Builder()
                .name("Иван")
                .gradebookNumber(123456)
                .grade(4.5)
                .build();

        assertNotNull(student);
        assertEquals("Иван", student.getName());
        assertEquals(123456, student.getGradebookNumber());
        assertEquals(4.5, student.getGrade());
    }

    @Test
    void testReturnCorrectStudentToString() {
        Student student = new Student.Builder()
                .name("Иван")
                .gradebookNumber(123456)
                .grade(4.5)
                .build();

        String expectedAnswer = "Имя студента: Иван; Номер зачетной книжки: 123456; Сред.бал: 4.5";

        assertEquals(expectedAnswer, student.toString());
    }

    @Test
    void testStudentsEqualsIfAllFieldsEqual() {
        Student student1 = new Student.Builder()
                .name("Иван")
                .gradebookNumber(123456)
                .grade(4.5)
                .build();

        Student student2 = new Student.Builder()
                .name("Иван")
                .gradebookNumber(123456)
                .grade(4.5)
                .build();

        assertEquals(student1, student2);
    }

    @Test
    void testStudentsNotEqualsIfFieldsDifferent() {
        Student student1 = new Student.Builder()
                .name("Иван")
                .gradebookNumber(123456)
                .grade(4.5)
                .build();

        Student student2 = new Student.Builder()
                .name("Петр")
                .gradebookNumber(124456)
                .grade(3.8)
                .build();

        assertNotEquals(student1, student2);
    }

    @Test
    void testHashCodeEqualsForEqualObjects() {
        Student student1 = new Student.Builder()
                .name("Иван")
                .gradebookNumber(123456)
                .grade(4.5)
                .build();

        Student student2 = new Student.Builder()
                .name("Иван")
                .gradebookNumber(123456)
                .grade(4.5)
                .build();

        assertEquals(student1.hashCode(), student2.hashCode());
    }

    @Test
    void testStudentEqualsShouldReturnFalseForNull() {
        Student student = new Student.Builder()
                .name("Иван")
                .gradebookNumber(123456)
                .grade(4.5)
                .build();

        assertNotEquals(null, student);
    }
}