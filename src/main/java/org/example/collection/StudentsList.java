package org.example.collection;

import org.example.model.Student;
import org.example.packageInterface.SortProcessor;
import org.example.packageSort.SortedClass;

import java.util.ArrayList;
import java.util.stream.Collector;

//пока так, наследуем его от ArrayList
public class StudentsList extends ArrayList<Student> {

    //признак, что данные отсортированны
    private boolean isSorted = false;
    SortProcessor sorted = new SortedClass();

    public boolean add(Student student) {
        isSorted = false;
        return super.add(student);
    }

    public boolean isSorted() {
        return isSorted;
    }

    public void add(int index, Student element) {
        isSorted = false;
        super.add(index, element);
    }

    public boolean remove(Student student) {
        return super.remove(student);
    }

    public void sortByName(){
        sorted.sortedName(this);
        isSorted = true;
    }

    public void sortByGrade(){
        sorted.sortedGrade(this);
        isSorted = true;
    }

    public void sortByNumberGradebook(){
        sorted.sortedGradebookNumber(this);
        isSorted = true;
    }
    public void clearSorted(){
        isSorted = false;
    }
}
