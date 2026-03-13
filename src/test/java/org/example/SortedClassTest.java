package org.example;

import org.example.collection.StudentsList;
import org.example.model.Student;
import org.example.packageSort.SortedClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SortedClassTest {

    @Test
    void testSortStudentsByNameAlphabetically() {

        StudentsList list = new StudentsList();

        list.add(new Student.Builder().name("Petr").gradebookNumber(111111).grade(4.0).build());
        list.add(new Student.Builder().name("Anna").gradebookNumber(222222).grade(5.0).build());
        list.add(new Student.Builder().name("Ivan").gradebookNumber(333333).grade(3.0).build());

        SortedClass sorter = new SortedClass();
        sorter.sortedName(list);

        Assertions.assertEquals("Anna", list.get(0).getName());
        Assertions.assertEquals("Ivan", list.get(1).getName());
        Assertions.assertEquals("Petr", list.get(2).getName());
    }

    @Test
    void testSortStudentsByGradeDescending() {

        StudentsList list = new StudentsList();

        list.add(new Student.Builder().name("Ivan").gradebookNumber(111111).grade(3.0).build());
        list.add(new Student.Builder().name("Petr").gradebookNumber(222222).grade(5.0).build());
        list.add(new Student.Builder().name("Anna").gradebookNumber(333333).grade(4.0).build());

        SortedClass sorter = new SortedClass();
        sorter.sortedGrade(list);

        Assertions.assertEquals(5.0, list.get(0).getGrade());
        Assertions.assertEquals(4.0, list.get(1).getGrade());
        Assertions.assertEquals(3.0, list.get(2).getGrade());
    }

    @Test
    void testSortStudentsByGradebookNumberAscending() {

        StudentsList list = new StudentsList();

        list.add(new Student.Builder().name("Ivan").gradebookNumber(333333).grade(4.0).build());
        list.add(new Student.Builder().name("Petr").gradebookNumber(111111).grade(5.0).build());
        list.add(new Student.Builder().name("Anna").gradebookNumber(222222).grade(3.0).build());

        SortedClass sorter = new SortedClass();
        sorter.sortedGradebookNumber(list);

        Assertions.assertEquals(111111, list.get(0).getGradebookNumber());
        Assertions.assertEquals(222222, list.get(1).getGradebookNumber());
        Assertions.assertEquals(333333, list.get(2).getGradebookNumber());
    }

    @Test
    void testSortEvenGradesOnly() {

        StudentsList list = new StudentsList();

        list.add(new Student.Builder().name("Ivan").gradebookNumber(111111).grade(4.0).build()); // четная
        list.add(new Student.Builder().name("Petr").gradebookNumber(222222).grade(3.0).build()); // нечетная
        list.add(new Student.Builder().name("Anna").gradebookNumber(333333).grade(2.0).build()); // четная
        list.add(new Student.Builder().name("Oleg").gradebookNumber(444444).grade(5.0).build()); // нечетная
        list.add(new Student.Builder().name("Svetlana").gradebookNumber(555555).grade(4.0).build()); // четная

        SortedClass sorter = new SortedClass();
        sorter.sortedGradeEvenOnly(list);

        Assertions.assertEquals(4.0, list.get(0).getGrade());
        Assertions.assertEquals(3.0, list.get(1).getGrade());
        Assertions.assertEquals(4.0, list.get(2).getGrade());
        Assertions.assertEquals(5.0, list.get(3).getGrade());
        Assertions.assertEquals(2.0, list.get(4).getGrade());
    }

    @Test
    void testSortEvenGradebookNumbersOnly() {

        StudentsList list = new StudentsList();

        list.add(new Student.Builder().name("Ivan").gradebookNumber(333333).grade(4.0).build());
        list.add(new Student.Builder().name("Svetlana").gradebookNumber(666666).grade(5.0).build());
        list.add(new Student.Builder().name("Anna").gradebookNumber(111111).grade(3.0).build());
        list.add(new Student.Builder().name("Oleg").gradebookNumber(444445).grade(4.0).build());
        list.add(new Student.Builder().name("Petr").gradebookNumber(222222).grade(5.0).build());

        SortedClass sorter = new SortedClass();
        sorter.sortedGradebookNumberEvenOnly(list);

        Assertions.assertEquals(333333, list.get(0).getGradebookNumber());
        Assertions.assertEquals(222222, list.get(1).getGradebookNumber());
        Assertions.assertEquals(111111, list.get(2).getGradebookNumber());
        Assertions.assertEquals(444445, list.get(3).getGradebookNumber());
        Assertions.assertEquals(666666, list.get(4).getGradebookNumber());
    }
}