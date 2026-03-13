package org.example.packageSort;

import org.example.collection.StudentsList;
import org.example.model.Student;
import org.example.packageInterface.SortProcessor;

import java.util.Comparator;

public class SortedClass implements SortProcessor {

    @Override
    public void sortedName(StudentsList studentsList) {

        System.out.println();

        StudentsList sorted = bubbleSort(
                studentsList,
                (a, b) -> a.getName().compareTo(b.getName())
        );

        for (int i = 0; i < studentsList.size(); i++) {
            studentsList.set(i, sorted.get(i));
        }

        System.out.println("------------------Список отсортированных студентов:-----------------");
        studentsList.forEach(System.out::println);
    }


    @Override
    public void sortedGrade(StudentsList studentsList) {

        System.out.println();

        StudentsList sorted = bubbleSort(
                studentsList,
                (a, b) -> Double.compare(b.getGrade(), a.getGrade())
        );

        for (int i = 0; i < studentsList.size(); i++) {
            studentsList.set(i, sorted.get(i));
        }

        System.out.println("------------------Список отсортированных студентов:-----------------");
        studentsList.forEach(System.out::println);
    }

    @Override
    public void sortedGradebookNumber(StudentsList studentsList) {

        StudentsList sorted = bubbleSort(
                studentsList,
                Comparator.comparingInt(Student::getGradebookNumber)
        );

        for (int i = 0; i < studentsList.size(); i++) {
            studentsList.set(i, sorted.get(i));
        }

        System.out.println("------------------Список отсортированных студентов:-----------------");
        studentsList.forEach(System.out::println);
    }

    @Override
    public void sortedGradeEvenOnly(StudentsList studentsList) {

        StudentsList evenStudents = new StudentsList();

        for (Student s : studentsList) {
            if ((int) s.getGrade() % 2 == 0) {
                evenStudents.add(s);
            }
        }

        evenStudents = bubbleSort(
                evenStudents,
                (a, b) -> Double.compare(b.getGrade(), a.getGrade())
        );

        int index = 0;
        for (int i = 0; i < studentsList.size(); i++) {
            if ((int) studentsList.get(i).getGrade() % 2 == 0) {
                studentsList.set(i, evenStudents.get(index++));
            }
        }

        System.out.println("------------------Список отсортированных студентов:-----------------");
        studentsList.forEach(System.out::println);
    }

    @Override
    public void sortedGradebookNumberEvenOnly(StudentsList studentsList) {

        StudentsList evenStudents = new StudentsList();

        for (Student s : studentsList) {
            if (s.getGradebookNumber() % 2 == 0) {
                evenStudents.add(s);
            }
        }

        evenStudents = bubbleSort(
                evenStudents,
                Comparator.comparingInt(Student::getGradebookNumber)
        );

        int index = 0;
        for (int i = 0; i < studentsList.size(); i++) {
            if (studentsList.get(i).getGradebookNumber() % 2 == 0) {
                studentsList.set(i, evenStudents.get(index++));
            }
        }

        System.out.println("------------------Список отсортированных студентов:-----------------");
        studentsList.forEach(System.out::println);
    }

    private StudentsList bubbleSort(StudentsList studentsList, Comparator<Student> comparator) {

        StudentsList sorted = new StudentsList(studentsList);

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
