package org.example.packageReadFile;

import org.example.collection.StudentsList;
import org.example.menu.MenuConstructorClass;
import org.example.model.Student;
import org.example.packageInterface.FileProcessor;
import org.example.packageReadFile.FileManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFileClass extends FileManager {
    private FileManager fileManager;
    public ReadFileClass(){
        fileManager = new FileManager();
    }
    //старая версия
    public static void readFile() throws IOException {

        List<Student> student = new ArrayList<>();

        System.out.println("Введите имя файла для загрузки данных: ");
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();

        FileProcessor processor = new FileManager();
        processor.processDeleteEmptyRemoverInterface(fileName);
        processor.processReadFileInterface(student, fileName);

        while (true) {

            MenuConstructorClass menuSort = new MenuConstructorClass();
            menuSort.sortMenu(student);

            MenuConstructorClass menuMain = new MenuConstructorClass();
            menuMain.mainMenu();
        }
    }

    //имя файла, из которого считываем данные
    private String fileName = "students.txt";
    //список студентов, считанных из файла
    private StudentsList students = new StudentsList();
    //метод для считывания файла
    //count - количество данных в массиве данные (по заданию пользователь имеет возможность ограничить)
    public StudentsList readFile(int count){
        System.out.println("Количество студентов для считывания из файла: "+count);
        String fileName = "students.txt";
        try {
            var buf = fileManager.readDataFromFile(fileName);
            if(count>buf.size()){
                System.out.println("В файле недостаточно данных.");
                students = buf;
            }else{
                buf.stream().limit(count).forEach(student -> students.add(student));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        students.forEach(System.out::println);
        return students;
    }
}
