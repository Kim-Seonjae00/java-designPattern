package modeling.realization;

interface Flyable {
    void fly();
}

class Bird implements Flyable {
    @Override
    public void fly() {
        System.out.println("새가 하늘을 납니다.");
    }
}

class Plane implements Flyable {
    @Override
    public void fly() {
        System.out.println("비행기가 하늘을 납니다.");
    }
}

public class Realization {
    public static void main(String[] args) {
        Flyable f1 = new Bird();
        Flyable f2 = new Plane();

        f1.fly();
        f2.fly();
    }
}
