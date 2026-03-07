package org.example.packageClass;

import org.example.menu.MenuConstructorClass;
import org.example.model.Student;
import org.example.packageInterface.FileProcessor;

import javax.imageio.IIOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileManager implements FileProcessor {

    @Override
    public void processDeleteEmptyRemoverInterface (String fileName) {

        try {
            Path input = Paths.get(fileName + ".txt");
            // Читаем, фильтруем (убираем пустые/пробельные) и записываем
            List<String> lines = Files.lines(input)
                    .filter(line -> !line.trim().isEmpty())
                    .collect(Collectors.toList());

            Files.write(input, lines);
        }
        catch (IIOException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void processReadFileInterface(List<Student> student, String fileName) {
        String[] linesBuffer;
//        List<Student> student = new ArrayList<>();
        try {
            // Читаем все строки из файла в List<String> lines
            List<String> lines = Files.readAllLines(Paths.get(fileName + ".txt"));

            for (String line : lines) {
                linesBuffer = line
                        .replaceAll(" ","")
                        .split(";");

                try {
                    student.add(new Student.Builder()
                            .name(linesBuffer[0])
                            .grade(Double.parseDouble(linesBuffer[1]))
                            .gradebookNumber(Integer.parseInt(linesBuffer[2]))
                            .build());
                }
                catch (NumberFormatException e) {
                    System.err.println("Ошибка при чтении данных из файла!\nПроверьте корректность данных в исходном файле " + e.getMessage());
                    System.exit(1);
                }
            }
            student.forEach(System.out::println);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void processWriteFileInterface (List<Student> student, boolean flag) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("temp_sorted.txt", flag))) {
            for (int i = 0; i < student.size(); i++) {
                writer.write(student.get(i).getName() + "; " + student.get(i).getGrade() + "; " + student.get(i).getGradebookNumber() + ";");
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        MenuConstructorClass menuConstructorClass = new MenuConstructorClass();
        menuConstructorClass.saveFile();
    }
}

