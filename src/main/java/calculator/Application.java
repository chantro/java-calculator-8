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
            delimiters.add(input.substring(2,3));
            input = input.substring(5);
        }

        // 구분자를 기준으로 덧셈
        String regex = "[" + String.join("", delimiters) + "]";
        String[] nums = input.split(regex);
        int sum = 0;
        for(String str_num : nums){
            int n = Integer.parseInt(str_num);
            sum += n;
        }
        System.out.println("결과 : " + sum);
    }
}
