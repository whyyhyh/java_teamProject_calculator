package pck01;

public class ProgramCounter extends Register {
    /*
     * ProgramCounter (PC) 클래스
     * - Register를 상속받아 문자열 기반 값 저장 구조 유지
     * - 현재 실행 중인 명령어의 위치(주소)를 저장
     * - 정수형 주소 계산을 위한 getter/setter 및 증가 메서드 제공
     *
     * 사용 예시:
     * - fetch 단계에서 PC 값을 MAR로 이동
     * - 명령어 실행 후 increment() 호출로 다음 주소로 이동
     */

    public ProgramCounter() {
        super();
        setValue("0"); // 초기 PC 값은 0
    }

    // 현재 PC 값을 정수형으로 반환
    public int getAsInt() {
        return Integer.parseInt(getValue());
    }

    // 정수형으로 PC 값을 설정
    public void set(int value) {
        setValue(String.valueOf(value));
    }

    // PC 값을 1 증가시킴 (다음 명령어 위치로 이동)
    public void increment() {
        set(getAsInt() + 1);
    }
}
