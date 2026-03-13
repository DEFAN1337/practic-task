package org.example.test;

import org.example.collection.StudentsList;
import org.example.model.Student;
import org.example.packageSort.SortedClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SortedClassTest {

    private SortedClass sortedClass;
    private StudentsList studentsList;


    Student student1 = new Student(new Student.Builder().name("Ольга").grade(5.0).gradebookNumber(100001));
    Student student2 = new Student(new Student.Builder().name("Олег").grade(3.9).gradebookNumber(100004));
    Student student3 = new Student(new Student.Builder().name("Арина").grade(4.4).gradebookNumber(100006));
    Student student4 = new Student(new Student.Builder().name("Алина").grade(4.6).gradebookNumber(100003));
    Student student5 = new Student(new Student.Builder().name("Асия").grade(4.1).gradebookNumber(100002));

    StudentsList expectedList = new StudentsList();

    @Before
    public void setUp() {
        sortedClass = new SortedClass();
        studentsList = new StudentsList();
    }

    @Test
    public void sortedName() {

        // ввод данных для теста сортировки по имени

        studentsList.add(student1);
        studentsList.add(student2);
        studentsList.add(student3);
        studentsList.add(student4);
        studentsList.add(student5);

        // вызов метода который сортирует студентов по имени
        sortedClass.sortedName(studentsList);

        // ожидаемый результат

        expectedList.add(student4);
        expectedList.add(student3);
        expectedList.add(student5);
        expectedList.add(student2);
        expectedList.add(student1);

        /* так как у нас это List студентов, запускаем цикл для сверки по каждому индексу */
        for (int i = 0; i < expectedList.size(); i++) {
            assertEquals(expectedList.get(i).getName(), studentsList.get(i).getName());
        }
    }

    @Test
    public void sortedGrade() {

        // ввод данных для теста сортировки по оценочному баллу
        studentsList.add(student1);
        studentsList.add(student2);
        studentsList.add(student3);
        studentsList.add(student4);
        studentsList.add(student5);

        // вызов метода который сортирует студентов по оценочному баллу
        sortedClass.sortedGrade(studentsList);

        // ожидаемый результат
        expectedList.add(student1);
        expectedList.add(student4);
        expectedList.add(student3);
        expectedList.add(student5);
        expectedList.add(student2);

        /* так как у нас это List студентов, запускаем цикл для сверки по каждому индексу */
        for (int i = 0; i < expectedList.size(); i++) {
            assertEquals(expectedList.get(i).getGrade(), studentsList.get(i).getGrade(), 0);
        }
    }

    @Test
    public void sortedGradebookNumber() {

        // ввод данных для теста сортировки по номеру зачетной книжки
        studentsList.add(student1);
        studentsList.add(student2);
        studentsList.add(student3);
        studentsList.add(student4);
        studentsList.add(student5);

        // вызов метода который сортирует студентов по номеру зачетной книжки
        sortedClass.sortedGradebookNumber(studentsList);

        // ожидаемый результат
        expectedList.add(student1);
        expectedList.add(student5);
        expectedList.add(student4);
        expectedList.add(student2);
        expectedList.add(student3);

        /* так как у нас это List студентов, запускаем цикл для сверки по каждому индексу */
        for (int i = 0; i < expectedList.size(); i++) {
            assertEquals(expectedList.get(i).getGradebookNumber(), studentsList.get(i).getGradebookNumber());
        }
    }

    @Test
    public void sorted_Grade() {

        // ввод данных для теста сортировки по имени
        studentsList.add(student1);
        studentsList.add(student2);
        studentsList.add(student3);
        studentsList.add(student4);
        studentsList.add(student5);

        // вызов метода который сортирует студентов по студенческому билету
        sortedClass.sorted_Grade(studentsList);

        // ожидаемый результат
        expectedList.add(student1);
        expectedList.add(student2);
        expectedList.add(student4);
        expectedList.add(student3);
        expectedList.add(student5);

        /* так как у нас это List студентов, запускаем цикл для сверки по каждому индексу */
        for (int i = 0; i < expectedList.size(); i++) {
            assertEquals(expectedList.get(i).getGrade(), studentsList.get(i).getGrade(),0);
        }
    }

    @Test
    public void sort_GradebookNumber() {
        // ввод данных для теста сортировки по имени
        studentsList.add(student1);
        studentsList.add(student2);
        studentsList.add(student3);
        studentsList.add(student4);
        studentsList.add(student5);

        // вызов метода который сортирует студентов по студенческому билету
        sortedClass.sorted_Grade(studentsList);

        // ожидаемый результат
        expectedList.add(student1);
        expectedList.add(student5);
        expectedList.add(student2);
        expectedList.add(student4);
        expectedList.add(student3);

        /* так как у нас это List студентов, запускаем цикл для сверки по каждому индексу */
        for (int i = 0; i < expectedList.size(); i++) {
            assertEquals(expectedList.get(i).getGradebookNumber(), studentsList.get(i).getGradebookNumber());
        }
    }
}