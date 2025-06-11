package pck01;

import java.util.List;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Map;

public class Compiler {
    private final int MAX_MEMORY = 16; // 메모리 최대용량
    private int commandCount, operandCount, commandFindIndex; 
    // 메모리 인덱스: commandCount는 뒤에서부터 command저장, operator는 앞에서부터 피연산자 저장
    // commandFindIndex는 command가 operand를 찾을 인덱스
    private Register[] memory; // 가상 메모리
    private Bus bus; // 컴파일러와 CPU 간 통신 버스

    public Compiler(Register[] memory, Bus bus) {
        this.memory = memory; // 메모리 참조
        this.bus = bus; // 버스 참조
    }

    /**
     * 수식 문자열을 컴파일하는 메소드 1. 문자열을 토큰(숫자/연산자)으로 분리 2. 중위표현식을 후위표현식으로 변환 (연산자 우선순위 처리)
     * 3. 후위표현식 순회하며 메모리에 숫자와 명령어를 저장
     */
    public int compile(String inputString) {
        commandCount = 0; // 명령어는 메모리 앞쪽부터 저장
        operandCount = MAX_MEMORY - 1; // 피연산자는 메모리 뒤쪽부터 저장
        commandFindIndex = operandCount; // 데이터를 찾아가기 때문에 마찮가지로 뒤쪽부터

        // 입력 수식을 공백 기준으로 분리 (사전에 normalizeString으로 공백을 일정하게 만드는 것이 좋음)
        String[] tokens = inputString.split(" ");

        // 중위 수식을 후위표기법으로 변환
        List<String> postfix = toPostfix(tokens);
        
        // 시작 : 먼저 메모리에서 데이터 가져오기
        bus.compToReg("load " + commandFindIndex-- , commandCount, memory[commandCount++]);

        // 후위표기법 수식을 순회하며 명령어/데이터를 메모리에 저장
        for (String token : postfix) {
            if (isNumber(token)) {
                // 숫자이면 메모리 뒤쪽부터 저장
                bus.compToReg(token, operandCount, memory[operandCount--]);
            } else {
                // 연산자이면 메모리 앞쪽부터 저장
                switch (token) {
                case "+":
                    bus.compToReg("add " + commandFindIndex-- , commandCount, memory[commandCount++]);
                    break;
                case "-":
                    bus.compToReg("sub " + commandFindIndex-- , commandCount, memory[commandCount++]);
                    break;
                case "*":
                    bus.compToReg("mul " + commandFindIndex-- , commandCount, memory[commandCount++]);
                    break;
                case "/":
                    bus.compToReg("div " + commandFindIndex-- , commandCount, memory[commandCount++]);
                    break;
                case "%":
                    bus.compToReg("mod " + commandFindIndex-- , commandCount, memory[commandCount++]);
                    break;
                default:
                    break;
                }
            }
            if (commandFindIndex < commandCount) {
                throw new IllegalStateException("메모리 초과: 명령어/피연산자가 충돌함");
            }
        }
        
        // 종료 : 마지막으로 메모리에 저장
        bus.compToReg("store " + commandFindIndex , commandCount, memory[commandCount++]);
        
        
        // STORE 한 위치(index) 반환
        return commandFindIndex;

    } // compile_end

    /**
     * 중위 표기법 수식을 후위 표기법으로 변환하는 메소드 연산자 우선순위를 고려하여 Stack으로 처리
     */
    private List<String> toPostfix(String[] tokens) {
        List<String> output = new ArrayList<>();
        Stack<String> opStack = new Stack<>();

        // 연산자 우선순위 정의
        Map<String, Integer> precedence = Map.of("+", 1, "-", 1, "*", 2, "/", 2, "%", 2);

        for (String token : tokens) {
            if (isNumber(token)) {
                output.add(token);
            } else {
                // 스택에 있는 연산자의 우선순위가 현재 연산자보다 크거나 같으면 팝하여 출력
                while (!opStack.isEmpty() && precedence.get(token) <= precedence.get(opStack.peek())) {
                    output.add(opStack.pop());
                }
                opStack.push(token);
            }
        }

        // 남은 연산자 스택에서 모두 꺼내기
        while (!opStack.isEmpty()) {
            output.add(opStack.pop());
        }

        return output;
    }

    /**
     * 문자열이 숫자인지 확인하는 메소드
     */
    private boolean isNumber(String s) {
        return s.matches("\\d+");
    }
}
