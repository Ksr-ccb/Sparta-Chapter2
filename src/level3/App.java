package level3;

import java.util.InputMismatchException;
import java.util.Scanner;


public class App {
    public static void main(String[] args) {
        ArithmeticCalculator calculator = new ArithmeticCalculator();

        boolean exitFlag = true;
        CalculateValue[] values = new CalculateValue[2];
        OperatorType operator;

        while(exitFlag){

            //입력받음
            values[0] = calculator.inputValue(0);
            operator = calculator.inputOperator();
            values[1] = calculator.inputValue(1);

            //결과 출력
            calculator.cacultate(values[0], values[1], operator);

            //handlingRecords
            exitFlag = calculator.checkContinue();

        }
    }
}
