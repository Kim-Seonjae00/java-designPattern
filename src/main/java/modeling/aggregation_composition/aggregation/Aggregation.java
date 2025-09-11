package modeling.aggregation_composition.aggregation;

import java.util.*;

/**
 * Aggregation: 외부에서 만든 부품을 주입
 * Multiplicity:
 * - CPU:        1..*
 * - MainBoard:  1
 * - Memory:     2..4
 * - PowerSupply:1
 */

class CPU {}
class MainBoard {}
class Memory {}
class PowerSupply {}

class Computer {
    private final List<CPU> cpus;           // 1..*
    private final MainBoard mainBoard;      // 1
    private final List<Memory> memories;    // 2..4
    private final PowerSupply powerSupply;  // 1

    public Computer(List<CPU> cpus, MainBoard mainBoard, List<Memory> memories, PowerSupply powerSupply) {
        if (cpus == null || cpus.isEmpty())
            throw new IllegalArgumentException("CPU must be at least 1 (1..*)");
        if (memories == null || memories.size() < 2 || memories.size() > 4)
            throw new IllegalArgumentException("Memory must be between 2 and 4");

        this.cpus = Collections.unmodifiableList(new ArrayList<>(cpus));
        this.mainBoard = Objects.requireNonNull(mainBoard);
        this.memories = Collections.unmodifiableList(new ArrayList<>(memories));
        this.powerSupply = Objects.requireNonNull(powerSupply);
    }

    public int cpuCount() { return cpus.size(); }
    public int memoryCount() { return memories.size(); }
}

public class Aggregation {
    public static void main(String[] args) {
        List<CPU> cpus = Arrays.asList(new CPU(), new CPU());       // 1..*
        List<Memory> mems = Arrays.asList(new Memory(), new Memory()); // 2..4
        MainBoard mb = new MainBoard();
        PowerSupply psu = new PowerSupply();

        Computer pc = new Computer(cpus, mb, mems, psu);
        pc = null; // Computer 참조는 끊겼지만, 부품들은 main 변수들이 들고 있어 여전히 살아있음 → 재사용 가능 (집약)
    }
}
