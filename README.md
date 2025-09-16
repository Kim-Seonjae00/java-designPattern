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
