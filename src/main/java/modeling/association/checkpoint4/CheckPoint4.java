package modeling.association.checkpoint4;

import java.util.ArrayList;
import java.util.List;

// Professor 클래스
class Professor {
    // 지도하는 학생들 (role: -student)
    private Student student;

    public  void setStudent(Student student) {
        this.student = student;
        this.student.setAdvisor(this); // 반대편 관계도 설정
    }
    public void getAdvise() {
       System.out.println("상담내용은 여기애...");
    }
}

// Student 클래스
class Student {
    private Professor advisor;

    public void setAdvisor(Professor advisor) {
        this.advisor = advisor;
    }

    public void setAdvise(String msg) {
       System.out.println(msg);
    }

}

// 테스트
public class CheckPoint4 {
    public static void main(String[] args) {
        Professor prof = new Professor();
        Student s1 = new Student();

        prof.setStudent(s1);
        prof.getAdvise();
        s1.setAdvise("상담할 내용"); //입력받아
    }
}
