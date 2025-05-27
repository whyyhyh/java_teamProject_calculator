package pck01;

import java.util.Scanner;

// 정규표


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 1. CPU & Compiler 생성
        CentralProcessingUnit cpu = new CentralProcessingUnit();
        
        // 0. TUI 객체 생성
        TextUserInterface tui = new TextUserInterface(cpu);
        
        Bus bus = new Bus(tui);

        Compiler compiler = new Compiler(cpu.getMemory(), bus);
        cpu.setBus(bus);

        // 2. 사용자 입력 받기
        System.out.print("수식을 입력하세요 (예: 1 + 2): ");
        String input = sc.nextLine();
        
        // 아키텍쳐 변수 입력

        // 3. 컴파일
        compiler.compile(input);  // 수식 → 명령어 + 데이터 → memory에 저장

        // 4. 실행
        cpu.run();  // ControlUnit이 memory[PC]부터 한 줄씩 실행

        
        sc.close();
    }
}
