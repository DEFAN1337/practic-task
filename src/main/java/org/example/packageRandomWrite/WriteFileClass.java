package org.example.packageRandomWrite;

import org.example.collection.StudentsList;
import org.example.menu.MenuConstructorClass;
import org.example.model.Student;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WriteFileClass {

    private Scanner scanner;
    private StudentsList students;

    public WriteFileClass(){
        scanner = new Scanner(System.in);
        students = new StudentsList();
    }

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

    //новый метод создания данных
    public StudentsList writeDataStudent(int count){
        for(int i=0; i<count; i++){
            System.out.println("Заполнение данных по студенту №"+(i+1));
           String name = null;
            int grade = 0, number = 0;
            System.out.println("Введите имя студента:");
            name = scanner.next();
            while (!name.matches("^[а-яА-Я]+$")){
                System.out.println("Ошибка! Введите имя студента, используя только буквы (кирилица). ");
                System.out.println("Введите имя студента:");
                name = scanner.next();
            }
            System.out.print("Введите оценочный балл студента: ");
            while (!scanner.hasNext("[2-5]")){
                System.out.println("Ошибка! Оценка - это число в диапазоне от 2 до 5");
                System.out.print("Введите оценочный балл студента: ");
                scanner.next();
            }
            grade = scanner.nextInt();
            System.out.print("Введите номер зачетной книжки студента: ");
            while (!scanner.hasNext("[1-9][0-9]{5}")){
                System.out.println("Ошибка! Номер зачетной книжки - это 6-ти значное число");
                System.out.print("Введите номер зачетной книжки студента: ");
                scanner.next();
            }
            number = scanner.nextInt();
            try {
                var student = new Student.Builder()
                        .name(name)
                        .gradebookNumber(number)
                        .grade(grade)
                        .build();
                System.out.println(student.toString());
                students.add(student);
            } catch (RuntimeException e) {
                System.err.println(e.getMessage());
                i--;
                //throw new RuntimeException(e);
            }
        }
        students.forEach(System.out::println);
        return students;
    }
}
