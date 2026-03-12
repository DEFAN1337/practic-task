package org.example.packageSort;

import org.example.collection.StudentsList;
import org.example.model.Student;
import org.example.packageInterface.SortProcessor;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void sortedGrade(StudentsList student) {

        System.out.println();


        //Студенты все
        //List<Student> Student_list1 = new ArrayList<>(student);
        StudentsList Student_list1 = new StudentsList(student);
        //Сортировка
        Sort_Grade(Student_list1);
        Student_list1.forEach(System.out::println);
        System.out.println("\nДанные отсортированы по оценочному баллу\n");

        //Студенты с чётными значениями
        //List<Student> Student_list2 = new ArrayList<>(student);
        StudentsList Student_list2 = new StudentsList(student);
        //Студенты с чётными значениями
        //List<Student> evenStudents = new ArrayList<>();
        StudentsList evenStudents = new StudentsList();
        for (Student s : student) {
            if (s.getGrade() % 2 == 0) {
                evenStudents.add(s);
            }
        }

        //Сортировка только чётных значений
        Sort_Grade(evenStudents);
        //Замена чётных значений в основном списке на отсортированные чётные значения
        int evenIndex = 0;
        for (int i = 0; i < student.size(); i++) {
            if (student.get(i).getGrade() % 2 == 0) {
                student.set(i, evenStudents.get(evenIndex));
                evenIndex++;
            }
        }
        Student_list2.forEach(System.out::println);
        System.out.println("\nДанные отсортированы по дополнительному заданию №1\n");
    }

    // Метод по сортировки оценок
    private void Sort_Grade(StudentsList list) {
        int n = list.size();
        // блок сортировки по оценке
        boolean is_Sorted = false;
        while (!is_Sorted){
            is_Sorted =true;
            for (int i = 0; i < n - 1; i++) {
                // Сравнение параметра Grade
                if (list.get(i).getGrade() < list.get(i + 1).getGrade()) {
                    // swap
                    Student temp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, temp);
                    is_Sorted = false;
                }
            }
        }
    }

    @Override
    public void sortedGradebookNumber(StudentsList student) {

        System.out.println();
       

        //Студенты все
        StudentsList Student_list1 = new StudentsList(student);
        //Сортировка
        Sort_GradebookNumber(Student_list1);
        Student_list1.forEach(System.out::println);
        System.out.println("\nДанные отсортированы по номеру студенческого билета\n");


        //Студенты с чётными значениями
        //List<Student> Student_list2 = new ArrayList<>(student);
        StudentsList Student_list2 = new StudentsList(student);
        //Студенты с чётными значениями
        //List<Student> evenStudents = new ArrayList<>();
        StudentsList evenStudents = new StudentsList();
        for (Student s : student) {
            if (s.getGradebookNumber() % 2 == 0) {
                evenStudents.add(s);
            }
        }

        //Сортировка только чётных значений
        Sort_GradebookNumber(evenStudents);

        //Замена чётных значений в основном списке на отсортированные чётные значения
        int evenIndex = 0;
        for (int i = 0; i < student.size(); i++) {
            if (student.get(i).getGradebookNumber() % 2 == 0) {
                student.set(i, evenStudents.get(evenIndex));
                evenIndex++;
            }
        }

        Student_list2.forEach(System.out::println);
        System.out.println("\nДанные отсортированы по дополнительному заданию №1\n");

    }

    // Метод по сортировки студенческих
    private void Sort_GradebookNumber(StudentsList list) {
        int n = list.size();
        // блок сортировки по оценке
        boolean is_Sorted = false;
        while (!is_Sorted){
            is_Sorted =true;
            for (int i = 0; i < n - 1; i++) {
                // Сравнение параметра Grade
                if (list.get(i).getGradebookNumber() < list.get(i + 1).getGradebookNumber()) {
                    // swap
                    Student temp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, temp);
                    is_Sorted = false;
                }
            }
        }
    }

}
