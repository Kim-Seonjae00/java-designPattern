package modeling.association.checkpoint6;

import java.util.Vector;

public class Student {
    private String name;
    private Vector<Course> courses;

    public Student(String name) {
        this.name = name;
        this.courses = new Vector<Course>();
    }

    public void registerCourse(Course course) {
        courses.add(course);
        course.addStudent(this);
    }

    public void dropCourse(Course course) {
        courses.remove(course);
        course.removeStudent(this);
    }

    public Vector<Course> getCourses() {
        return courses;
    }
}
