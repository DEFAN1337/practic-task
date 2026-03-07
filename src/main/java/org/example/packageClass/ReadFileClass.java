package org.example.packageClass;

import org.example.menu.MenuConstructorClass;
import org.example.model.Student;
import org.example.packageInterface.FileProcessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFileClass {

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

}
