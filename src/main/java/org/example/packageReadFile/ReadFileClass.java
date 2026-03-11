package org.example.packageReadFile;

import org.example.collection.StudentsList;

public class ReadFileClass {

    private final FileManager fileManager = new FileManager();

    public StudentsList readFile(String fileName, int count) {

        System.out.println("Количество студентов для считывания: " + count);

        StudentsList result = new StudentsList();

        StudentsList buffer = fileManager.readDataFromFile(fileName);

        if (count > buffer.size()) {
            System.out.println("В файле недостаточно данных.");
            return buffer;
        }

        for (int i = 0; i < count; i++) {
            result.add(buffer.get(i));
        }

        result.forEach(System.out::println);

        return result;
    }
}
