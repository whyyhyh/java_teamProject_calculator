package pck01;

public class Register {
    /*
     * Register 클래스 (레지스터 기본형)
     * 
     * - CPU 내부에서 데이터를 임시로 저장하는 저장소 역할
     * - value 필드에는 정수 형태의 데이터나, 문자열 형태의 명령어가 저장
     * 
     * 예시 저장 값:
     * - 데이터: "1", "42", ...
     * - 명령어: "load 5", "add 6", "store 7", ...
     * 
     */

    // 문자열 형태의 값(데이터 또는 명령어)을 저장
    private String value;

    // 기본 생성자
    public Register() {
        // 특별한 초기값 없이 빈 레지스터 생성
    }

    // 레지스터의 값을 설정하는 메서드
    public void setValue(String value) {
        this.value = value;
    }

    // 레지스터의 현재 값을 반환하는 메서드
    public String getValue() {
        return value;
    }
}
