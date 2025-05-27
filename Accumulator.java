package pck01;

public class Accumulator extends Register {
    /*
     * Accumulator (ACC) 클래스
     * - Register를 상속받아 연산 결과를 임시로 저장하는 누산기 역할 수행
     * - 자체 연산은 하지 않으며, ALU와의 데이터 전달에 사용
     * - 클래스 이름으로 역할을 명확히 하기 위한 선언
     * - 구조적 통일성과 가독성을 위한 설계
     *
     * 사용 예시:
     * - 연산 전 피연산자를 ALU로 전달
     * - 연산 결과를 ALU로부터 받아 저장
     * - 이후 MBR로 결과 전달
     */

    public Accumulator() {
        super();
    }
}
