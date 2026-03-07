package org.example.model;

import org.example.validation.ValidationException;

public class Grade {
    private final String subject;
    private final int value;

    public Grade(Builder builder) {
        this.subject = builder.subject;
        this.value = builder.value;
        validate();
    }

    public String getSubject() {
        return subject;
    }

    public int getGrade() {
        return value;
    }

    private void validate() {
        if (subject == null || subject.trim().isEmpty()) {
            throw new ValidationException("Предмет не может быть пустым");
        }
        if (value < 2 || value > 5) {
            throw new ValidationException("Оценка должна быть в диапазоне от 2 до 5");
        }
    }

    @Override
    public String toString() {
        return subject + "-" + value;
    }

    public static class Builder {
        private String subject;
        private int value;

        public Builder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder value(int value) {
            this.value = value;
            return this;
        }

        public Grade build() {
            return new Grade(this);
        }
    }

}

