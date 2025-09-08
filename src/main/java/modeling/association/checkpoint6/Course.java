package modeling.association.checkpoint6;

import java.util.Vector;

public class Course {
    private int id;
    private String name;
    private Vector<Student> students;

    public Course(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public void addStudent(Student student) {
        students.add(student);
    }
    public void removeStudent(Student student) {
        students.remove(student);
    }

    public Vector<Student> getStudents(){
        return students;
    }
}
