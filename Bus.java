
import java.util.Scanner;

public class Bus {

	private String temparary;
	private TextUserInterface tui;
	private final Scanner scanner = new Scanner(System.in);

	public Bus(TextUserInterface tui) {
		this.tui = tui;
	}

	/**
	 * 사용자에게 엔터를 누르도록 요청하여 시뮬레이션 흐름을 제어합니다.
	 * 
	 * @param stepDescription 현재 버스 동작에 대한 설명
	 */
	// 공통 대기 메시지
	public void waitForEnter(String stepDescription) {
		tui.displayStructure();
		System.out.println("[BUS] " + stepDescription + " (Press Enter to continue...)"); // 출력 메시지 형태 차이
		scanner.nextLine(); // 사용자가 Enter 칠 때까지 대기
	}

	/**
	 * 레지스터 간 값 이동
	 * 
	 * sender → accepter 레지스터 간에 값을 전달합니다. (Register to Register) 데이터 버스의 역할을
	 * 시뮬레이션합니다.
	 *
	 * @param sender   값을 보내는 레지스터
	 * @param accepter 값을 받는 레지스터
	 */
	public void regToReg(Register sender, Register accepter) {
		if (sender == null || accepter == null) { // Null 체크 로직 추가
			System.err.println("[Bus Error] regToReg: Sender or accepter register is null.");
			return;
		}
		temparary = sender.getValue();
		accepter.setValue(temparary);
		waitForEnter(sender.getClass().getSimpleName() + " → " + accepter.getClass().getSimpleName() + " (Value: '"
				+ temparary + "')"); // 메시지에 실제 값 포
	}

	/**
	 * 컴파일러가 생성한 명령어/데이터를 CPU의 메모리(Register)에 직접 로드합니다.
	 * 
	 * @param compiled 명령어 또는 데이터 문자열 (Compiler에서 제공)
	 * @param memory   값을 받을 메모리 셀 (Register 객체)
	 */
	public void compToReg(String compiled, Register memory) {
		if (memory == null) { // Null 체크 로직 추가
			System.err.println("[Bus Error] compToReg: Target memory register is null.");
			return;
		}
		temparary = compiled;
		memory.setValue(temparary);
		waitForEnter("Compiler → Memory (Value: '" + temparary + "')"); // 메시지에 실제 값 포함

	}

	/*
	 * 제어장치에서 Mar로 index 데이터를 넘김 controlunit -> mar
	 */
	public void valueToReg(String value, Register target) {
		if (target == null) { // Null 체크 로직 추가
			System.err.println("[Bus Error] CtoR: Receiver register is null."); // (이전 답변에서 CtoR로 명명했음)
			return;
		}
		temparary = value;
		target.setValue(temparary);
		waitForEnter("Constant/ControlUnit → " + target.getClass().getSimpleName() + " (Value: '" + temparary + "')");
	}

	/**
	 * CPU가 메모리에서 데이터를 읽어오는 과정을 시뮬레이션함
	 *
	 * 
	 * @param memory CPU의 메모리 (Register 배열)
	 * @param mar    메모리 주소 레지스터 (읽을 주소 포함)
	 * @param mbr    메모리 버퍼 레지스터 (메모리에서 읽은 데이터가 일시적으로 저장될 곳)
	 */
	public void readFromMemory(Register[] memory, MemoryAddressRegister mar, MemoryBufferRegister mbr) {
		int address = -1;
		try {
			address = mar.getAddress();
		} catch (NumberFormatException e) {
			System.err.println("[Bus Error] readFromMemory: Invalid address in MAR. " + e.getMessage());
			mbr.setValue("");
			return;
		}

		System.out.println("--- MEMORY READ OPERATION ---");
		waitForEnter("    [Address Bus] Sending address " + address + " to Memory.");
		waitForEnter("    [Control Bus] Sending 'READ' signal to Memory.");

		if (address < 0 || address >= memory.length) { // 유효 주소 범위 체크 추가
			System.err.println("[Bus Error] readFromMemory: Invalid memory address " + address + " for read.");
			mbr.setValue("");
			return;
		}

		temparary = memory[address].getValue();
		mbr.setValue(temparary);

		waitForEnter("    [Data Bus] Data '" + temparary + "' transferred from Memory to MBR."); // 메시지에 실제 값 포함
		System.out.println("--- MEMORY READ COMPLETE ---");
	}

	/**
	 *
	 * @param memory CPU의 메모리 (Register 배열)
	 * @param mar    메모리 주소 레지스터 (쓸 주소 포함)
	 * @param mbr    메모리 버퍼 레지스터 (메모리에 쓸 데이터 포함)
	 */
	public void writeToMemory(Register[] memory, MemoryAddressRegister mar, MemoryBufferRegister mbr) {
		// 메모리 주소 변환 예외 처리 추가
		int address = -1;
		try {
			address = mar.getAddress();
		} catch (NumberFormatException e) {
			System.err.println("[Bus Error] writeToMemory: Invalid address in MAR. " + e.getMessage());
			return;
		}

		temparary = mbr.getValue(); // 데이터는 MBR에서 가져옴

		System.out.println("--- MEMORY WRITE OPERATION ---");
		waitForEnter("    [Address Bus] Sending address " + address + " to Memory.");
		waitForEnter("    [Data Bus] Sending data '" + temparary + "' from MBR to Memory."); // 메시지에 실제 값 포함
		waitForEnter("    [Control Bus] Sending 'WRITE' signal to Memory.");

		if (address < 0 || address >= memory.length) { // 유효 주소 범위 체크 추가
			System.err.println("[Bus Error] writeToMemory: Invalid memory address " + address + " for write.");
			return;
		}

		memory[address].setValue(temparary);
		waitForEnter("");
		System.out.println("--- MEMORY WRITE COMPLETE ---");
	}
}