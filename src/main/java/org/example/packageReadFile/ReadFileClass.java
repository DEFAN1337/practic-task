package org.example.packageReadFile;

import org.example.menu.MenuConstructorClass;
import org.example.model.Student;
import org.example.packageInterface.FileProcessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFileClass extends FileManager {

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

            MenuConstructorClass menuConstructorClass = new MenuConstructorClass();
            menuConstructorClass.saveFile();

            MenuConstructorClass menuMain = new MenuConstructorClass();
            menuMain.mainMenu();

        }

    }

}
