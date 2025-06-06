import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		int storeIndex;
		
		// 1. 시스템 초기화
		Scanner sc = new Scanner(System.in); // 사용자 입력용 스캐너
		CentralProcessingUnit cpu = new CentralProcessingUnit(); // CPU 생성
		TextUserInterface tui = new TextUserInterface(cpu); // UI와 CPU 연결

		Bus bus = new Bus(tui); // Bus에 UI 연결

		Compiler compiler = new Compiler(cpu.getMemory(), bus); // 컴파일러에 메모리, 버스 연결
		cpu.setBus(bus); // CPU에 버스 연결 (양방향)

		// 2. 사용자 입력 루프
		while (true) {
			System.out.print("수식을 입력하세요 (예: 1 + 2): "); // 추후 UI 출력으로 대체 예정
			String input = sc.nextLine(); // 사용자 입력 수식 받기
			
			// 3. 종료 조건
			if(input.equals("0")) {
				System.out.println("인간 시대의 끝이 도래했 다."); // 추후 UI 출력으로 대체 예정
				break;
			}

			// 4. 입력 형식 검사 및 표준화 (정규표현식 기반 유효성 검증)
			input = InputValidator.validateAndNormalize(input);
			if(input == null) {
				System.out.println("다시 입력하세요"); // UI로 대체
				continue;
			}
			
			// 5. 컴파일 단계: 수식을 명령어/데이터로 해석하여 메모리에 저장
			storeIndex = compiler.compile(input);

			// 6. 실행 단계: CPU가 메모리의 명령어를 순차적으로 실행
			cpu.run(); 
			
			// 7. 계산 결과 출력
			System.out.println(input + " = " + cpu.getMemory()[storeIndex].getValue());
		} // end while
		
		sc.close(); // 스캐너 자원 해제
	} // end main()
}
