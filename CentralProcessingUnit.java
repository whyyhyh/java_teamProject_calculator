
public class CentralProcessingUnit {

    // 주요 레지스터 및 구성 요소
    private ProgramCounter pc;                   // 명령어 주소 추적
    private MemoryAddressRegister mar;           // 메모리 접근 주소 저장
    private MemoryBufferRegister mbr;            // 메모리에서 읽거나 쓸 데이터 저장
    private InstructionRegister ir;              // 현재 명령어 저장
    private Accumulator acc;                     // 연산 결과 임시 저장 (누산기)
    private ArithmeticLogicUnit alu;             // 산술/논리 연산 담당
    private ControlUnit cu;                      // 명령어 해석 및 실행 흐름 제어
    private Register[] memory;                   // 메모리(명령어/데이터 저장)
   
    /**
     * 생성자
     * - 레지스터 및 ALU 초기화
     * - memory 공간 16칸 확보
     * - CU에 모든 컴포넌트 전달
     */
    public CentralProcessingUnit() {
        this.memory = new Register[16];
        for (int i = 0; i < memory.length; i++) {
            memory[i] = new Register(); // 각 셀 초기화
        }

        this.pc = new ProgramCounter();
        this.mar = new MemoryAddressRegister();
        this.mbr = new MemoryBufferRegister();
        this.ir = new InstructionRegister();
        this.acc = new Accumulator();
        this.alu = new ArithmeticLogicUnit();
        this.cu = new ControlUnit(pc, mar, mbr, ir, acc, alu, memory);
    }

     public void setBus(Bus bus) {
       cu.setBus(bus);
     }
    
    /**
     * 외부(Compiler 등)가 메모리에 접근할 수 있도록 getter 제공
     * - 명령어/데이터 적재를 위해 필요
     */
    public Register[] getMemory() {
        return memory;
    }

    /**
     * CPU 실행 루프
     * - PC의 값 0으로 초기화.
     * - CU의 executeNextInstruction()을 반복 호출
     * - CU가 false 반환 시(종료 조건 충족), 실행 종료
     */
    public void run() {
    	pc.set(0);
        while (true) {
            boolean continueExecution = cu.executeNextInstruction();
            if (!continueExecution) break;
        }
    }

    // TUI 출력용
    public ProgramCounter getPC() { return pc; }
    public MemoryAddressRegister getMAR() { return mar; }
    public MemoryBufferRegister getMBR() { return mbr; }
    public InstructionRegister getIR() { return ir; }
    public Accumulator getACC() { return acc; }
    public ArithmeticLogicUnit getALU() { return alu; }
    public ControlUnit getCU() { return cu; }
}
