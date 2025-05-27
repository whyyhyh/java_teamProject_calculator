package pck01;

public class MemoryAddressRegister extends Register {
    /*
     * MemoryAddressRegister (MAR) 클래스
     * - Register를 상속받아 문자열 기반 데이터 전달 구조 유지
     * - 메모리 접근을 위한 주소값을 저장하는 용도로 사용
     * - getAddress()를 통해 정수형 주소로 변환하여 사용
     *
     * 사용 예시:
     * - CPU가 PC 값을 통해 MAR에 주소 저장
     * - 이후 memory[MAR.getAddress()]와 같은 방식으로 접근
     */

    public MemoryAddressRegister() {
        super();
    }

    // 현재 저장된 문자열 주소 값을 정수로 변환하여 반환
    public int getAddress() {
        return Integer.parseInt(getValue());
    }
}