<details>
  <summary><h1>1. OOP - 클래스 간 관계</h1></summary>

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
        // Player는 외부에서 독립적으로 생성됨
        Player p1 = new Player("Alice");
        Player p2 = new Player("Bob");
        Team t = new Team();
        t.add(p1); // 주입 (재사용 가능)
        t.add(p2);
        t.listMembers();
        // Team이 사라져도 p1, p2는 살아있음
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
        // Order 내부에서 직접 생성 → 외부 재사용 불가
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
        // OrderLine은 Order 내부에서만 생성됨 → Order가 사라지면 같이 소멸
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
        processor.process(amount); // 실행 시점에만 의존
    }
}
    </code></pre>
  </details>

  <details>
    <summary>5. 실체화 (Realization)</summary>
    <ul>
      <li><strong>인터페이스</strong>(책임 집합)와 이를 구현한 클래스 간의 관계</li>
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

  <details>
    <summary>📌 UML 표기 요약</summary>
    <table>
      <thead>
        <tr>
          <th>관계</th>
          <th>설명</th>
          <th>UML 표기</th>
        </tr>
      </thead>
      <tbody>
        <tr><td>연관 (Association)</td><td>클래스 간 지속적 연결</td><td>실선 →</td></tr>
        <tr><td>일반화 (Generalization)</td><td>상속, IS-A 관계</td><td>빈 삼각형 화살표</td></tr>
        <tr><td>집합 (Aggregation)</td><td>전체-부분 (독립)</td><td>빈 마름모 ◊</td></tr>
        <tr><td>합성 (Composition)</td><td>전체-부분 (종속)</td><td>채워진 마름모 ◆</td></tr>
        <tr><td>의존 (Dependency)</td><td>일시적 사용</td><td>점선 화살표</td></tr>
        <tr><td>실체화 (Realization)</td><td>인터페이스 구현</td><td>빈 삼각형 + 점선</td></tr>
      </tbody>
    </table>
  </details>

</details>
