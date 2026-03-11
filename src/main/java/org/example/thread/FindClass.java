package org.example.thread;

import org.example.collection.StudentsList;
import org.example.model.Student;

import java.util.List;
import java.util.stream.Collectors;

//класс реализующий методы поиска
public class FindClass{

    private StudentsList students;
    private String name = null;
    private double grade= 0.0;
    private int number = 0;
    private Student student = null;

    public FindClass(StudentsList students){
        this.students = students;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void findStudentsByGrade(){
        System.out.println("Поток "+Thread.currentThread().getName()+" запущен");
        if(grade!=0 ){
            System.out.println("Ищем студенов с оценкой '"+grade+"'");
            var result = students.stream().filter(student_->student_.getGrade() == grade).toList();
            System.out.println("Количество студентов с оценкой '" + grade + "' равно: " + result.size());
            result.forEach(System.out::println);
        }else {
            System.out.println("Нет данных для поиска");
        }
    }
    //метод для поиска студентов по номеру зачетной книжки
    public void findStudentsByNumberGrandbook(){
        System.out.println("Поток "+Thread.currentThread().getName()+" запущен");
        if(number!=0 ){
            System.out.println("Ищем студенов с номером зачетной книжки '"+number+"'");
            var result = students.stream().filter(student_->student_.getGrade() == grade).toList();
            System.out.println("Количество студентов с номером зачетной книжки '"+number+"' равно: " + result.size());
            result.forEach(System.out::println);
        }else {
            System.out.println("Нет данных для поиска");
        }
    }
    public void findStudentsByName(){
        System.out.println("Поток "+Thread.currentThread().getName()+" запущен");
        if ((!name.isEmpty()) || (name != null)) {
            System.out.println("Ищем студентов с имененм "+name);
            var result = students.stream().filter(value -> value.getName().equals(name)).toList();
            System.out.println("Количество студентов с именем '" + name + "' равно: " + result.size());
            result.forEach(System.out::println);
        } else {
            System.out.println("Нет данных для поиска");
        }

    }

    public void findStudent(Student student){

    }
}
