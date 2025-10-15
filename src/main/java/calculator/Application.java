package calculator;
import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.List;
public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        String input = Console.readLine();
        List<String> delimiters = new ArrayList<>(List.of(",", ":"));

        // 커스텀 문자 존재 확인 (커스텀 문자는 한 글자여야 한다)
        if (input.startsWith("//")){
            if(input.length() < 5){
                throw new IllegalArgumentException("커스텀 입력값이 잘못되었습니다: " + input);
            }
            String custom_end = input.substring(3,5);
            if(custom_end.equals("\\n")){
                delimiters.add(input.substring(2,3));
                input = input.substring(5);
            }else{
                throw new IllegalArgumentException("커스텀 입력값이 잘못되었습니다: " + custom_end);
            }
        }

        // 구분자를 기준으로 덧셈
        String regex = "[" + String.join("", delimiters) + "]";
        String[] nums = input.split(regex);
        int sum = 0;
        try {
            for(String str_num : nums){
                int n = Integer.parseInt(str_num);
                sum = Math.addExact(sum, n);
            }
        }catch (NumberFormatException nfe){
            throw new IllegalArgumentException("구분자+int형 정수 이외의 값이 입력되었습니다.");
        }catch (ArithmeticException ae){
            throw new IllegalArgumentException("총합의 범위가 int를 초과했습니다");
        }
        System.out.println("결과 : " + sum);
    }
}
