package org.example.thread;

import org.example.collection.StudentsList;
import org.example.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//класс для поиска
public class FindThreadClass<T> {
    //используем для синхронизации потоков
    private final Lock lockFirst = new ReentrantLock(true);
    //поток для поиска элемента
    private  Thread threadFind;
    private FindClass findClass;

    public FindThreadClass(TypeFindData typeFind, StudentsList students, T params){
        //методы для поиска данных в массиве
        findClass = new FindClass(students);
        switch (typeFind){
            case Name:
                findClass.setName((String) params);
                threadFind = new Thread(findClass::findStudentsByName, "threadFindByName");
                break;
            case Grade:
                findClass.setGrade((Integer) params);
                threadFind = new Thread(findClass::findStudentsByGrade, "threadFindByGrade");
                break;
            case NumberGradeBook:
                findClass.setNumber((Integer) params);
                threadFind = new Thread(findClass::findStudentsByNumberGrandbook, "threadFindByNumber");
                break;
        }
    }
   public void startThread(){
       //lockFirst.lock();
       threadFind.start();
       try {
           System.out.println("Ожидаем завершения потока");
           //ожидаем пока поток завершит работу
           threadFind.join();
       } catch (InterruptedException e) {
           throw new RuntimeException(e);
       }
       //lockFirst.unlock();
    }
}
