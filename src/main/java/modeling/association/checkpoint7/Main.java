package modeling.association.checkpoint7;

import java.util.Vector;

class Student{
    private String name;
    private Vector<Transcript> transcripts;

    public Student(String name) {
        this.name = name;
        this.transcripts = new Vector<Transcript>();
    }

    public void addTranscript(Transcript transcript) {
        this.transcripts.add(transcript);
    }

    public Vector<Course> getCourses() {
        Vector<Course> courses = new Vector<Course>();
        for (Transcript transcript : transcripts) {
            courses.add(transcript.getCourse());
        }
        return courses;
    }
}

class Course{
    private String name;
    private Vector<Transcript> transcripts;

    public Course(String name) {
        this.name = name;
        this.transcripts = new Vector<Transcript>();
    }

    public void addTranscript(Transcript transcript) {
        this.transcripts.add(transcript);
    }

    public String getName() {
        return name;
    }

    public Vector<Student> getStudents() {
        Vector<Student> students = new Vector<Student>();
        for (Transcript transcript : transcripts) {
            students.add(transcript.getStudent());
        }
        return students;
    }
}

class Transcript{
    private Student student;
    private Course course;
    private String date;
    private String grade;

    public Transcript(Student student, Course course) {
        this.student = student;
        this.student.addTranscript(this);
        this.course = course;
        this.course.addTranscript(this);
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGrade() {
        return grade;
    }
}


public class Main {
    public static void main(String[] args) {
        Student s1 = new Student("학생1");
        Student s2 = new Student("학생2");

        Course se = new Course("Software Engineering");
        Course dp = new Course("Design Pattern");

        Transcript t1 = new Transcript(s1, se); //학생1이 소프트웨어공학 수강
        Transcript t2 = new Transcript(s1, dp); //학생1이 디자인패턴 수강
        Transcript t3 = new Transcript(s2, dp); //학생2가 디자인패턴 수강

        //학생1의 수강 학기와 학점 입력(소프트웨어공학에서 2025년 1학기에 A+취득, 디자인패턴에서 2024년 2학기에 A취득)
        t1.setDate("2025-1");
        t1.setGrade("A+");
        t2.setDate("2024-2");
        t2.setGrade("A");
        //학생2의 수강 학기와 학점 입력(디자인패턴에서 2025년 2학기에 B취득)
        t3.setDate("2025-2");
        t3.setGrade("B");

        //학생1의 수강 내역 출력
        Vector<Course> courses = s1.getCourses();
        for (Course course : courses) {
            System.out.println(course.getName());
        }

    }
}
