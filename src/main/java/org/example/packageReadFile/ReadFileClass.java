package org.example.packageReadFile;

import org.example.collection.StudentsList;

import java.util.ArrayList;

public class ReadFileClass {

    private final FileManager fileManager = new FileManager();

    public StudentsList readFile(String fileName, int count) {

        if(count!=-1)
            System.out.println("Количество студентов для считывания: " + count);
        else
            System.out.println("Надо считать все данные из файла");

        StudentsList result = new StudentsList();
        StudentsList buffer = new StudentsList();

        try {
            buffer = fileManager.readDataFromFile(fileName);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

        if(count == -1) {
            return buffer;
        }else {
            if (count > buffer.size()) {
                System.out.println("В файле недостаточно данных.");
                return buffer;
            }
            /*
            for (int i = 0; i < count; i++) {
                result.add(buffer.get(i));
            }
            */
            //заполнение коллекции через стрим (мне кажется так получше будет)
            buffer.stream().limit(count).forEach(result::add);

            return result;
        }
    }

    public boolean isContainsFile(String fileName){
        return fileManager.isHaveFile(fileName);
    }
}
