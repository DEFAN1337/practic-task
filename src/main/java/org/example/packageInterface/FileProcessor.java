package org.example.packageInterface;

import org.example.model.Student;

import java.io.IOException;
import java.util.List;

public interface FileProcessor {
    void processDeleteEmptyRemoverInterface (String fileName) throws IOException;

    void processReadFileInterface (List<Student> student, String fileName);

    void processWriteFileInterface (List<Student> student, boolean flag);

    void processWriteFileInterface (List<Student> student, String fileName, boolean flag);

    void processSaveFile (String fileName);

    void processDeleteTempFile();
}
