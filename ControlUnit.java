package pck01;

public class ControlUnit {

    // CPU 구성 요소
    private ProgramCounter pc;
    private MemoryAddressRegister mar;
    private MemoryBufferRegister mbr;
    private InstructionRegister ir;
    private Accumulator acc;
    private ArithmeticLogicUnit alu;
    private Register[] memory;
    private Bus bus;

    // UI 출력용 상태: 현재 해석 중인 명령어
    private String currentlyProcessing = "";

    // 생성자: 모든 CPU 구성 요소를 주입받아 연결
    public ControlUnit(
        ProgramCounter pc,
        MemoryAddressRegister mar,
        MemoryBufferRegister mbr,
        InstructionRegister ir,
        Accumulator acc,
        ArithmeticLogicUnit alu,
        Register[] memory
    ) {
        this.pc = pc;
        this.mar = mar;
        this.mbr = mbr;
        this.ir = ir;
        this.acc = acc;
        this.alu = alu;
        this.memory = memory;
    }
    

    public void setBus(Bus bus) {
      this.bus = bus;
    }
   

    // 현재 실행 중인 명령어 (UI용)
    public String getCurrentlyProcessing() {
        return currentlyProcessing;
    }

    /**
     * 메모리에서 값 로드 → ACC
     * - 주소 설정: memoryIndex → MAR
     * - 값 로드: memory[MAR] → MBR → ACC
     */
    private void loadMemoryToACC(int memoryIndex) {
    	    bus.valueToReg(String.valueOf(memoryIndex), mar); // 주소 → MAR
    	    bus.regToReg(memory[mar.getAddress()], mbr);      // memory[MAR] → MBR
    	    bus.regToReg(mbr, acc);                           // MBR → ACC
    	}


    /**
     * 연산자 명령어: operand1(ACC), operand2(memory[x]) → ALU
     * - operand1: ACC → ALU
     * - operand2: memory[x] → ACC → ALU
     */
    private void prepareBinaryOperation(int memoryIndex) {
        bus.regToReg(acc, alu);          // ACC → ALU (operand1)
        loadMemoryToACC(memoryIndex);    // memory[x] → ACC
        bus.regToReg(acc, alu);          // ACC → ALU (operand2)
    }

    /**
     * store 명령어 처리
     * - 주소 설정: address → MAR
     * - 값 저장: ACC → MBR → memory[MAR]
     */
    private void storeToMemory(int memoryIndex) {
        mar.setValue(String.valueOf(memoryIndex));           // 주소 → MAR
        bus.regToReg(acc, mbr);                          // ACC → MBR
        bus.writeToMemory(memory, mar, mbr);     // MBR → memory[MAR]
    }

    /**
     * CPU의 한 사이클 수행: fetch → decode → execute
     * @return false: 종료 조건(빈 명령어 or 범위 초과) 시
     */
    public boolean executeNextInstruction() {
        // 1. PC → MAR
        bus.regToReg(pc, mar);
        int address = mar.getAddress();
        if (address >= memory.length) return false; // 메모리 범위 초과 → 종료

        // 2. memory[MAR] → MBR
        bus.readFromMemory(memory, mar, mbr);

        // 3. MBR → IR
        bus.regToReg(mbr, ir);

        // 4. IR의 명령어 추출
        String command = ir.getValue();
        bus.waitForEnter("IR -> CU 해석 : " + command);
        if (command == null || command.isEmpty()) return false; // 종료 조건

        currentlyProcessing = command; // UI 표시용 저장

        // 5. 명령어 해석
        String[] parts = command.split(" ");
        String op = parts[0]; // 예: "add"
        int targetAddr = Integer.parseInt(parts[1]); // 예: "3"

        // 6. 명령 실행
        switch (op) {
            case "load":
                loadMemoryToACC(targetAddr);
                break;

            case "add":
                prepareBinaryOperation(targetAddr);
                alu.add();
                bus.regToReg(alu, acc);
                break;

            case "sub":
                prepareBinaryOperation(targetAddr);
                alu.sub();
                bus.regToReg(alu, acc);
                break;

            case "mul":
                prepareBinaryOperation(targetAddr);
                alu.mul();
                bus.regToReg(alu, acc);
                break;

            case "div":
                prepareBinaryOperation(targetAddr);
                alu.div();
                bus.regToReg(alu, acc);
                break;

            case "store":
                storeToMemory(targetAddr);
                return false;

            default:
                System.out.println("Unknown command: " + op);
                return false;
        }

        // 7. PC++
        pc.increment();
        return true;
    }
}
