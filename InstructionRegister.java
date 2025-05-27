package pck01;

public class InstructionRegister extends Register {
    /*
     * InstructionRegister (IR) 클래스
     * - Register를 상속받아 현재 실행할 명령어를 임시로 저장함
     * - 실제 기능은 Register와 동일하며, 클래스 이름으로 역할을 명확히 구분
     * - 구조적 통일성과 가독성 확보를 위한 선언
     *
     * 사용 예시:
     * - fetch 단계에서 MBR의 명령어를 IR로 복사
     * - 이후 CU가 IR의 값을 해석하여 실행 제어
     */

    public InstructionRegister() {
        super();
    }
}
