package org.example.packageInterface;

import org.example.collection.StudentsList;
import org.example.model.Student;

import java.util.List;

public interface SortProcessor {

    void sortedName(StudentsList student);

    void sortedGrade(StudentsList student);

    void sortedGradebookNumber(StudentsList student);

    void sortedGradeEvenOnly(StudentsList list);

    void sortedGradebookNumberEvenOnly(StudentsList list);
}
