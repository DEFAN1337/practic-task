package org.example.packageClass;

import org.example.model.Student;
import org.example.packageInterface.SortProcessor;

import java.util.List;

public class SortedClass implements SortProcessor {

    @Override
    public void sortedName(List<Student> student) {

        System.out.println();
        // первый этап сортирует по первым буквам
        for (int i = 0; i < student.size() - 1; i++) {
            for (int j = 0; j < student.size() - i - 1; j++) {
                if (student.get(j).getName().charAt(0) > student.get(j + 1).getName().charAt(0)) {
                    // swap (перестановка)
                    Student temp = student.get(j);
                    student.set(j, student.get(j + 1));
                    student.set(j + 1, temp);
                }
            }
        }
        // второй этап сортирует имена от второго символа и далее
        for (int i = 0; i < student.size() - 1; i++) {
            for (int j = 0; j < student.size() - i - 1; j++) {
                int lim = Math.min(student.get(j).getName().length(), student.get(j+1).getName().length());
                int k = 0;
                    // // Сравнение параметра name
                while (k < lim - 1) {
                    if (student.get(j).getName().charAt(k) == student.get(j + 1).getName().charAt(k) &&
                            student.get(j).getName().charAt(k + 1) > student.get(j + 1).getName().charAt(k + 1)) {
                        // swap (перестановка)
                        Student temp = student.get(j);
                        student.set(j, student.get(j + 1));
                        student.set(j + 1, temp);
                        k = lim;
                    }
                    k++;
                }

            }

        }

        student.forEach(System.out::println);
        System.out.println("\nДанные отсортированы по имени");

    }

    @Override
    public void sortedGrade(List<Student> student) {

        System.out.println();
        // далее блок сортировки по оценочному баллу
        for (int i = 0; i < student.size() - 1; i++) {
            for (int j = 0; j < student.size() - i - 1; j++) {
                // Сравнение параметра grade
                if (student.get(j).getGrade() < student.get(j + 1).getGrade()) {
                    // swap (перестановка)
                    Student temp = student.get(j);
                    student.set(j, student.get(j + 1));
                    student.set(j + 1, temp);
                }
            }
        }
        student.forEach(System.out::println);
        System.out.println("\nДанные отсортированы по оценочному баллу");

    }

    @Override
    public void sortedGradebookNumber(List<Student> student) {

        System.out.println();
        // далее блок сортировки по номеру зачетной книжки
        for (int i = 0; i < student.size() - 1; i++) {
            for (int j = 0; j < student.size() - i - 1; j++) {
                // Сравнение параметра gradebookNumberStudent
                if (student.get(j).getGradebookNumber() > student.get(j + 1).getGradebookNumber()) {
                    // swap (перестановка)
                    Student temp = student.get(j);
                    student.set(j, student.get(j + 1));
                    student.set(j + 1, temp);
                }
            }
        }
        student.forEach(System.out::println);
        System.out.println("\nДанные отсортированы по номеру зачетной книжки");

    }

}