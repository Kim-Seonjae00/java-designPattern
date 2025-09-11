package modeling.aggregation_composition.compositon;

import java.util.*;

/**
 * Composition: Computer 내부에서 직접 부품 생성
 * Multiplicity:
 * - CPU:        1..*
 * - MainBoard:  1
 * - Memory:     2..4
 * - PowerSupply:1
 */

class CPU {
    public void dispose() { System.out.println("CPU disposed"); }
}
class MainBoard {
    public void dispose() { System.out.println("MainBoard disposed"); }
}
class Memory {
    public void dispose() { System.out.println("Memory disposed"); }
}
class PowerSupply {
    public void dispose() { System.out.println("PowerSupply disposed"); }
}

class Computer {
    private final List<CPU> cpus;
    private final MainBoard mainBoard;
    private final List<Memory> memories;
    private final PowerSupply powerSupply;

    public Computer(int cpuCount, int memoryCount) {
        if (cpuCount < 1)
            throw new IllegalArgumentException("CPU must be at least 1 (1..*)");
        if (memoryCount < 2 || memoryCount > 4)
            throw new IllegalArgumentException("Memory must be between 2 and 4");

        // Computer가 직접 부품 생성
        List<CPU> cpuList = new ArrayList<>();
        for (int i = 0; i < cpuCount; i++) cpuList.add(new CPU());
        this.cpus = Collections.unmodifiableList(cpuList);

        List<Memory> memList = new ArrayList<>();
        for (int i = 0; i < memoryCount; i++) memList.add(new Memory());
        this.memories = Collections.unmodifiableList(memList);

        this.mainBoard = new MainBoard();
        this.powerSupply = new PowerSupply();
    }

    public int cpuCount() { return cpus.size(); }
    public int memoryCount() { return memories.size(); }
}

public class Composition {
    public static void main(String[] args) {
        Computer pc = new Computer(2, 4); // CPU 2개, Memory 4개
        pc = null; //PC를 null 만들었으므로 내부 부품도 같이 사라짐(GC를 통해서 지움).

    }
}
