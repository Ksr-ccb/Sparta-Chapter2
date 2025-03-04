package level2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        boolean exitFlag = true;
        while(exitFlag){
            calculator.inputValues(); // 계산할 정수 입력 받기
            calculator.inputOperator(); // 계산할 연산자 입력 받기
            
            //calculator.getResultStr();
            calculator.operate();//결과 출력
            
            exitFlag = calculator.checkContinue(); // 계산기 종료여부 + 누적 계산 결과 리스트 접근 여부


        }
    }
}
