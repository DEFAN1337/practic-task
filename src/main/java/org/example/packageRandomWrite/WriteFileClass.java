package org.example.packageRandomWrite;

import org.example.menu.MenuConstructorClass;
import org.example.model.Student;
import org.example.packageInterface.FileProcessor;
import org.example.packageReadFile.FileManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WriteFileClass {

    // Метод реализующий ручной ввод данных
    public static void writeFile() throws IOException {

        List<Student> student = new ArrayList<>();
        String nameStudent = null, gradeStudent = null, gradebookNumberStudent = null;

        while (true) {
            // переключатели для выхода из циклов
            boolean turnNameStudent = true;
            boolean turnGradeStudent = true;
            boolean turnGradebookNumberStudent = true;

            // обработка ввода имени студента
            while (turnNameStudent) {
                Scanner sc = new Scanner(System.in);
                System.out.print("Введите имя студента: ");

                if (sc.hasNext("^[а-яА-Я]+$")) {
                    String input = sc.next();
                    System.out.println("Вы ввели имя: " + input);
                    nameStudent = input;
                    turnNameStudent=!turnNameStudent;
                } else {
                    System.out.println("Ошибка! Введите имя студента, используя только буквы");
                }
            }

            // обработка ввода оценочного балла
            while (turnGradeStudent) {
                Scanner sc = new Scanner(System.in);
                System.out.print("Введите оценочный балл студента: ");
                if (sc.hasNextInt()) {
                    int number = sc.nextInt();
                    if (number > 0 && number <= 5) {
                        System.out.println("Вы ввели оценочный балл студенту: " + number);
                        turnGradeStudent = !turnGradeStudent;
                        gradeStudent = String.valueOf(number);
                    } else {
                        System.out.println("Оценочный балл должен быть в диапазоне от 1 до 5.");
                    }
                } else {
                    System.out.println("Ошибка! Это не число.");
                }
            }

            // обработка ввода номера зачетной книжки студента
            while (turnGradebookNumberStudent) {
                Scanner sc = new Scanner(System.in);
                System.out.print("Введите номер зачетной книжки студента: ");
                if (sc.hasNextInt()) {
                    int number = sc.nextInt();
                    System.out.println("Вы ввели номер зачетной книжки студента: " + number);
                    turnGradebookNumberStudent = !turnGradebookNumberStudent;
                    gradebookNumberStudent = String.valueOf(number);
                } else {
                    System.out.println("Ошибка! В номере зачетной книжки студента используются только цифры.");
                }
            }

            student.add(new Student.Builder()
                    .name(nameStudent)
                    .grade(Double.parseDouble(gradeStudent))
                    .gradebookNumber(Integer.parseInt(gradebookNumberStudent))
                    .build());
            System.out.println("\nДобавление данных в файл: " + student);

            FileProcessor processor = new FileManager();
            processor.processDeleteEmptyRemoverInterface("Note");
            processor.processWriteFileInterface(student,"Note", true);
            student.clear();
            System.out.println("\nСписок студентов которых вы добавили: \n");
            processor.processReadFileInterface(student,"Note");


            while (true) {

                MenuConstructorClass menuConstructorClass = new MenuConstructorClass();
                menuConstructorClass.writeMenu(student);

                MenuConstructorClass menuSaveFile = new MenuConstructorClass();
                menuSaveFile.saveFile();

                while (true) {
                    MenuConstructorClass menuConstructorClassMainMenu = new MenuConstructorClass();
                    menuConstructorClassMainMenu.mainMenu();

                    MenuConstructorClass menuSort = new MenuConstructorClass();
                    menuSort.sortMenu(student);

                    menuSaveFile.saveFile();
                }

            }

        }

    }

}
