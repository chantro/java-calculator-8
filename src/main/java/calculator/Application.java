package calculator;
import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        String input = Console.readLine().trim();
        List<String> delimiters = new ArrayList<>(List.of(",", ":"));

        // 커스텀 문자 존재 확인 (커스텀 문자는 한 글자여야 한다)
        if (input.startsWith("//")){
            if (input.length() < 4) {
                throw new IllegalArgumentException("커스텀 입력값이 잘못되었습니다: " + input);
            }
            char custom = input.charAt(2);
            int cut;
            if (input.charAt(3) == '\n') { // 실제 개행
                cut = 4;
            } else if (input.startsWith("\\n", 3)) {               // 리터럴 "\n"
                cut = 5;
            } else {
                throw new IllegalArgumentException("커스텀 입력값이 잘못되었습니다: " + input);
            }
            delimiters.add(String.valueOf(custom));
            input = input.substring(cut);
        }

        // 빈 문자열은 0 반환
        input = input.trim();
        if(input.isEmpty()){
            System.out.println("결과 : 0");
            return;
        }

        // 각 구분자 이스케이프 후, 이를 기준으로 덧셈
        String regex = String.join("|", delimiters.stream().map(Pattern::quote).toList());
        int sum = getSum(input, regex);
        System.out.println("결과 : " + sum);
    }

    private static int getSum(String input, String regex) {
        //"1,2," 와 같이 피연산자가 없으면 잘못된 입력으로 봄
        String[] nums = input.split(regex, -1);

        int sum = 0;
        try {
            for(String str_num : nums){
                str_num = str_num.trim();
                if (str_num.isEmpty()) {
                    throw new IllegalArgumentException("구분자 다음에 int형 양수가 입력되지 않았습니다.");
                }
                int n = Integer.parseInt(str_num);
                sum = Math.addExact(sum, n);
            }
        }catch (NumberFormatException nfe){
            throw new IllegalArgumentException("구분자+int형 양수 이외의 값이 입력되었습니다.");
        }catch (ArithmeticException ae){
            throw new IllegalArgumentException("총합의 범위가 int를 초과했습니다.");
        }
        return sum;
    }
}
