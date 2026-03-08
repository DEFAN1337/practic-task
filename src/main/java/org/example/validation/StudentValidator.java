package org.example.validation;

import org.example.model.Student;

public final class StudentValidator {

    public static void validate(Student.Builder builder) {

        validateGrade(builder);
    }

    private static void validateGrade(Student.Builder builder) {

        double grade = builder.getGrade();

        if (Double.isNaN(grade) || Double.isInfinite(grade)) {
            throw new ValidationException("Средний балл имеет недопустимое значение: " + grade);
        }

        if (grade < 2.0 || grade > 5.0) {
            throw new ValidationException(
                    "Ошибка у студента '" + builder.getName() +
                            "'. Некорректный средний балл: " + grade +
                            ". Допустимый диапазон: 2.0 – 5.0"
            );
        }
    }
}
