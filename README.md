<h1>OOP - 클래스 간 관계</h1>
<hr />

<h2>1. 연관 (Association)</h2>
<details>
  <summary>설명 보기</summary>
  <ul>
    <li>클래스들이 개념상 서로 연결되었음을 나타냄</li>
    <li>보통은 한 클래스가 다른 클래스에서 제공하는 메소드를 사용하는 상황</li>
    <li>클래스 다이어그램에서 <strong>실선</strong>이나 <strong>화살표</strong>로 표시</li>
    <li>수명은 독립적이며, 방향(단방향/양방향)과 다중성(1, 0..*, 1..*)을 가짐</li>
  </ul>
</details>

<details>
  <summary>예시 (Java)</summary>
<pre><code class="language-java">
class Customer { private String name; }
class Order {
    private Customer customer;
    public Order(Customer customer) { this.customer = customer; }
}
</code></pre>
</details>

<details>
  <summary>Mermaid UML</summary>
<pre><code class="language-mermaid">
classDiagram
    class Customer
    class Order
    Order --> Customer : Association
</code></pre>
</details>

<hr />

<h2>2. 일반화 (Generalization)</h2>
<details>
  <summary>설명 보기</summary>
  <ul>
    <li>상속 관계를 의미하며, <strong>IS-A 관계</strong>라고도 함</li>
    <li>상위 클래스가 하위 클래스의 공통 속성과 동작을 정의</li>
    <li>클래스 다이어그램에서 <strong>속이 빈 삼각형 화살표</strong>로 표시</li>
  </ul>
</details>

<details>
  <summary>예시 (Java)</summary>
<pre><code class="language-java">
class Vehicle { void move() {} }
class Car extends Vehicle { @Override void move() { /* ... */ } }
</code></pre>
</details>

<details>
  <summary>Mermaid UML</summary>
<pre><code class="language-mermaid">
classDiagram
    class Vehicle {
        +move()
    }
    class Car {
        +move()
    }
    Vehicle <|-- Car : Generalization
</code></pre>
</details>

<hr />

<h2>3. 집합 (Aggregation & Composition)</h2>
<details>
  <summary>설명 보기</summary>
  <ul>
    <li><strong>Aggregation</strong>: 전체와 부분이 느슨하게 연결, 부분은 독립적으로 존재 (빈 마름모 ◊)</li>
    <li><strong>Composition</strong>: 전체가 사라지면 부분도 함께 소멸 (채워진 마름모 ◆)</li>
  </ul>
</details>

<details>
  <summary>Aggregation 예시 (Java)</summary>
<pre><code class="language-java">
class Player {}
class Team {
    private List&lt;Player&gt; members = new ArrayList&lt;&gt;();
    public void add(Player p) { members.add(p); }
}
</code></pre>
</details>

<details>
  <summary>Aggregation UML</summary>
<pre><code class="language-mermaid">
classDiagram
    class Player
    class Team
    Team o-- Player : Aggregation
</code></pre>
</details>

<details>
  <summary>Composition 예시 (Java)</summary>
<pre><code class="language-java">
class OrderLine {}
class Order {
    private final List&lt;OrderLine&gt; lines = new ArrayList&lt;&gt;();
    public void addLine(OrderLine l) { lines.add(l); }
}
</code></pre>
</details>

<details>
  <summary>Composition UML</summary>
<pre><code class="language-mermaid">
classDiagram
    class Order
    class OrderLine
    Order *-- OrderLine : Composition
</code></pre>
</details>

<hr />

<h2>4. 의존 (Dependency)</h2>
<details>
  <summary>설명 보기</summary>
  <ul>
    <li>한 클래스가 다른 클래스의 기능을 <strong>잠깐</strong> 사용하는 관계</li>
    <li>연관과 달리 관계가 <strong>일시적</strong> (메서드 실행 동안 유지)</li>
    <li>클래스 다이어그램에서 <strong>점선 화살표</strong>로 표시</li>
  </ul>
</details>

<details>
  <summary>예시 (Java)</summary>
<pre><code class="language-java">
class PaymentService {
    void pay(Processor processor, long amount) {
        processor.process(amount); 
    }
}
interface Processor { void process(long amount); }
</code></pre>
</details>

<details>
  <summary>Mermaid UML</summary>
<pre><code class="language-mermaid">
classDiagram
    class PaymentService {
        +pay(processor, amount)
    }
    class Processor {
        +process(amount)
    }
    PaymentService ..> Processor : Dependency
</code></pre>
</details>

<hr />

<h2>5. 실체화 (Realization)</h2>
<details>
  <summary>설명 보기</summary>
  <ul>
    <li><strong>인터페이스</strong>(책임들의 집합)와 이를 구현한 클래스 간의 관계</li>
    <li>구현 클래스는 인터페이스가 정의한 책임을 실제로 수행</li>
    <li>클래스 다이어그램에서 <strong>빈 삼각형 + 점선</strong>으로 표시</li>
  </ul>
</details>

<details>
  <summary>예시 (Java)</summary>
<pre><code class="language-java">
interface Repository { void save(Object o); }
class MemoryRepository implements Repository {
    @Override public void save(Object o) { /* ... */ }
}
</code></pre>
</details>

<details>
  <summary>Mermaid UML</summary>
<pre><code class="language-mermaid">
classDiagram
    class Repository {
        +save(o)
    }
    class MemoryRepository {
        +save(o)
    }
    Repository <|.. MemoryRepository : Realization
</code></pre>
</details>

<hr />

<h2>📌 UML 표기 요약</h2>
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
    <tr><td>집합 (Aggregation)</td><td>전체-부분 (독립)</td><td>빈 마름모 o--</td></tr>
    <tr><td>합성 (Composition)</td><td>전체-부분 (종속)</td><td>채워진 마름모 *--</td></tr>
    <tr><td>의존 (Dependency)</td><td>일시적 사용</td><td>점선 ..></td></tr>
    <tr><td>실체화 (Realization)</td><td>인터페이스 구현</td><td>빈 삼각형 점선 <|..</td></tr>
  </tbody>
</table>
