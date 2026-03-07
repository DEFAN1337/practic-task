package org.example.validation;

import org.example.model.Student;

public final class StudentValidator {

    public static void validate(Student.Builder builder) {
        //будет валидация

        validateGrade(builder.getGrade());
    }

    private static void validateGrade(double grade) {
        if (grade < 0) {
            throw new ValidationException("Средний балл не может быть меньше 2.0");
        }
        if (grade > 5) {
            throw new ValidationException("Средний балл не может быть больше 5.0");
        }
    }
}
