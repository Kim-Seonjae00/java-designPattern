package modeling.dependency;

class GasStation {
    void provideFuel() {
        System.out.println("주유소: 기름을 채워줍니다!");
    }
}

class Car {
    void drive() {
        System.out.println("자동차가 달립니다.");
    }

    // 의존 관계: refuel 메서드 실행 시점에만 GasStation 사용
    void refuel(GasStation station) {
        station.provideFuel();
        System.out.println("자동차: 주유 완료!");
    }
}

class Person {
    private final Car car; // 연관 관계: 사람은 자동차를 항상 소유

    public Person(Car car) {
        this.car = car;
    }

    void goToWork() {
        System.out.println("사람이 출근합니다.");
        car.drive();
    }

    void refuelAt(GasStation station) {
        System.out.println("사람이 주유소에 들렀습니다.");
        car.refuel(station); // Car가 GasStation에 "잠깐" 의존
    }
}

public class dependency {
    public static void main(String[] args){
        Car car = new Car();
        Person person = new Person(car);  // 연관: 사람은 자동차 소유
        GasStation station = new GasStation();

        person.goToWork();         // 자동차를 이용해 출근
        person.refuelAt(station);  // 잠깐 주유소 이용 (의존)
    }
}
