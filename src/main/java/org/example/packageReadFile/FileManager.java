package org.example.packageReadFile;

import org.example.collection.StudentsList;
import org.example.model.Student;
import org.example.packageInterface.FileProcessor;

import javax.imageio.IIOException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
//класс для работы с файлом
public class FileManager implements FileProcessor {

    //этот метод технически не используется (вызывается в классе ReadFileClass в старой версии readFile, которая не используется)
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

    //этот метод технически не используется (вызывается в классе ReadFileClass в старой версии readFile, которая не используется)
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

    //этот метод вообще не используется
    @Override
    public void processWriteFileInterface (List<Student> student, boolean flag) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("temp_sorted.txt", StandardCharsets.UTF_8, flag))) {
            for (int i = 0; i < student.size(); i++) {
                writer.write(student.get(i).getName() + "; " + student.get(i).getGrade() + "; " + student.get(i).getGradebookNumber() + ";");
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void processWriteFileInterface (List<Student> student, String fileName, boolean flag) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName + ".txt", StandardCharsets.UTF_8, flag))) {
            for (int i = 0; i < student.size(); i++) {
                writer.write(student.get(i).getName() + "; " + student.get(i).getGrade() + "; " + student.get(i).getGradebookNumber() + ";");
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void processSaveFile (String fileName) {

        File oldFile = new File("temp_sorted.txt");

        File newFile = new File(fileName + ".txt");
        newFile.delete();
        oldFile.renameTo(newFile);
        oldFile.delete();

    }

    @Override
    public void processDeleteTempFile () {

        File file = new File("temp_sorted.txt");
        file.delete();

    }

    //метод для записи в файл
    //student - отсортированный список студентов
    //fileName_ - наименование файла, в который надо записать
    //isAppend - признак, что надо дописывать в файл если он уже существует
    //isWriteNew - признак, что надо записывать в новый файл, если он существует(true) или же затирать старые данные
    public void writeDataInFile(List<Student> students, String fileName_, boolean isAppend, boolean isWriteNew){
        //проверяем как пользователь указал данные
        String fileName = chckPath(fileName_);
        //если файл существует и пишем в новый файл, то решаем проблему
        if (Files.exists(Path.of(fileName)) && isWriteNew) {
            String name = fileName.split("\\.")[0];
            DateTimeFormatter format = DateTimeFormatter.ofPattern("-ddMMyy-HHmmss");

            fileName = name + LocalDateTime.now().format(format) + ".txt";
        }
        Path path = Path.of(fileName);
        System.out.println(students.size());
        try {
            //если файла нет, то создаем
            if(!Files.exists(path))
                Files.createFile(path);

            StringBuilder builder = new StringBuilder();
            //по хорошему надо подумать как избавиться от последней пустой строки
            students.forEach(student -> builder.append(student.getName()).append(";").append(student.getGrade()).append(";").append(student.getGradebookNumber()).append("\n"));
            StandardOpenOption option;
            //если нам надо дозаписать в конец
            if(isAppend)
                option = StandardOpenOption.APPEND;
            else
                option = StandardOpenOption.WRITE;

            Files.writeString(path, builder.toString(), option);
            System.out.println("Данные успешно сохранены в файл "+path);
        } catch (IOException e) {

            System.out.println(e.getMessage());
        }

    }

    //метод для считывания данных из файла
    //name - наименование файла
    public StudentsList readDataFromFile(String name){
        String fileName = chckPath(name);
        StudentsList students = new StudentsList();
        Path path = Path.of(fileName);
        //проверяем существует ли файл
        if( Files.exists(path)){
            try {
                //считываем файл и отбираем все НЕ пустые строки и с нужным количеством данных
                var data = Files.readAllLines(Path.of(fileName)).stream()
                        .filter(line->(!line.isEmpty() && line.trim().split(";").length==3)).toList();
                data.stream().forEach(student->{
                    var splitData = student.trim().split(";");
                    double grade;
                    int number;
                    try {
                        grade = Double.parseDouble(splitData[1].trim());
                    } catch (NumberFormatException e) {
                        //System.out.println("В файле есть ошибки.");
                        throw new RuntimeException("В файле есть ошибки.");
                    }
                    try {
                        number = Integer.parseInt(splitData[2].trim());
                    } catch (NumberFormatException e) {
                        throw new RuntimeException("В файле есть ошибки.");
                    }
                    if(grade!=0 && number!=0){
                        try {
                            students.add(new Student.Builder()
                                    .name(splitData[0].trim())
                                    .gradebookNumber(number)
                                    .grade(grade)
                                    .build());
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            throw new RuntimeException("Ошибка! Файла не существует.");
        }
        return students;
    }

    private String chckPath(String fileName){
        if (!fileName.contains(".txt")) {
            fileName = fileName + ".txt";
        }
        return  fileName;
    }

    public boolean isHaveFile(String name){
        String fileName = chckPath(name);
        return  Files.exists(Path.of(fileName));
    }
}

