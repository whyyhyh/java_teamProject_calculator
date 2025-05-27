package pck01;

public class ArithmeticLogicUnit extends Register {
    /*
     * ALU 클래스
     * - Register를 상속받아 Bus를 통한 데이터 이동을 가능하게 함
     * - setValue(String)를 오버라이딩하여 입력값을 내부 피연산자(operand)로 처리
     * - 실제 연산 결과만 부모 Register의 value에 저장
     * 
     * 구조:
     * - operand1: 첫 번째 피연산자
     * - operand2: 두 번째 피연산자
     * - result: 연산 결과
     * - isFirst: true이면 operand1에 저장, false이면 operand2에 저장
     */

    private int operand1;
    private int operand2;
    private int result;
    private boolean isFirst = true; // operand1 → operand2 순서 제어용

    // 기본 생성자
    public ArithmeticLogicUnit() {}

    // 덧셈 연산: operand1 + operand2 결과를 result에 저장
    public void add() {
        result = operand1 + operand2;
        saveResult();
    }

    // 뺄셈 연산: operand1 - operand2 후 결과 저장
    public void sub() {
        result = operand1 - operand2;
        saveResult();
    }

    // 곱셈 연산: operand1 * operand2 후 결과 저장
    public void mul() {
        result = operand1 * operand2;
        saveResult();
    }

    // 나눗셈 연산: operand1 / operand2 후 결과 저장
    public void div() {
        result = operand1 / operand2;
        saveResult();
    }

    // 연산 결과를 부모 클래스(Register)의 value 필드에 문자열로 저장
    public void saveResult() {
        super.setValue(Integer.toString(result));
    }

    /**
     * 오버라이딩된 setValue(String)
     * - Bus로부터 전달된 값이 이 메서드를 통해 들어옴
     * - 내부적으로 operand1 또는 operand2에 저장됨
     * - 실제 Register의 value 필드는 이 과정에서는 사용되지 않음
     * - 연산 결과만 value에 저장됨 (saveResult() 호출 시)
     */
    @Override
    public void setValue(String value) {
        if (isFirst) {
            operand1 = Integer.parseInt(value);
        } else {
            operand2 = Integer.parseInt(value);
        }
        isFirst = !isFirst; // 다음 setValue는 다른 operand로 들어가게 제어
    }
}