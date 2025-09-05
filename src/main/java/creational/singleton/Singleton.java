package creational.singleton;

//싱글턴 패턴(Singleton Pattern)
//클래스의 인스턴스를 오직 하나만 생성, 어디서든 해당 인스턴트에 접근할 수 있도록 하는 패턴
//주로 데이터베이스 연결 관리, 로그 기록, 스레드 풀 등과 같이 시스템에서 단 하나만 존재해야만 하는 객체에서 사용됨.
//핵심 구현 원리
//1. 생성자를 private로 선언하여 외부에서 new 키워드로 직접 생성되는걸 방지
//2. static 필드에 유일한 인스턴스 저장
//3. static 메서드를 통해 해당 인스턴스 반환

public class Singleton {
    // 2. 유일한 인스턴스를 저장할 static 변수
    private static Singleton instance;

    // 1. 생성자를 private으로 선언
    private Singleton() {
        System.out.println("싱글턴 인스턴스 생성됨!");
    }

    // 3. 인스턴스를 반환하는 public static 메서드
    @SuppressWarnings("InstantiationOfUtilityClass")
    public static Singleton getInstance() {
        // 인스턴스가 null일 경우에만 새로 생성
        if (instance == null) instance = new Singleton();
        return instance;
    }
}
