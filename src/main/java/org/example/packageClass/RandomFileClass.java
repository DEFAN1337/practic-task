package org.example.packageClass;

import org.example.menu.MenuConstructorClass;
import org.example.model.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RandomFileClass {

    List<Student> student = new ArrayList<>();
    public String[] arrayName = new String[]
            {
                    // Мужские имена (50)
                    "Александр", "Михаил", "Максим", "Иван", "Артем", "Дмитрий", "Даниил", "Марк", "Лев", "Андрей",
                    "Илья", "Никита", "Алексей", "Сергей", "Владимир", "Матвей", "Тимофей", "Роман", "Кирилл", "Владислав",
                    "Егор", "Павел", "Арсений", "Георгий", "Денис", "Демид", "Мирон", "Тимофей", "Ярослав", "Давид",
                    "Игорь", "Андрей", "Федор", "Богдан", "Константин", "Николай", "Степан", "Владислав", "Евгений", "Вадим",
                    "Кирилл", "Тимофей", "Роман", "Арсений", "Артемий", "Егор", "Никита", "Марк", "Матвей", "Лев",

                    // Женские имена (50)
                    "София", "Анна", "Мария", "Алиса", "Ева", "Виктория", "Полина", "Александра", "Варвара", "Анастасия",
                    "Елизавета", "Арина", "Дарья", "Вероника", "Алена", "Ксения", "Василиса", "Софья", "Мирослава", "Таисия",
                    "Диана", "Екатерина", "Ангелина", "Алина", "Агата", "Анна", "Мария", "Алиса", "Ева", "Виктория",
                    "Полина", "Александра", "Варвара", "Анастасия", "Елизавета", "Арина", "Дарья", "Вероника", "Алена", "Ксения",
                    "Василиса", "Софья", "Мирослава", "Таисия", "Диана", "Екатерина", "Ангелина", "Алина", "Агата", "Арина"
            };

    int min = 100000;
    int max = 200000;
    public void randomFile() throws IOException {
        int number = 0;
        boolean turn = true;
        while (turn) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Введите количество студентов: ");
            if (sc.hasNextInt()) {
                number = sc.nextInt();
                System.out.println("Вы ввели случайное число студентов: " + number + "\n");
                turn= !turn;
            } else {
                System.out.println("Ошибка! Можно использовать только цифры.");
            }
        }
        for (int i = 0; i < number; i++) {

            int randomName = (int) (Math.random() * arrayName.length);
            int randomGrade = (int) (Math.random() * 5) + 1;
            int randomeGradebookNumber = (int) (Math.random() * ((max - min) + 1) + min);

            /* Неплохо было бы сделать обработку данных перед записью в коллекцию, на случай
            если номер зачетной книжки уже был введен, второй раз вводить недопустимо
            у студентов номера зачетной книжек не должны совпадать.
            есть понимание как сделать при ручном вводе, попробую организвать
            с рандомом посложнее, пока не получилось.
            */

            /*
            while (turn) {

                for (int j = 0; j < student.size(); j++) {
                    randomeGradebookNumber = (int) (Math.random() * ((max - min) + 1) + min);
                    if (randomeGradebookNumber == student.get(j).gradebookNumberStudent) {
                        break;
                    }

                    if (j == student.size() - 1 && randomeGradebookNumber != student.get(j).gradebookNumberStudent) {
                        turn = !turn;
                    }

                }
                System.out.println(randomeGradebookNumber);
            }
            */

            student.add(new Student(arrayName[randomName],randomGrade,randomeGradebookNumber));
        }
        student.forEach(System.out::println);

        while (true) {

            MenuConstructorClass menuSort = new MenuConstructorClass();
            menuSort.sortMenu(student);

            MenuConstructorClass menuMain = new MenuConstructorClass();
            menuMain.mainMenu();

        }

    }

}
