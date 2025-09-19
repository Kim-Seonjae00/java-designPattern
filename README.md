<h1>OOP와 디자인패턴</h1>

<details>
  <summary><h2>1. OOP - 클래스 간 관계</h2></summary>
  <details>
    <summary>1. 연관 (Association)</summary>
    <ul>
      <li>클래스들이 개념상 서로 연결되었음을 나타냄</li>
      <li>보통은 한 클래스가 다른 클래스에서 제공하는 메소드를 사용하는 상황</li>
      <li>클래스 다이어그램에서 <strong>실선</strong>이나 <strong>화살표</strong>로 표시</li>
      <li>수명은 독립적이며, 방향(단방향/양방향)과 다중성(1, 0..*, 1..*)을 가짐</li>
    </ul>

<pre><code class="language-java">
class Customer { private String name; }

class Order {
    private Customer customer; // 연관관계: Order는 Customer를 참조
    public Order(Customer customer) { this.customer = customer; }
}
</code></pre>
  </details>

  <details>
    <summary>2. 일반화 (Generalization)</summary>
    <ul>
      <li>상속 관계를 의미하며, <strong>IS-A 관계</strong>라고도 함</li>
      <li>상위 클래스가 하위 클래스의 공통 속성과 동작을 정의</li>
      <li>클래스 다이어그램에서 <strong>속이 빈 삼각형 화살표</strong>로 표시</li>
    </ul>

<pre><code class="language-java">
class Vehicle { void move() {} }

class Car extends Vehicle {
    @Override void move() { System.out.println("Car driving"); }
}
</code></pre>
  </details>

  <details>
    <summary>3. 집합 (Aggregation & Composition)</summary>
    <ul>
      <li>클래스들 사이의 <strong>전체-부분</strong> 관계</li>
      <li><strong>Aggregation</strong>: 전체와 부분이 느슨하게 연결, 부분은 독립적으로 존재 (빈 마름모 ◊)</li>
      <li><strong>Composition</strong>: 전체가 사라지면 부분도 함께 소멸 (채워진 마름모 ◆)</li>
    </ul>

  <details>
      <summary>Aggregation 예시 (Java)</summary>
<pre><code class="language-java">
class Player {
    private final String name;
    public Player(String name) { this.name = name; }
    public String getName() { return name; }
}

class Team {
    private java.util.List&lt;Player&gt; members = new java.util.ArrayList&lt;&gt;();
    public void add(Player p) { members.add(p); }
    public void listMembers() {
        members.forEach(m -&gt; System.out.println(m.getName()));
    }
}

public class AggregationDemo {
    public static void main(String[] args) {
        Player p1 = new Player("Alice");
        Player p2 = new Player("Bob");
        Team t = new Team();
        t.add(p1);
        t.add(p2);
        t.listMembers();
        System.out.println("Player 재사용: " + p1.getName());
    }
}
</code></pre>
    </details>

  <details>
      <summary>Composition 예시 (Java)</summary>
<pre><code class="language-java">
class OrderLine {
    private final String product;
    public OrderLine(String product) { this.product = product; }
    public String getProduct() { return product; }
}

class Order {
    private final java.util.List&lt;OrderLine&gt; lines = new java.util.ArrayList&lt;&gt;();
    public void addLine(String product) {
        lines.add(new OrderLine(product));
    }
    public void listLines() {
        lines.forEach(l -&gt; System.out.println(l.getProduct()));
    }
}

public class CompositionDemo {
    public static void main(String[] args) {
        Order order = new Order();
        order.addLine("노트북");
        order.addLine("마우스");
        order.listLines();
    }
}
</code></pre>
    </details>
  </details>

  <details>
    <summary>4. 의존 (Dependency)</summary>
    <ul>
      <li>한 클래스가 다른 클래스의 기능을 <strong>잠깐</strong> 사용하는 관계</li>
      <li>연관과 달리 관계가 <strong>일시적</strong> (메서드 실행 동안 유지)</li>
      <li>클래스 다이어그램에서 <strong>점선 화살표</strong>로 표시</li>
    </ul>

<pre><code class="language-java">
interface Processor { void process(long amount); }

class PaymentService {
    void pay(Processor processor, long amount) {
        processor.process(amount); 
    }
}
</code></pre>
  </details>

  <details>
    <summary>5. 실체화 (Realization)</summary>
    <ul>
      <li><strong>인터페이스</strong>와 이를 구현한 클래스 간의 관계</li>
      <li>구현 클래스는 인터페이스가 정의한 책임을 실제로 수행</li>
      <li>클래스 다이어그램에서 <strong>빈 삼각형 + 점선</strong>으로 표시</li>
    </ul>

<pre><code class="language-java">
interface Repository { void save(Object o); }

class MemoryRepository implements Repository {
    @Override public void save(Object o) {
        System.out.println("Saved: " + o);
    }
}
</code></pre>
  </details>
</details>

<details>
  <summary><h2>2. 객체지향 원리</h2></summary>

  <details>
    <summary>1. 추상화 (Abstraction)</summary>
    <ul>
      <li>어떤 영역에서 공통적으로 중요한 속성과 행위를 <strong>추출</strong>하는 작업</li>
      <li>불필요한 세부사항은 감추고 본질적인 특징만 드러냄</li>
      <li>예: 자동차를 승객 수, 문의 개수로 추상화 → 세단, SUV, 승합차 등 구체적 객체로 분류</li>
      <li>추상 타입을 통해 동일한 메서드를 호출할 수 있고, 실제 동작은 구체적 객체마다 다르게 실행됨</li>
    </ul>

<pre><code class="language-java">
abstract class Vehicle {
    abstract void move();
}

class Car extends Vehicle {
    @Override void move() { System.out.println("🚗 자동차가 달립니다."); }
}

class Plane extends Vehicle {
    @Override void move() { System.out.println("✈️ 비행기가 납니다."); }
}

public class AbstractionDemo {
    public static void main(String[] args) {
        Vehicle v1 = new Car();
        Vehicle v2 = new Plane();
        v1.move();
        v2.move();
    }
}
</code></pre>
  </details>

  <details>
    <summary>2. 캡슐화 (Encapsulation)</summary>
    <ul>
      <li>데이터와 메서드를 <strong>클래스</strong>라는 단위로 묶는다.</li>
      <li><strong>정보은닉</strong>으로 외부에서 내부 상태를 직접 건드릴 수 없고, 공개된 메서드만 사용한다.</li>
      <li>이를 통해 <strong>높은 응집도</strong>와 <strong>낮은 결합도</strong>를 달성한다.</li>
      <li>클래스끼리 결합도가 높으면 한 클래스가 변경될 시 다른 클래스도 변경해야 할 가능성이 커지므로 객체지향 측면에서 좋지 않다.</li>
    </ul>

<p><strong>❌ 나쁜 예시</strong></p>
<pre><code class="language-java">
class BankAccount {
  public int balance; 
}

class App {
  public static void main(String[] args) {
    BankAccount acc = new BankAccount();
    acc.balance = -9999; 
  }
}
</code></pre>

<p><strong>✅ 좋은 예시</strong></p>
<pre><code class="language-java">
class BankAccount {
  private int balance;

  public void deposit(int amount) {
    if (amount &lt;= 0) throw new IllegalArgumentException();
    balance += amount;
  }

  public void withdraw(int amount) {
    if (balance &lt; amount) throw new IllegalStateException("잔액 부족");
    balance -= amount;
  }

  public int getBalance() { return balance; }
}

class App {
  public static void main(String[] args) {
    BankAccount acc = new BankAccount();
    acc.deposit(1000);
    acc.withdraw(300);
    System.out.println(acc.getBalance());
  }
}
</code></pre>
  </details>
  <details>
  <summary>3. 일반화 관계 (상속, Generalization)</summary>
  <ul>
    <li><strong>일반화는 또 다른 캡슐화</strong><br/>
      여러 클래스의 공통 속성과 행위를 상위 클래스로 추출 → 코드 중복 제거 + 추상화 효과</li>
    <li><strong>일반화 관계와 위임</strong><br/>
      - 상속은 <em>is-a</em> 관계로 강한 결합을 형성<br/>
      - 위임(Delegation)은 <em>has-a</em> 관계로 느슨한 결합 유지<br/>
      - 일반화는 재사용성이 크지만 변경에 취약할 수 있으므로 상황에 맞게 선택</li>
    <li><strong>집합론 관점</strong><br/>
      - 상위 클래스는 더 큰 집합, 하위 클래스는 부분 집합<br/>
      - 예: Animal ⊇ Dog ⊇ Bulldog</li>
  </ul>

<pre><code class="language-java">
class Animal {
    void eat() { System.out.println("먹는다"); }
    void sleep() { System.out.println("잔다"); }
}

// 일반화: Dog is-a Animal
class Dog extends Animal {
    void bark() { System.out.println("멍멍"); }
}

// 위임 예시 (has-a 관계)
class Person {
    private final Dog dog = new Dog(); // 합성/위임
    void walkDog() { dog.bark(); }
}

public class GeneralizationDemo {
    public static void main(String[] args) {
        Dog d = new Dog();
        d.eat();     // Animal에서 상속받은 메서드
        d.bark();    // Dog 고유 메서드

        Person p = new Person();
        p.walkDog(); // 위임을 통해 Dog 기능 사용
    }
}
</code></pre>
</details>
<details>
  <summary>4. 다형성 (Polymorphism)</summary>
  <ul>
    <li><strong>정의</strong>: 동일한 메서드 호출이지만, 실제 객체 타입에 따라 다른 동작을 수행하는 성질</li>
    <li><strong>오버라이드와 관계</strong>: 
      - 오버라이드(메서드 재정의)는 다형성을 실현하는 수단<br/>
      - 오버라이드 없이는 다형성이 발생할 수 없음</li>
    <li><strong>예</strong>: 상위 클래스의 메서드를 하위 클래스들이 각각 다르게 구현 → 실행 시점에 객체 타입에 맞는 메서드가 호출됨</li>
  </ul>

<pre><code class="language-java">
class Animal {
    void speak() { System.out.println("..."); }
}

class Dog extends Animal {
    @Override void speak() { System.out.println("멍멍"); }
}

class Cat extends Animal {
    @Override void speak() { System.out.println("야옹"); }
}

public class PolymorphismDemo {
    public static void main(String[] args) {
        Animal a1 = new Dog();
        Animal a2 = new Cat();
        
        // 같은 메서드 호출이지만 실제 객체 타입에 따라 다르게 동작
        a1.speak(); // 멍멍
        a2.speak(); // 야옹
    }
}
</code></pre>
</details>
<details>
  <summary>5. 상속 규칙 (Peter Coad)</summary>
  <ul>
    <li><strong>IS-A 규칙</strong><br/>
      상속은 "X is a kind of Y" 관계일 때만 사용한다.<br/>
      예: Dog is a kind of Animal ✅, Car is a kind of Engine ❌</li>
    <li><strong>100% 규칙</strong><br/>
      하위 클래스는 반드시 상위 클래스의 성격을 100% 충족해야 한다.<br/>
      일부만 해당되면 상속이 아니라 다른 관계(집약/합성/위임)를 고려해야 한다.</li>
    <li><strong>속성/행위 공통성 규칙</strong><br/>
      상속은 여러 클래스들의 <em>공통 속성과 행위</em>를 일반화하여 추출할 때 사용한다.</li>
    <li><strong>행위 일관성 규칙</strong><br/>
      하위 클래스가 상위 클래스의 행위를 <em>정상적으로 수행할 수 있어야</em> 한다.<br/>
      즉, 상속이 깨지면 Liskov Substitution Principle(LSP) 위반.</li>
    <li><strong>변경 안정성 규칙</strong><br/>
      상위 클래스 변경이 하위 클래스에 과도한 영향을 주지 않아야 한다.<br/>
      변경 파급 효과가 크면 상속보다는 합성(Composition)이 낫다.</li>
  </ul>

<pre><code class="language-java">
// ✅ 올바른 상속 (is-a kind of)
class Animal { void speak() {} }
class Dog extends Animal { @Override void speak() { System.out.println("멍멍"); } }

// ❌ 잘못된 상속 (Car is-a kind of Engine ? → No!)
class Engine {}
class Car extends Engine { } // 부적절, Car has-a Engine 이 맞음
</code></pre>
</details>

</details>



<details>
  <summary><h2>3. SOLID 원칙</h2></summary>
<details>
  <summary>1. SRP (단일 책임 원칙, Single Responsibility Principle)</summary>

  <ul>
    <li><strong>정의</strong>: 한 클래스는 단 하나의 책임만 가져야 하며, 변경 사유는 하나여야 한다.</li>
    <li><strong>문제 상황</strong>: 하나의 클래스가 여러 책임을 가지면 응집도가 떨어지고, 변경에 취약해진다.</li>
  </ul>

  <p><strong>❌ 잘못된 예시 (Student 클래스가 너무 많은 책임을 가짐)</strong></p>
<pre><code class="language-java">
public class Student {
    public void getCourses() {}
    public void addCourse() {}
    public void save() {}
    public Student load() { return this; }
    public void printOnReportCard() {}
    public void printOnAttendanceBook() {}
}
</code></pre>

  <ul>
    <li>위 <code>Student</code> 클래스는 과목 관리, 데이터베이스 접근, 출력까지 모두 포함 → 책임 과다</li>
    <li>변경 사유가 너무 많음: 학생 고유 정보, DB 스키마 변경, 출력 형식 변화</li>
    <li>책임이 많아질수록 코드 간 결합도가 높아지고 유지보수 어려움</li>
  </ul>

  <p><strong>✅ 개선 방향</strong></p>
  <ul>
    <li><code>Student</code>: 학생 고유 정보 및 수강 과목 관리 책임만 담당</li>
    <li><code>StudentDAO</code>: 데이터베이스 저장/로드 책임 담당</li>
    <li><code>ReportPrinter</code>, <code>AttendancePrinter</code>: 출력 형식 책임 분리</li>
  </ul>

  <hr/>

  <h3>🚨 산탄총 수술 (Shotgun Surgery) 문제</h3>
  <ul>
    <li>한 클래스에 여러 책임을 몰아넣는 경우 외에도, <strong>하나의 책임이 여러 클래스에 분산</strong>된 경우 문제가 발생</li>
    <li>예: 로깅, 보안, 트랜잭션 같은 <strong>횡단 관심사(Cross-Cutting Concern)</strong></li>
    <li>부가 기능이 여러 핵심 기능에 흩어져 있으면 변경 시 모든 클래스 수정 필요 → 에러 위험 증가</li>
  </ul>

  <p><strong>✅ 해결 방법</strong></p>
  <ul>
    <li>부가 기능을 별도의 클래스로 분리 → 응집도 강화</li>
    <li>방법 예시:
      <ul>
        <li>단순 Util/Logger 클래스 작성</li>
        <li>전략(Strategy) 패턴 적용</li>
        <li><strong>AOP (Aspect-Oriented Programming)</strong>: 횡단 관심사를 핵심 코드와 분리, 필요한 지점에서 자동 실행</li>
      </ul>
    </li>
  </ul>

</details>
<details>
  <summary>2. OCP (개방-폐쇄 원칙, Open-Closed Principle)</summary>
  <ul>
    <li><strong>정의</strong>: 클래스는 <strong>확장에는 열려(Open)</strong> 있고, <strong>변경에는 닫혀(Closed)</strong> 있어야 한다.</li>
    <li>즉, 기존 코드를 수정하지 않고도 새로운 기능을 추가할 수 있도록 설계해야 한다.</li>
  </ul>

  <h3>핵심 아이디어</h3>
  <ul>
    <li>무엇이 <strong>변하는지</strong>와 무엇이 <strong>변하지 않는지</strong>를 구분한다.</li>
    <li>예: 
      <ul>
        <li>변하는 것 → 출력 매체 (성적표, 출석부, 도서관 대여 명부 등)</li>
        <li>변하지 않는 것 → "학생 정보를 출력한다"는 추상적 행위</li>
      </ul>
    </li>
  </ul>

  <h3>OCP 위반 예시</h3>
  <pre><code class="language-java">
class SomeClient {
    public void doWork(String type) {
        if ("report".equals(type)) {
            System.out.println("[Report] 출력");
        } else if ("attendance".equals(type)) {
            System.out.println("[Attendance] 출력");
        } else if ("library".equals(type)) {
            System.out.println("[Library Rental] 출력");
        }
    }
}
  </code></pre>
  <p>→ 새로운 매체가 추가될 때마다 <code>SomeClient</code>를 수정해야 하므로 OCP 위반</p>

  <h3>OCP 준수 예시 (전략 패턴 활용)</h3>
  <pre><code class="language-java">
interface RecordPrinter { void print(); }

class ReportPrinter implements RecordPrinter {
    public void print() { System.out.println("[Report] 출력"); }
}

class AttendancePrinter implements RecordPrinter {
    public void print() { System.out.println("[Attendance] 출력"); }
}

class LibraryRentalPrinter implements RecordPrinter {
    public void print() { System.out.println("[Library Rental] 출력"); }
}

class SomeClient {
    private final RecordPrinter printer;
    public SomeClient(RecordPrinter printer) { this.printer = printer; }
    public void doWork() { printer.print(); }
}
  </code></pre>
  <p>→ <code>SomeClient</code>는 인터페이스만 알고, 새로운 매체는 클래스를 추가하면 됨 (OCP 준수)</p>

  <h3>OCP와 테스트</h3>
  <ul>
    <li>OCP를 지키면 기존 코드가 안정적이므로 테스트 코드 수정이 최소화된다.</li>
    <li>전략(인터페이스)을 통해 Mock 객체를 주입할 수 있어 단위 테스트가 쉬워진다.</li>
    <li>OCP를 위반하면 조건문/분기마다 테스트 케이스를 수정해야 해 유지보수성이 떨어진다.</li>
  </ul>
</details>


<details>
  <summary>3. LSP (리스코프 치환 원칙, Liskov Substitution Principle)</summary>
  <ul>
    <li><strong>정의</strong>: 자식 클래스는 최소한 자신의 부모 클래스에서 가능한 행위는 수행할 수 있어야 한다.</li>
    <li>LSP를 만족한다면, 부모 클래스의 인스턴스를 자식 클래스 인스턴스로 대체해도 프로그램 의미가 변하지 않는다.</li>
    <li>즉, 상속 관계에서는 <strong>IS-A 관계</strong>가 성립해야 한다.</li>
  </ul>

  <h3>올바른 예시 ✅</h3>
  <pre><code class="language-java">
class Bird {
    public void fly() {
        System.out.println("새가 난다!");
    }
}

class Sparrow extends Bird {
    @Override
    public void fly() {
        System.out.println("참새가 빠르게 난다!");
    }
}

public class LSPDemo {
    public static void main(String[] args) {
        Bird bird = new Sparrow(); // 부모 타입으로 자식 대체
        bird.fly(); // 정상 동작 (LSP 만족)
    }
}
  </code></pre>
  <p>→ <code>Sparrow</code>는 <code>Bird</code>가 가진 행위를 모두 올바르게 수행할 수 있으므로 LSP 만족</p>

  <h3>잘못된 예시 ❌</h3>
  <pre><code class="language-java">
class Bird {
    public void fly() {
        System.out.println("새가 난다!");
    }
}

class Penguin extends Bird {
    @Override
    public void fly() {
        throw new UnsupportedOperationException("펭귄은 날 수 없다!"); 
    }
}

public class LSPViolation {
    public static void main(String[] args) {
        Bird bird = new Penguin(); // 부모 타입으로 자식 대체
        bird.fly(); // 예외 발생 → 프로그램 의미가 깨짐 (LSP 위반)
    }
}
  </code></pre>
  <p>→ <code>Penguin</code>은 부모 클래스의 계약을 깨뜨리므로 LSP 위반</p>

  <h3>펭귄 문제 해결 (인터페이스 활용) 🐧</h3>
  <pre><code class="language-java">
// 새라는 공통 개념
abstract class Bird { }

// "날 수 있는 새"는 별도의 인터페이스로 분리
interface Flyable {
    void fly();
}

class Sparrow extends Bird implements Flyable {
    @Override
    public void fly() {
        System.out.println("참새가 난다!");
    }
}

class Penguin extends Bird {
    // Flyable을 구현하지 않음 → 날 수 없는 새를 자연스럽게 표현
}

public class LSPFixed {
    public static void main(String[] args) {
        Flyable sparrow = new Sparrow();
        sparrow.fly();
        Bird penguin = new Penguin();
        // penguin.fly()를 강요하지 않음 → 계약 위반 없음
    }
}
  </code></pre>
  <p>→ <code>Bird</code>는 "새"의 일반적 개념만 표현하고, 
     <code>Flyable</code> 인터페이스를 통해 "날 수 있는 새"만 따로 구분함.</p>

  <h3>핵심 요약</h3>
  <ul>
    <li>LSP는 부모의 계약을 자식이 어기지 않도록 하는 원칙.</li>
    <li>잘못된 상속 관계는 <strong>인터페이스 분리</strong>나 <strong>구성(Composition)</strong>으로 해결할 수 있다.</li>
    <li>부모 클래스 설계를 더 추상화하여 계약을 명확히 하는 것이 중요하다.</li>
  </ul>
</details>

<details>
  <summary>4. ISP (인터페이스 분리 원칙, Interface Segregation Principle)</summary>
  <ul>
    <li><strong>정의</strong>: 클라이언트는 자신이 사용하지 않는 메서드에 의존하지 않아야 한다.</li>
    <li>즉, <strong>인터페이스는 작게, 구체적으로 분리</strong>해야 하며, 불필요한 기능을 강요하면 안 된다.</li>
    <li>하나의 큰 인터페이스보다 여러 개의 작은 인터페이스가 더 낫다.</li>
  </ul>

  <h3>잘못된 예시 ❌</h3>
  <pre><code class="language-java">
// 너무 큰 인터페이스
interface Machine {
    void print();
    void scan();
    void fax();
}

// 어떤 클라이언트는 print만 필요
class SimplePrinter implements Machine {
    @Override
    public void print() { System.out.println("문서 출력"); }
    @Override
    public void scan() { throw new UnsupportedOperationException(); }
    @Override
    public void fax() { throw new UnsupportedOperationException(); }
}
  </code></pre>
  <p>→ <code>SimplePrinter</code>는 사용하지 않는 <code>scan</code>, <code>fax</code> 메서드를 억지로 구현해야 하므로 ISP 위반</p>

  <h3>올바른 예시 ✅</h3>
  <pre><code class="language-java">
// 인터페이스를 기능별로 분리
interface Printer {
    void print();
}

interface Scanner {
    void scan();
}

interface Fax {
    void fax();
}

// 클라이언트는 자신에게 필요한 인터페이스만 구현
class SimplePrinter implements Printer {
    @Override
    public void print() { System.out.println("문서 출력"); }
}

class MultiFunctionPrinter implements Printer, Scanner, Fax {
    @Override
    public void print() { System.out.println("문서 출력"); }
    @Override
    public void scan() { System.out.println("문서 스캔"); }
    @Override
    public void fax() { System.out.println("팩스 전송"); }
}
  </code></pre>
  <p>→ <code>SimplePrinter</code>는 필요한 <code>Printer</code>만, 
     <code>MultiFunctionPrinter</code>는 여러 인터페이스를 조합해 사용. ISP 준수</p>

  <h3>핵심 요약</h3>
  <ul>
    <li>인터페이스는 클라이언트 맞춤형으로 작게 분리해야 한다.</li>
    <li>불필요한 의존성을 줄여 시스템을 더 유연하고 유지보수하기 쉽게 만든다.</li>
    <li>큰 인터페이스 = 강한 결합 / 작은 인터페이스 = 낮은 결합</li>
  </ul>
</details>

<details>
  <summary>5. DIP (의존 역전 원칙, Dependency Inversion Principle)</summary>
  <ul>
    <li><strong>정의</strong>: 고수준 모듈(정책, 비즈니스 로직)은 저수준 모듈(구현 세부사항)에 의존하지 않아야 한다.</li>
    <li>둘 다 <strong>추상화(인터페이스, 추상 클래스)</strong>에 의존해야 한다.</li>
    <li>즉, "구체적인 클래스에 의존하지 말고, 추상화에 의존하라."</li>
  </ul>

  <h3>위반 예시 ❌</h3>
  <pre><code class="language-java">
// 고수준 모듈: 결제 서비스
class PaymentService {
    private final CreditCardProcessor processor = new CreditCardProcessor();
    public void pay(long amount) {
        processor.process(amount); // 특정 구현체에 직접 의존
    }
}

// 저수준 모듈: 구체 구현
class CreditCardProcessor {
    public void process(long amount) {
        System.out.println("신용카드 결제: " + amount);
    }
}
  </code></pre>
  <p>→ <code>PaymentService</code>가 <code>CreditCardProcessor</code>에 직접 의존 → DIP 위반</p>

  <h3>올바른 예시 ✅</h3>
  <pre><code class="language-java">
// 추상화
interface PaymentProcessor {
    void process(long amount);
}

// 저수준 모듈: 구현체
class CreditCardProcessor implements PaymentProcessor {
    @Override
    public void process(long amount) {
        System.out.println("신용카드 결제: " + amount);
    }
}

class PayPalProcessor implements PaymentProcessor {
    @Override
    public void process(long amount) {
        System.out.println("PayPal 결제: " + amount);
    }
}

// 고수준 모듈: 추상화에 의존
class PaymentService {
    private final PaymentProcessor processor;
    public PaymentService(PaymentProcessor processor) {
        this.processor = processor; // 구체 구현체를 주입 (DI)
    }
    public void pay(long amount) {
        processor.process(amount);
    }
}

// 실행
public class DIPDemo {
    public static void main(String[] args) {
        PaymentService cardService = new PaymentService(new CreditCardProcessor());
        cardService.pay(1000);
        PaymentService paypalService = new PaymentService(new PayPalProcessor());
        paypalService.pay(2000);
    }
}
  </code></pre>
  <p>→ <code>PaymentService</code>는 <code>PaymentProcessor</code> 인터페이스에만 의존 → DIP 준수</p>

  <h3>핵심 요약</h3>
  <ul>
    <li>DIP는 고수준 모듈과 저수준 모듈 모두 <strong>추상화</strong>에 의존하게 만드는 원칙이다.</li>
    <li>이를 실현하는 기법이 <strong>DI(Dependency Injection, 의존성 주입)</strong>이다.</li>
    <li>결과적으로 유연한 확장, 테스트 용이성, 낮은 결합도를 얻을 수 있다.</li>
  </ul>
</details>


</details>
