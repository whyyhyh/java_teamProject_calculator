import java.util.regex.Pattern;

public class InputValidator {

    // 정규표현식 패턴: "숫자 연산자 숫자" 형식만 허용
    private static final Pattern pattern = Pattern.compile("^\\d+(\\s*[-+*/%]\\s*\\d+)+$");

    public InputValidator() {}
    /**
     * 입력 문자열이 유효한 수식인지 검증하고, 정규화된 문자열로 반환하는 메서드
     * 
     * 1. 정규표현식을 통해 기본적인 수식 구조 검사
     * 2. '/' 또는 '%' 연산자의 경우 0으로 나누는 상황 방지
     * 3. 유효하다면 공백 정리 등 표준화된 문자열을 반환
     * 
     * @param str 입력 문자열
     * @return 유효하고 정규화된 문자열, 유효하지 않으면 null
     */
    public static String validateAndNormalize(String str) {
        // 정규표현식으로 기본적인 수식 형식(숫자 연산자 숫자 ...) 확인
        if (!pattern.matcher(str).matches()) 
            return null;

        // 공백을 모두 제거한 문자열 생성
        String noSpace = str.replaceAll("\\s+", "");

        // 숫자들만 추출 (피연산자들)
        String[] operands = noSpace.split("[-+*/%]");

        // 연산자만 추출 (모든 숫자를 제거하고 남은 것)
        String[] operators = noSpace.replaceAll("\\d+", "").split("");

        // '/' 또는 '%' 연산자 바로 뒤의 숫자가 0인지 확인 → 0으로 나누는 연산 방지
        for (int i = 0; i < operators.length; i++) {
            if ((operators[i].equals("/") || operators[i].equals("%")) 
                && Integer.parseInt(operands[i + 1]) == 0) {
                return null;
            }
        }

        // 정규화된 문자열 반환 (공백, 포맷 정리)
        return normalizeString(str);
    }

    /**
     * 수식 문자열을 표준화하는 메소드
     * - 모든 공백 제거 후, 연산자 앞뒤에 공백을 넣고
     * - 최종적으로는 "숫자 연산자 숫자" 형식으로 반환
     */
    private static String normalizeString(String str) {
        if (str == null) return "";

        String normalized = str;

        // 1. 모든 공백 제거
        normalized = normalized.replaceAll("\\s+", "");

        // 2. 연산자 앞뒤에 공백 추가
        normalized = normalized.replaceAll("([+\\-*/%])", " $1 ");

        // 3. 여러 공백 → 한 칸으로 줄이기
        normalized = normalized.replaceAll("\\s+", " ");

        // 4. 양쪽 공백 제거
        normalized = normalized.trim();

        return normalized;
    }
}
