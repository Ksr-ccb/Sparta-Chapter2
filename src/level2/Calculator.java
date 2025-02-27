package level2;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
    private int[] values = new int[2];
    private int result= 0;
    private char operator;

    private List<Integer> savedResults= new ArrayList<>();

    //계산 함수
    int operation(int[] values, char operator) {
        if((values.length !=2)){
            System.out.println("계산할 정수가 올바르게 입력되지 않았어요");
        }
        switch (operator){
            case '+':
                result = (values[0] + values[1]);
                break;
            case '-':
                result = (values[0] - values[1]);
                break;
            case '*':
                result = (values[0] * values[1]);
                break;
            case '/':
                //result = (values[0] / values[1]);
                if(values[1] != 0){
                    result = (values[0] / values[1]);
                }else {
                    System.out.println("나눗셈 연산에서 분모(두번째 정수)에 0이 입력될 수 없어요");
                }
                break;
            default:
                System.out.println("사칙 연산 기호는 +,-,*,/만 사용가능해요.");
        }
        return result;
    }

    int operationChkValidation(int[] values, char operator) {
        int result= 0;

        switch (operator){
            case '+':
                result = (values[0] + values[1]);
                break;
            case '-':
                result = (values[0] - values[1]);
                break;
            case '*':
                result = (values[0] * values[1]);
                break;
            case '/':
                result = (values[0] / values[1]);
                break;
        }
        return result;
    }

    void operationException() {
        try{
            switch (operator){
                case '+':
                    result = (values[0] + values[1]);
                    System.out.println("더하기 계산 결과는 " + (values[0] + values[1]) + "입니다.");
                    savedResults.add(result);
                    break;
                case '-':
                    result = (values[0] - values[1]);
                    System.out.println("빼기 계산 결과는 " + (values[0] - values[1]) + "입니다.");
                    savedResults.add(result);
                    break;
                case '*':
                    result = (values[0] * values[1]);
                    System.out.println("곱하기 계산 결과는 " + (values[0] * values[1]) + "입니다.");
                    savedResults.add(result);
                    break;
                case '/':
                    result = (values[0] / values[1]);
                    System.out.println("나누기 계산 결과는 " + (values[0] / values[1]) + "입니다.");
                    savedResults.add(result);
                    break;
            }
        }catch (Exception e){
            System.out.println("연산을 진행할 수 없어요");
        }
    }

     boolean checkContinue(String str) {
        if(str.equalsIgnoreCase("exit")){
            System.out.println("계산기를 종료합니다.");
            return false;
        }
        else{
            System.out.println("입력된 값 : " + str);
            System.out.println("계산을 다시 시작합니다.");
            return true;
        }
    }

    int getResult() {
        return result;
    }

    void setResult(int result) {
        this.result = result;
    }

    int[] getValues() {
        return values;
    }

    void setValues(int value, int index) {
        this.values[index] = value;
    }

    char getOperator() {
        return operator;
    }
    void setOperator(char operator) {
        this.operator = operator;
    }

    List<Integer> getSavedResults() {
        return savedResults;
    }
    void deleteSavedResult(){
        savedResults.remove(0);
    }
}
