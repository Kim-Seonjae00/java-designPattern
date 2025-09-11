package modeling.association.checkpoint5;
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
    }

    public void dropCourse(Course course) {
        courses.remove(course);
    }

    public Vector<Course> getCourses() {
        return courses;
    }
}
