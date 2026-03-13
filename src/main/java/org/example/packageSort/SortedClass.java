package org.example.packageSort;

import org.example.collection.StudentsList;
import org.example.model.Student;
import org.example.packageInterface.SortProcessor;

import java.util.Comparator;

public class SortedClass implements SortProcessor {

    @Override
    public void sortedName(StudentsList student) {

        System.out.println();

        // Сортировка по алфавиту
        boolean is_Sorted = false;
        while (!is_Sorted) {
            is_Sorted = true;
            for (int i = 0; i < student.size() - 1; i++) {
                String name1 = student.get(i).getName();
                String name2 = student.get(i + 1).getName();
                int lim = Math.min(name1.length(), name2.length());
                int k = 0;

                while (k < lim) {
                    if (name1.charAt(k) != name2.charAt(k)) {

                        // swap (перестановка)
                        if (name1.charAt(k) > name2.charAt(k)) {
                            Student temp = student.get(i);
                            student.set(i, student.get(i + 1));
                            student.set(i + 1, temp);
                            is_Sorted = false;
                        }
                        break;
                    }
                    k++;
                }

                // Сортировка по длине имени
                if (k == lim && name1.length() > name2.length()){
                    Student temp = student.get(i);
                    student.set(i, student.get(i + 1));
                    student.set(i + 1, temp);
                    is_Sorted = false;
                }

            }

        }

        student.forEach(System.out::println);
        System.out.println("\nДанные отсортированы по имени");

    }


    // Метод по сортировки оценок
    @Override
    public void sortedGrade(StudentsList list) {

        StudentsList sorted = bubbleSort(
                list,
                (a, b) -> Double.compare(b.getGrade(), a.getGrade())
        );

        for (int i = 0; i < list.size(); i++) {
            list.set(i, sorted.get(i));
        }
    }

    // Метод по сортировки студенческих
    @Override
    public void sortedGradebookNumber(StudentsList list) {

        StudentsList sorted = bubbleSort(
                list,
                Comparator.comparingInt(Student::getGradebookNumber)
        );

        for (int i = 0; i < list.size(); i++) {
            list.set(i, sorted.get(i));
        }
    }

    @Override
    public void sortedGradeEvenOnly(StudentsList list) {
        StudentsList evenStudents = new StudentsList();
        for (Student s : list) {
            if ((int) s.getGrade() % 2 == 0) {
                evenStudents.add(s);
            }
        }

        evenStudents = bubbleSort(
                evenStudents,
                (a, b) -> Double.compare(b.getGrade(), a.getGrade())
        );

        int index = 0;
        for (int i = 0; i < list.size(); i++) {
            if ((int) list.get(i).getGrade() % 2 == 0) {
                list.set(i, evenStudents.get(index++));
            }
        }
    }

    @Override
    public void sortedGradebookNumberEvenOnly(StudentsList list) {
        StudentsList evenStudents = new StudentsList();
        for (Student s : list) {
            if (s.getGradebookNumber() % 2 == 0) {
                evenStudents.add(s);
            }
        }

        evenStudents = bubbleSort(
                evenStudents,
                Comparator.comparingInt(Student::getGradebookNumber)
        );

        int index = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getGradebookNumber() % 2 == 0) {
                list.set(i, evenStudents.get(index++));
            }
        }
    }

    private StudentsList bubbleSort(StudentsList list, Comparator<Student> comparator) {
        StudentsList sorted = new StudentsList(list);
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < sorted.size() - 1; i++) {
                if (comparator.compare(sorted.get(i), sorted.get(i + 1)) > 0) {
                    Student temp = sorted.get(i);
                    sorted.set(i, sorted.get(i + 1));
                    sorted.set(i + 1, temp);
                    swapped = true;
                }
            }
        } while (swapped);
        return sorted;
    }
}
