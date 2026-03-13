package org.example.collection;

import org.example.model.Student;
import org.example.packageSort.SortedClass;

import java.util.*;
import java.util.function.Consumer;

public class StudentsList extends AbstractList<Student>
implements List<Student>, RandomAccess, Cloneable, java.io.Serializable, Iterable<Student>
{
    private final SortedClass sorter = new SortedClass();
    private boolean isSorted = false;
    //текущий размер
    private int size = 0;
    private int defaultSize = 10;
    private Student[] elementData = {};
    private int cursor;

    public StudentsList(){
        cursor = 0;
        resize(defaultSize);
    }

    public StudentsList(StudentsList students) {
        Student[] buf = students.toArray();
        size = buf.length;
        if (size != 0) {
            elementData = Arrays.copyOf(buf, size, Student[].class);
        } else {
            // replace with empty array.
            elementData = new Student[defaultSize];
        }
    }

    //метод для добавления студента в определенное место
    @Override
    public void add(int index, Student element) {
        if(element!=null) {
            isSorted = false;
            addStudent(element, index);
            //super.add(index, element);
        }
    }
    //добавление студента в конец
    @Override
    public boolean add(Student student){
        if(student!=null) {
            addStudent(student, cursor);
            cursor++;
            return true;
        }else
            return false;
    }
    //метод для добавления студента (вспомогательный)
    private void addStudent(Student student, Integer index){
        if((index == elementData.length)){
            //надо расширять массив
            elementData = resize(elementData.length+1);
        }
        elementData[index]=new Student.Builder()
                .name(student.getName())
                .grade(student.getGrade())
                .gradebookNumber(student.getGradebookNumber())
                .build();
        size++;
    }
    //метод для расширения массива
    private Student[] resize(int size){
        int oldSize = elementData.length;
        if(oldSize>0) {
            elementData = Arrays.copyOf(elementData, size);
            return elementData;
        }
        else
            return elementData = new Student[Math.max(defaultSize, size)];
    }
    //метод для удаления студента
    @Override
    public Student remove(int index) {
        if(index<0 || index>=elementData.length){
            throw new IndexOutOfBoundsException();
        }else {
            int newSize = size-1;
            if(newSize >index){
                System.arraycopy(elementData, index+ 1, elementData, index, newSize - index);
            }
            size = newSize;
            var object = elementData[index];
            elementData[index]=null;
            return object;
        }
    }
    //действие применяемое к каждому элементу коллекции
    @Override
    public void forEach(Consumer<? super Student> action) {
        Objects.requireNonNull(action);
        for(int i=0; i<size; i++){
            action.accept(elementData[i]);
        }
    }
    //метод для определения размера коллекции
    @Override
    public int size() {
        return this.size;
    }
    //метод для определения пустая ли коллекция
    @Override
    public boolean isEmpty() {
        return (size == 0);
    }
    //метод для получения индекса студента
    public int indexOf(Student student) {
        int index = -1;
        for(int i=0; i<size; i++){
            if(elementData[i].equals(student)){
                index = i;
                break;
            }
        }
        return index;
    }
    //метод для установки значения в определенный индекс
    @Override
    public Student set(int index, Student element) {
        elementData[index]=element;
        return elementData[index];
    }
    //получить студента по индексу
    @Override
    public Student get(int index) {
        if(index<0 || index>(elementData.length-1))
            return null;
        else
            return elementData[index];
    }
    //очистка списка
    @Override
    public void clear() {
        isSorted = false;
        for(int i=0; i<size; i++){
            elementData[i]=null;
        }
        size=0;
        //super.clear();
    }
    //преобразование в массив
    @Override
    public Student[] toArray() {
        return Arrays.copyOf(elementData, size);
    }
    //получение последнего индекса вхождения
    public int lastIndexOf(Student student) {
        int index = -1;
        for(int i=0; i<size; i++){
            if(elementData[i].equals(student)){
                index = i;
            }
        }
        return index;
    }
    /*
    @Override
    public ListIterator<Student> listIterator() {
        return new ArrayList.ListItr(0);
    }

    @Override
    public ListIterator<Student> listIterator(int index) {
        return new Itr();
    }
    */
    //метод для извлечения под-массива
    //fromIndex - начальный индекс, с которого начинается под-массив
    //toIndex - индекс, где должен под-массив заканчивается (элемент по этому элементу входит в под-массив)
    @Override
    public List<Student> subList(int fromIndex, int toIndex) {
        Student[] sublist = new Student[toIndex - fromIndex];
        System.arraycopy(elementData, fromIndex, sublist, 0, toIndex - fromIndex);
        return Arrays.stream(sublist).toList();
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

    public void sortByEvenGrade() {
        sorter.sortedGradeEvenOnly(this);
        isSorted = true;
    }

    public void sortByEvenNumberGradebook() {
        sorter.sortedGradebookNumberEvenOnly(this);
        isSorted = true;
    }
}
