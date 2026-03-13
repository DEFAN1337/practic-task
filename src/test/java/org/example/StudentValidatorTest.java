package org.example;

import org.example.model.Student;
import org.example.validation.StudentValidator;
import org.example.validation.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StudentValidatorTest {

    @Test
    void testPassValidationForCorrectStudent() {
        Student student = new Student.Builder()
                .name("Ivan")
                .gradebookNumber(123456)
                .grade(4.5)
                .build();

        Assertions.assertNotNull(student);
    }

    @Test
    void testThrowExceptionWhenNameIsNull() {
        Assertions.assertThrows(ValidationException.class, () ->
                new Student.Builder()
                        .name(null)
                        .gradebookNumber(123456)
                        .grade(4.5)
                        .build()
        );
    }

    @Test
    void testThrowExceptionWhenNameIsEmpty() {
        Assertions.assertThrows(ValidationException.class, () ->
                new Student.Builder()
                        .name("   ")
                        .gradebookNumber(123456)
                        .grade(4.5)
                        .build()
        );
    }

    @Test
    void testThrowExceptionWhenNameTooShort() {
        Assertions.assertThrows(ValidationException.class, () ->
                new Student.Builder()
                        .name("A")
                        .gradebookNumber(123456)
                        .grade(4.5)
                        .build()
        );
    }

    @Test
    void testThrowExceptionWhenNameTooLong() {
        String longName = "A".repeat(StudentValidator.MAX_NAME_LENGTH + 1);

        Assertions.assertThrows(ValidationException.class, () ->
                new Student.Builder()
                        .name(longName)
                        .gradebookNumber(123456)
                        .grade(4.5)
                        .build()
        );
    }

    @Test
    void testThrowExceptionWhenNameContainsInvalidCharacters() {
        Assertions.assertThrows(ValidationException.class, () ->
                new Student.Builder()
                        .name("Ivan123")
                        .gradebookNumber(123456)
                        .grade(4.5)
                        .build()
        );
    }

    @Test
    void testThrowExceptionWhenGradebookNumberNegative() {
        Assertions.assertThrows(ValidationException.class, () ->
                new Student.Builder()
                        .name("Ivan")
                        .gradebookNumber(-1)
                        .grade(4.5)
                        .build()
        );
    }

    @Test
    void testThrowExceptionWhenGradebookNumberNotSixDigits() {
        Assertions.assertThrows(ValidationException.class, () ->
                new Student.Builder()
                        .name("Ivan")
                        .gradebookNumber(12345)
                        .grade(4.5)
                        .build()
        );
    }

    @Test
    void testThrowExceptionWhenGradeTooLow() {
        Assertions.assertThrows(ValidationException.class, () ->
                new Student.Builder()
                        .name("Ivan")
                        .gradebookNumber(123456)
                        .grade(1.9)
                        .build()
        );
    }

    @Test
    void testThrowExceptionWhenGradeTooHigh() {
        Assertions.assertThrows(ValidationException.class, () ->
                new Student.Builder()
                        .name("Ivan")
                        .gradebookNumber(123456)
                        .grade(5.1)
                        .build()
        );
    }

    @Test
    void testThrowExceptionWhenGradeNaN() {
        Assertions.assertThrows(ValidationException.class, () ->
                new Student.Builder()
                        .name("Ivan")
                        .gradebookNumber(123456)
                        .grade(Double.NaN)
                        .build()
        );
    }

    @Test
    void testThrowExceptionWhenGradeInfinite() {
        Assertions.assertThrows(ValidationException.class, () ->
                new Student.Builder()
                        .name("Ivan")
                        .gradebookNumber(123456)
                        .grade(Double.POSITIVE_INFINITY)
                        .build()
        );
    }
}