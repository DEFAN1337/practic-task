package org.example.collection;

import org.example.model.Student;
import org.example.packageSort.SortedClass;

import java.util.ArrayList;

public class StudentsList extends ArrayList<Student> {

    private final SortedClass sorter = new SortedClass();

    private boolean isSorted = false;

    @Override
    public boolean add(Student student) {
        isSorted = false;
        return super.add(student);
    }

    @Override
    public void add(int index, Student element) {
        isSorted = false;
        super.add(index, element);
    }

    @Override
    public void clear() {
        isSorted = false;
        super.clear();
    }

    public boolean isSorted() {
        return isSorted;
    }

    public void sortByName() {
        sorter.sortedName(this);
        isSorted = true;
    }

    public void sortByGrade() {
        sorter.sortedGrade(this);
        isSorted = true;
    }

    public void sortByNumberGradebook() {
        sorter.sortedGradebookNumber(this);
        isSorted = true;
    }
}
