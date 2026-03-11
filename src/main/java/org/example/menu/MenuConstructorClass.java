package org.example.menu;

import org.example.collection.StudentsList;
import org.example.model.Student;
import org.example.packageRandomWrite.*;
import org.example.packageInterface.*;
import org.example.packageReadFile.FileManager;
import org.example.packageReadFile.ReadFileClass;
import org.example.packageSort.SortedClass;
import org.example.thread.FindThreadClass;
import org.example.thread.TypeFindData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuConstructorClass {
    private final Scanner scanner;
    private StudentsList students;
    private FileManager fileManager;

    public MenuConstructorClass() {
        this.scanner = new Scanner(System.in);
        this.students = new StudentsList();
        this.fileManager = new FileManager();
    }

    public void selectMainMenu() {
        while (true) {
            System.out.println("\nМеню 'Выбор ввода данных'");
            System.out.println("\nСписок команд");
            System.out.println("'manual_input' - ввод данных вручную");
            System.out.println("'random_input' - ввод данных случайно");
            System.out.println("'file_input' - ввод данных из файла");
            System.out.println("'quit' - закрыть приложение");

            // если уже считали какие-то данные
            if (!students.isEmpty()) {
                System.out.println("'sort_menu' - вернуться в меню 'Сортировка данных'");
                System.out.println("'find' - перейти в меню 'Поиск данных'");

                // если данные отсортированы, то можем их сохранить в файл
                if (students.isSorted()) {
                    saveFile();
                }
            }

            System.out.print("Введите команду: ");
            String menu = scanner.nextLine();
            checkCommand(menu);
        }
    }

    public void mainMenu() throws IOException {
        System.out.println("\nСписок команд");
        System.out.println("'main_menu' - вернуться в меню 'Выбор ввода данных'");
        System.out.println("'sort_menu' - вернуться в меню 'Сортировка данных'");
        System.out.println("'quit' - закрыть приложение");
        System.out.print("Введите команду: ");

        String menu = scanner.nextLine();

        switch (menu) {
            case "main_menu":
                selectMainMenu();
                break;
            case "sort_menu":
                // sortMenu();
                break;
            case "quit":
                System.exit(0);
                break;
            default:
                System.out.println("\nНеверная команда, попробуйте снова...");
                mainMenu();
        }
    }

    public void sortMenu(List<Student> student) {
        System.out.println("\nМеню 'Сортировка данных'");
        System.out.println("\nСписок команд");
        System.out.println("'sort_name' - отсортировать по имени");
        System.out.println("'sort_grade' - отсортировать по оценочному баллу");
        System.out.println("'sort_gradebook' - отсортировать по номеру зачетной книжки");
        System.out.println("'quit' - закрыть приложение");
        System.out.print("Введите команду: ");

        String menu = scanner.nextLine();
        SortProcessor sorted = new SortedClass();

        switch (menu) {
            case "sort_name":
                students.sortByName();
                break;
            case "sort_grade":
                students.sortByGrade();
                break;
            case "sort_gradebook":
                students.sortByNumberGradebook();
                break;
            case "quit":
                System.exit(0);
                break;
            default:
                System.out.println("\nНеверная команда, попробуйте снова...\n");
                sortMenu(student);
        }
    }

    public void writeMenu(List<Student> student) throws IOException {
        System.out.println("\nСписок команд");
        System.out.println("'continue' - продолжить ввод данных");
        System.out.println("'sort_menu' - открыть меню 'Сортировка данных'");
        System.out.println("'quit' - закрыть приложение");
        System.out.print("Введите команду: ");

        String menu = scanner.nextLine();

        switch (menu) {
            case "continue":
                System.out.println();
                WriteFileClass writeFileClass = new WriteFileClass();
                writeFileClass.writeFile();
                break;
            case "sort_menu":
                sortMenu(student);
                break;
            case "quit":
                System.exit(0);
                break;
            default:
                System.out.println("\nНеверная команда, попробуйте снова...");
                writeMenu(student);
        }
    }

    public void saveFile() {
        System.out.println("\nСохранить отсортированный файл?");
        System.out.println("'yes' - да");
        System.out.println("'no' - нет");
        System.out.print("Введите команду: ");

        String menu = scanner.nextLine();
        FileProcessor processor = new FileManager();

        switch (menu) {
            case "yes":
                System.out.println("Введите название файла:");
                String fileName_str = scanner.nextLine();
                fileManager.writeDataInFile(students, fileName_str);
                students.clearSorted();
                selectMainMenu();

                System.out.println("Введите название файла:");
                processor.processSaveFile(scanner.nextLine());
                break;
            case "no":
                students.clearSorted();
                selectMainMenu();
                processor.processDeleteTempFile();
                break;
            default:
                System.out.println("\nНеверная команда, попробуйте снова...");
                saveFile();
        }
    }

    // метод для проверки команд
    public void checkCommand(String command) {
        switch (command) {
            // ручной ввод информации
            case "manual_input":
                System.out.println("\nВы выбрали ввод данных вручную\n");
                System.out.println("Введите количество студентов:");
                while (!scanner.hasNextInt()) {
                    System.out.println("Введите количество студентов:");
                    scanner.next();
                }
                int count_ = scanner.nextInt();
                scanner.nextLine(); // очистка буфера после nextInt()
                WriteFileClass writeFileClass = new WriteFileClass();
                students = writeFileClass.writeDataStudent(count_);
                break;

            // генерация данных
            case "random_input":
                System.out.println("\nВы выбрали ввод данных случайно");
                RandomFileClass randomFileClass = new RandomFileClass();
                try {
                    randomFileClass.randomFile();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                break;

            // считывание данных из файла
            case "file_input":
                System.out.println("\nВы выбрали ввод данных из файла");
                ReadFileClass readFileClass = new ReadFileClass();
                System.out.println("Введите количество студентов:");
                while (!scanner.hasNextInt()) {
                    System.out.println("Введите количество студентов:");
                    scanner.next();
                }
                int count = scanner.nextInt();
                scanner.nextLine(); // очистка буфера после nextInt()
                students = readFileClass.readFile(count);
                break;

            // выход из программы
            case "quit":
                System.exit(0);
                break;

            // отображение меню для сортировки
            case "sort_menu":
                sortMenu(students);
                break;

            case "find":
                findData();
                break;

            default:
                System.out.println("\nНеверная команда, попробуйте снова...");
                selectMainMenu();
        }
    }

    // отображение меню для поиска данных
    void findData() {
        System.out.println("\nМеню 'Поиск данных'");
        System.out.println("\nСписок команд");
        System.out.println("'find_name' - поиск по имени");
        System.out.println("'find_grade' - отсортировать по оценочному баллу");
        System.out.println("'find_gradebook' - отсортировать по номеру зачетной книжки");
        System.out.println("'quit' - закрыть приложение");
        System.out.print("Введите команду: ");

        String menu = scanner.nextLine();

        switch (menu) {
            case "find_name": {
                System.out.println("Введите имя студента для поиска: ");
                String name = scanner.nextLine();
                while (!name.matches("^[а-яА-Я]+$")) {
                    System.out.println("Введите имя студента для поиска: ");
                    name = scanner.nextLine();
                }
                FindThreadClass thread = new FindThreadClass(TypeFindData.Name, students, name);
                thread.startThread();
                break;
            }
            case "find_grade": {
                System.out.println("Введите оценку для поиска: ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Введите оценку для поиска: ");
                    scanner.next();
                }
                int grade = scanner.nextInt();
                scanner.nextLine(); // очистка буфера после nextInt()

                while (grade < 2 || grade > 5) {
                    System.out.println("Оценка должна быть от 2 до 5. Введите оценку для поиска: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Введите оценку для поиска: ");
                        scanner.next();
                    }
                    grade = scanner.nextInt();
                    scanner.nextLine();
                }

                FindThreadClass thread = new FindThreadClass(TypeFindData.Grade, students, grade);
                thread.startThread();
                break;
            }
            case "find_gradebook": {
                System.out.println("Введите номер зачетки для поиска (6 цифр): ");
                String input = scanner.nextLine();
                while (!input.matches("[1-9][0-9]{5}")) {
                    System.out.println("Номер должен содержать 6 цифр (первая не 0). Введите номер зачетки: ");
                    input = scanner.nextLine();
                }
                int number = Integer.parseInt(input);
                FindThreadClass thread = new FindThreadClass(TypeFindData.NumberGradeBook, students, number);
                thread.startThread();
                break;
            }
            case "quit":
                System.exit(0);
                break;
            default:
                System.out.println("\nНеверная команда, попробуйте снова...\n");
                findData();
        }
    }
}