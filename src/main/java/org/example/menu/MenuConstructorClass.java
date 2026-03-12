package org.example.menu;

import org.example.collection.StudentsList;
import org.example.packageRandomWrite.RandomFileClass;
import org.example.packageRandomWrite.WriteFileClass;
import org.example.packageReadFile.FileManager;
import org.example.packageReadFile.ReadFileClass;
import org.example.thread.FindThreadClass;
import org.example.thread.TypeFindData;

import java.util.Scanner;

public class MenuConstructorClass {
    private final Scanner scanner = new Scanner(System.in);

    private final FileManager fileManager = new FileManager();

    private StudentsList studentsList = new StudentsList();
    private ReadFileClass readFileClass = new ReadFileClass();

    public void start() {
        boolean running = true;
        while (running) {
            printMainMenu();
            String command = scanner.nextLine();
            switch (command) {
                case "manual_input" -> manualInput();
                case "random_input" -> randomInput();
                case "file_input" -> selectFileMenu();
                case "sort_menu" -> {
                    if(studentsList.isEmpty()){
                        System.out.println("Список студентов пуст. Сначала добавьте данные.");
                    }else {
                        sortMenu();
                    }
                }
                case "save" -> {
                    if(studentsList.isEmpty()){
                        System.out.println("Список студентов пуст. Сначала добавьте данные.");
                    }else {
                        saveData();
                    }
                }
                case "find" -> {
                    if (studentsList.isEmpty()) {
                        System.out.println("Список студентов пуст. Сначала добавьте данные.");
                    } else {
                        findData();
                    }
                }
                case "quit" -> running = false;
                default -> System.out.println("Неверная команда");
            }
        }
    }
    //ручной ввод данных
    private void manualInput() {

        System.out.println("Введите количество студентов:");
        int count = readInt();

        WriteFileClass writer = new WriteFileClass();
        studentsList.clear();
        studentsList = writer.writeDataStudent(count);
        System.out.println("---------------------Список введенных студентов:--------------------");
        studentsList.forEach(System.out::println);
    }
    //генерация данных
    private void randomInput() {

        System.out.println("Введите количество студентов:");
        int count = readInt();

        RandomFileClass generator = new RandomFileClass();
        studentsList.clear();
        studentsList = generator.generate(count);
        studentsList.forEach(System.out::println);
    }
    //выбор файла для считывания
    private void selectFileMenu(){
        studentsList.clear();
        while (studentsList.isEmpty()) {
            printSelectFileMenu();
            System.out.println("Введите команду:");
            String command = scanner.nextLine();
            switch (command) {
                case "defailt" -> fileInput("students.txt");
                case "castom" -> {
                    System.out.println("Введите имя файла для ввода:");
                    String fileName = scanner.nextLine();
                    while (!fileManager.isHaveFile(fileName)){
                        System.err.print("Ошибка! Такого файла не существует. Попробуйте еще раз:");
                        fileName = scanner.nextLine();
                    }
                    fileInput(fileName);
                }
                case "back" -> {
                    return;
                }
                case "quit" -> System.exit(0);
                default -> System.out.println("Неверная команда");
            }
        }

    }
    //отображение меню для считывания
    private  void printSelectFileMenu(){
        System.out.println("""
                    Меню ' Выбор файла для считывания'
                    'defailt' - для выбора файла по умолчанию
                    'castom'  - для ввода наименования своего файла
                    'back'    - вернуться в главное меню
                    'quit'    - закрыть приложение
                    """);
    }
    //считывание данных из файла
    private void fileInput(String fileName) {

        //ReadFileClass readFileClass = new ReadFileClass();

        //System.out.println("Введите имя файла для ввода:");
        //String fileName = scanner.nextLine();

        System.out.println("Введите количество студентов. Для отбора всех данных введите -1:");
        int count = readInt();
        try {
            studentsList = readFileClass.readFile(fileName, count);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

    }
    //сортировка данных
    private void sortMenu() {

        while (true) {

            printSortMenu();
            String command = scanner.nextLine();

            switch (command) {

                case "sort_name" -> studentsList.sortByName();
                case "sort_grade" -> studentsList.sortByGrade();
                case "sort_gradebook" -> studentsList.sortByNumberGradebook();
                case "back" -> {
                    return;
                }
                case "quit" -> System.exit(0);
                default -> System.out.println("Неверная команда");
            }
        }
    }
    //поиск данных
    private void findData() {

        while (true) {

            printSearchMenu();

            String command = scanner.nextLine();

            switch (command) {

                case "find_name" -> findByName();
                case "find_grade" -> findByGrade();
                case "find_gradebook" -> findByGradebook();
                case "back" -> {
                    return;
                }
                case "quit" -> System.exit(0);
                default -> System.out.println("Неверная команда");
            }
        }
    }

    private void findByName() {

        System.out.println("Введите имя студента:");

        String name = scanner.nextLine();

        while (!name.matches("^[a-zA-Zа-яА-ЯёЁ]+$")) {
            System.out.println("Неправильное имя. Введите имя студента:");
            name = scanner.nextLine();
        }

        FindThreadClass thread =
                new FindThreadClass(TypeFindData.Name, studentsList, name);

        thread.startThread();
    }

    private void findByGrade() {

        System.out.println("Введите оценку:");

        int grade = readInt();

        while (grade < 2 || grade > 5) {
            System.out.println("Оценка должна быть от 2 до 5:");
            grade = readInt();
        }

        FindThreadClass thread =
                new FindThreadClass(TypeFindData.Grade, studentsList, grade);

        thread.startThread();
    }

    private void findByGradebook() {

        System.out.println("Введите номер зачётки (6 цифр):");

        String input = scanner.nextLine();

        while (!input.matches("[1-9][0-9]{5}")) {
            System.out.println("Номер должен содержать 6 цифр:");
            input = scanner.nextLine();
        }

        int number = Integer.parseInt(input);

        FindThreadClass thread =
                new FindThreadClass(TypeFindData.NumberGradeBook, studentsList, number);

        thread.startThread();
    }

    private void saveData() {
        String nameFileToSave = "";
        //признак, если файл существует, то дописать к нему
        //true - дописываем в конец файла
        boolean isAppend = false;
        boolean isWriteNewFile = false;

        while (nameFileToSave.isEmpty()){
            printMenuSave();
            String command = scanner.nextLine();
            System.out.println("Введите имя файла:");
            nameFileToSave = scanner.nextLine();
            switch (command) {
                case "append"->{
                    isAppend=true;
                    isWriteNewFile=false;
                }
                case "rewrite"->{
                    isAppend=false;
                    isWriteNewFile=false;
                }
                case "new"->{
                    isAppend=false;
                    isWriteNewFile=true;
                }
            }
            fileManager.writeDataInFile(studentsList, nameFileToSave, isAppend, isWriteNewFile);
            studentsList.clear();
        }
    }
    private  void printMenuSave(){
        System.out.println(
                """
                Меню 'Cохранение данных'
                'append'  - дозаписать в существующий файл
                'rewrite' - перезаписать существующий файл
                'new'     - записать в новый файл
                'back'    - вернуться в главное меню
                'quit'    - закрыть приложение
                """
        );
    }
    private void printMainMenu() {
        System.out.println(
                """
                Меню 'Выбор ввода данных'
                manual_input  - ввод вручную
                random_input  - случайные данные
                file_input    - загрузка из файла
                sort_menu     - сортировка
                save          - сохранение
                find          - поиск
                quit          - выход
                """
        );
    }

    private void printSortMenu() {
        System.out.println(
                """
                Меню 'Сортировка данных'
                sort_name       - сортировка по имени
                sort_grade      - сортировка по среднему баллу
                sort_gradebook  - сортировка по номеру зачетки
                back            - вернуться в главное меню
                quit            - закрыть приложение
                """
        );
    }

    private void printSearchMenu() {
        System.out.println(
                """
                Меню 'Поиск данных'
                find_name       - поиск по имени
                find_grade      - поиск по оценочному баллу
                find_gradebook  - поиск по номеру зачетной книжки
                back            - вернуться в главное меню
                quit            - закрыть приложение
                """
        );
    }

    private int readInt() {

        while (!scanner.hasNextInt()) {
            scanner.next();
        }

        int value = scanner.nextInt();
        scanner.nextLine();

        return value;
    }
}