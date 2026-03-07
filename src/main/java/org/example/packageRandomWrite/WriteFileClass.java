package org.example.packageRandomWrite;

import org.example.menu.MenuConstructorClass;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WriteFileClass {

    // Метод реализующий ручной ввод данных
    public static void writeFile() throws IOException {

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

            System.out.println("Добавление данных в файл: " + nameStudent + "; " + gradeStudent + "; " + gradebookNumberStudent + ";");

//            Student p = new Student.Builder()
//                    .setName(nameStudent)
//                    .setGrade(Integer.parseInt(gradeStudent))
//                    .setGradebookNumberStudent(Integer.parseInt(gradebookNumberStudent))
//                    .build();
//            System.out.println("\nДобавление данных в файл: \n" + p);

            // Запись в файл
            // append=false - перезапись, true - добавление в конец
            try (FileWriter writer = new FileWriter("NoteManualInput.txt", true)) {
                writer.write("\n" + nameStudent + "; " + gradeStudent + "; " + gradebookNumberStudent + ";");
                writer.flush(); // очистка буфера
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            while (true) {
                MenuConstructorClass menuConstructorClass = new MenuConstructorClass();
                menuConstructorClass.writeMenu();

                while (true) {
                    MenuConstructorClass menuConstructorClassMainMenu = new MenuConstructorClass();
                    menuConstructorClassMainMenu.mainMenu();
                }

            }

        }

    }

}
