package org.example.packageRandomWrite;

import org.example.collection.StudentsList;
import org.example.model.Student;

public class RandomFileClass {

    StudentsList studentsList = new StudentsList();

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

    public StudentsList generate(int number) {

        for (int i = 0; i < number; i++) {

            int randomName = (int) (Math.random() * arrayName.length);
            double randomGrade = Math.round((Math.random() * 30 + 20)) / 10.0;
            int randomGradebookNumber = (int) (Math.random() * ((max - min) + 1) + min);

            studentsList.add(
                    new Student.Builder()
                    .name(arrayName[randomName])
                    .grade(randomGrade)
                    .gradebookNumber(randomGradebookNumber)
                    .build()
            );
        }

        return studentsList;
    }
}
