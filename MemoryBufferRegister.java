package pck01;

public class MemoryBufferRegister extends Register {
    /*
     * MemoryBufferRegister (MBR) 클래스
     * - Register를 상속받아 메모리 입출력 시 임시 데이터 저장 용도로 사용됨
     * - 실제 기능은 Register와 동일하며, 클래스 이름만으로 역할을 구분함
     * - 구조적 통일성과 가독성을 위한 별도 선언
     *
     * 사용 예시:
     * - memory[MAR]에서 읽은 값을 MBR에 저장
     * - 또는 MBR의 값을 memory[MAR]에 저장
     */

    public MemoryBufferRegister() {
        super();
    }
}
