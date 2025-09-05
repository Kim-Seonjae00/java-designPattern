package creational.singleton;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SingletonTest {

    @Test
    void testSingletonInstance() {
        // 첫 번째 인스턴스 얻기
        Singleton instance1 = Singleton.getInstance();

        // 두 번째 인스턴스 얻기
        Singleton instance2 = Singleton.getInstance();

        // 두 인스턴스가 동일한 객체를 참조하는지 확인
        // 즉, Singleton 클래스의 인스턴스는 오직 하나만 존재함을 검증
        assertSame(instance1, instance2);

        // 인스턴스가 null이 아닌지도 확인
        assertNotNull(instance1);
    }
}