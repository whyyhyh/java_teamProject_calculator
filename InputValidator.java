import java.util.regex.Pattern;

public class InputValidator {

    // 정규표현식 패턴: "숫자 [공백] 연산자 [공백] 숫자" 형식만 허용
    private static final Pattern pattern = Pattern.compile("^\\d+\\s*[-+*/%]\\s*\\d+$");

    public InputValidator() {}

    /**
     * 입력 문자열이 유효한지 확인하는 메소드
     * 1. 정규식 매칭으로 기본 형식 검증
     * 2. 나눗셈(/) 또는 나머지(%) 연산일 경우, 두 번째 숫자가 0이면 false
     */
    public static boolean isValid(String str) {
        // 정규표현식으로 형식 검사
        if (!pattern.matcher(str).matches()) 
            return false;

        // 모든 공백 제거 후 연산자 기준으로 숫자 분리
        String[] parts = str.replaceAll("\\s+", "").split("[-+*/%]");

        // 오른쪽 피연산자 (연산자의 오른쪽 숫자)
        int right = Integer.parseInt(parts[1]);

        // 연산자 추출: 왼쪽 숫자의 길이 = 연산자의 인덱스
        char op = str.replaceAll("\\s+", "").charAt(parts[0].length());

        // 나눗셈(/)이나 나머지(%)이고, 오른쪽 피연산자가 0이면 false
        if ((op == '/' || op == '%') && right == 0) return false;

        return true;
    }

    /**
     * 수식 문자열을 표준화하는 메소드
     * - 모든 공백 제거 후, 연산자 앞뒤에 공백을 넣고
     * - 최종적으로는 "숫자 연산자 숫자" 형식으로 반환
     */
    public static String normalizeString(String str) {
        if (str == null) return "";

        String normalized = str;

        // 1. 모든 공백 제거
        normalized = normalized.replaceAll("\\s*", "");

        // 2. 연산자 앞뒤에 공백 추가
        normalized = normalized.replaceAll("([+\\-*/%])", " $1 ");

        // 3. 여러 공백 → 한 칸으로 줄이기
        normalized = normalized.replaceAll("\\s+", " ");

        // 4. 양쪽 공백 제거
        normalized = normalized.trim();

        return normalized;
    }
}
