package org.example.menu;

import org.example.model.Student;
import org.example.packageRandomWrite.*;
import org.example.packageInterface.*;
import org.example.packageReadFile.FileManager;
import org.example.packageReadFile.ReadFileClass;
import org.example.packageSort.SortedClass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuConstructorClass {
        private Scanner scanner;
        private List<Student> students;
        boolean isSorted;
        private  FileManager fileManager;

        public MenuConstructorClass(){
            //scanner  = new Scanner(System.in);
            students = new ArrayList<>();
            isSorted = false;
            fileManager = new FileManager();
        }

        public void selectMainMenu() {
            while (true){
                //очень странное решение, но так работает
                scanner  = new Scanner(System.in);
                System.out.println("\nМеню 'Выбор ввода данных'");
                System.out.println("\nСписок команд");
                System.out.println("'manual_input' - ввод данных вручную");
                System.out.println("'random_input' - ввод данных случайно");
                System.out.println("'file_input' - ввод данных из файла");
                System.out.println("'quit' - закрыть приложение");
                //если уже считали какие-то данные
                if(!students.isEmpty()){
                    System.out.println("'sort_menu' - вернуться в меню 'Сортировка данных'");
                    //если данные отсортированы, то можем их сохранить в файл
                    if(isSorted){
                        saveFile();
                    }
                }
                System.out.print("Введите команду: ");
                String menu = scanner.nextLine();

                checkCommand(menu);
            }
        }

        public void mainMenu() throws IOException {
            /*
            System.out.println("\nСписок команд");
            System.out.println("'main_menu' - вернуться в меню 'Выбор ввода данных'");
            System.out.println("'sort_menu' - вернуться в меню 'Сортировка данных'");
            System.out.println("'quit' - закрыть приложение");
            System.out.print("Введите команду: ");

            Scanner scanner = new Scanner(System.in);
            String menu = scanner.nextLine();

            switch (menu) {
                case "main_menu":
                    selectMainMenu();
                    break;
                case "sort_menu":
//                sortMenu();
                    break;
                case "quit":
                    System.exit(0);
                    break;

                default:
                    System.out.println("\nНеверная команда, попробуйте снова...");
                    mainMenu();
            }
            */
        }

        public void sortMenu(List<Student> student) {

            System.out.println("\nМеню 'Сортировка данных'");
            System.out.println("\nСписок команд");
            System.out.println("'sort_name' - отсортировать по имени");
            System.out.println("'sort_grade' - отсортировать по оценочному баллу");
            System.out.println("'sort gradebook' - отсортировать по номеру зачетной книжки");
            System.out.println("'quit' - закрыть приложение");
            System.out.print("Введите команду: ");


            Scanner scanner = new Scanner(System.in);
            String menu = scanner.nextLine();
            SortProcessor sorted = new SortedClass();
            //Один большой вопрос: зачем временный файл
            //FileProcessor processor = new FileManager();

            switch (menu) {
                case "sort_name":
                    sorted.sortedName(student);
                    isSorted = true;
                    //processor.processWriteFileInterface(student,false);
                    break;
                case "sort_grade":
                    sorted.sortedGrade(student);
                    isSorted = true;
                    //processor.processWriteFileInterface(student,false);
                    break;
                case "sort_gradebook":
                    sorted.sortedGradebookNumber(student);
                    isSorted = true;
                    //processor.processWriteFileInterface(student,false);
                    break;
                case "quit":
                    System.exit(0);
                    break;

                default:
                    System.out.println("\nНеверная команда, попробуйте снова...\n");
                    sortMenu(student);
            }

        }

        public void writeMenu() throws IOException {

            System.out.println("\nСписок команд");
            System.out.println("'continue' - продолжить ввод данных");
            System.out.println("'sort_menu' - открыть меню 'Сортировка данных'");
            System.out.println("'quit' - закрыть приложение");
            System.out.print("Введите команду: ");

            Scanner scanner = new Scanner(System.in);
            String menu = scanner.nextLine();

            switch (menu) {
                case "continue":
                    System.out.println();
                    WriteFileClass writeFileClass = new WriteFileClass();
                    writeFileClass.writeFile();
                    break;
                case "sort_menu":
//                sortMenu();
                    break;
                case "quit":
                    System.exit(0);
                    break;

                default:
                    System.out.println("\nНеверная команда, попробуйте снова...");
                    writeMenu();
            }

        }

        public void saveFile() {
            System.out.println("\nСохранить отсортированный файл?");
            System.out.println("'yes' - да");
            System.out.println("'no' - нет");
            System.out.print("Введите команду: ");

            Scanner scanner = new Scanner(System.in);
            String menu = scanner.nextLine();
            switch (menu) {
                case "yes":
//                    System.out.println("Введите название файла:");
//                    File oldFile = new File("temp_sorted.txt");
//                    String fileName = scanner.nextLine();
//                    File newFile = new File(fileName + ".txt");
//                    newFile.delete();
//                    oldFile.renameTo(newFile);
//                    oldFile.delete();
                    System.out.println("Введите название файла:");
                    String fileName = scanner.nextLine();
                    fileManager.writeDataInFile(students, fileName);
                    selectMainMenu();
                    break;
                case "no":
                    selectMainMenu();
                    break;
                default:
                    System.out.println("\nНеверная команда, попробуйте снова...");
                    saveFile();
            }
        }
        //метод для проверки команд
        public void checkCommand(String command){
            switch (command) {
                case "manual_input":
                    System.out.println("\nВы выбрали ввод данных вручную\n");
                    WriteFileClass writeFileClass = new WriteFileClass();
                    try {
                        writeFileClass.writeFile();
                        isSorted = false;
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                        //throw new RuntimeException(e);
                    }
                    break;
                case "random_input":
                    System.out.println("\nВы выбрали ввод данных случайно");
                    RandomFileClass randomFileClass = new RandomFileClass();
                    try {
                        randomFileClass.randomFile();
                        isSorted = false;
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                        //throw new RuntimeException(e);
                    }
                    break;
                case "file_input":
                    System.out.println("\nВы выбрали ввод данных из файла");
                    ReadFileClass readFileClass = new ReadFileClass();
                    //по заданию пользователь должен иметь возможность ограничить
                    System.out.println("Введите количество студентов:");
                    while (!scanner.hasNextInt()){
                        System.out.println("Введите количество студентов:");
                        scanner.next();
                    }
                    int count = scanner.nextInt();
                    students = readFileClass.readFile(count);
                    isSorted = false;
                    break;
                case "quit":
                    System.exit(0);
                    break;
                case "sort_menu":
                    sortMenu(students);
                    break;
                default:
                    System.out.println("\nНеверная команда, попробуйте снова...");
                    selectMainMenu();
            }
        }
}
