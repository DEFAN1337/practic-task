package org.example.packageInterface;

import org.example.model.Student;

import java.util.List;

public interface SortProcessor {

    void sortedName(List<Student> student);

    void sortedGrade(List<Student> student);

    void sortedGradebookNumber(List<Student> student);
}
