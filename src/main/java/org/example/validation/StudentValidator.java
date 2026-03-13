package org.example.validation;

import org.example.model.Student;

import java.util.regex.Pattern;

public final class StudentValidator {

    private static final Pattern studentNamePattern = Pattern.compile("\\p{L}+");

    public static final int MIN_NAME_LENGTH = 2;

    public static final int MAX_NAME_LENGTH = 100;

    public static void validate(Student.Builder builder) {

        validateName(builder.getName());
        validateGradebookNumber(builder);
        validateGrade(builder);
    }

    private static void validateName(String name) {

        if (name == null) {
            throw new ValidationException("Имя студента не может быть null");
        }

        String normalizedName = normalizeName(name);

        if (!studentNamePattern.matcher(normalizedName).matches()) {
            throw new ValidationException(
                    "Имя содержит недопустимые символы: " + normalizedName
            );
        }
    }

    private static String normalizeName(String name) {

        String normalized = name.trim().replaceAll("\\s+", " ");

        if (normalized.isEmpty()) {
            throw new ValidationException("Имя студента не может быть пустым");
        }

        int length = normalized.length();

        if (length < MIN_NAME_LENGTH || length > MAX_NAME_LENGTH) {
            throw new ValidationException(
                    "Длина имени должна быть от " + MIN_NAME_LENGTH +
                            " до " + MAX_NAME_LENGTH +
                            ". У имени '" + normalized + "' длина: " + length
            );
        }

        return normalized;
    }

    private static void validateGradebookNumber(Student.Builder builder) {

        int number = builder.getGradebookNumber();

        if (number <= 0) {
            throw new ValidationException(
                    "Ошибка у студента '" + builder.getName() +
                        "'. Номер зачетной книжки должен быть положительным. " +
                        "Вы ввели: " + number
            );
        }

        if (number < 100000 || number > 999999) {
            throw new ValidationException(
                    "Ошибка у студента '" + builder.getName() +
                            "'. Номер зачетной книжки должен иметь длину 6. " +
                            "Вы ввели: " + number
            );
        }
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
