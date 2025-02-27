package level2;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Calculator {
    Scanner sc = new Scanner(System.in);
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

    int[] inputValues(){
        for(int i = 0; i < 2; i++){
            System.out.print("연산을 진행할 "+ (i+1) +"번째 정수를 입력해주세요 : ");
            try{
                values[i] = sc.nextInt();
                if(values[i] < 0){
                    System.out.println("0 이상의 값만 입력이 가능해요.");
                    sc.nextLine();
                    i--;
                }else{
                    setValues(values[i], i);
                }
            }catch (InputMismatchException e) {
                System.out.println("정수 값을 입력해주세요!");
                sc.nextLine();
                i--;
            }
        }
        return values;
    }

    void inputOperator(){
        System.out.print(values[0] + "와" + values[1] + " 사이 계산할 사칙연산 기호를 정해주세요: ");
        char operator = sc.next().charAt(0);
        sc.nextLine();
        if(operator == '+' || operator == '-'||operator == '*'||operator == '/'){
            setOperator(operator);
        }else{
            System.out.println(operator + "은 유효한 연산 기호가 아니에요 ");
        }
        operationException();
        System.out.println("계산 결과 값은 : " + getResult() + " 입니다");
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

     boolean confirmContinue(String str) {
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

    boolean checkContinue() {
        System.out.println("//////////////////////////////////////");
        System.out.println("계산기를 계속 진행하시겠습니까?");
        System.out.print("계산을 종료하시려면 'exit' , 누적 계산 결과를 출력하시려면 'total' 을 입력하세요 : ");
        String finishStr = sc.nextLine();

        if(finishStr.equalsIgnoreCase("total")){
            deleteSavedResult();
            return checkContinue();
        }else{
            System.out.println("//////////////////////////////////////");
            return confirmContinue(finishStr);
        }
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
        String totalStr;
        for (Integer i : savedResults) {
            System.out.print(i + ", ");
        }
        System.out.println("처음 누적 결과값을 삭제할 수 있어요.");

        System.out.print("처음 누적 결과값을 삭제하려면 'delete' , 계산을 종료하시려면 'exit' 를 입력하세요 : ");
        totalStr = sc.nextLine();
        if(totalStr.equalsIgnoreCase("delete")){
            try{
                System.out.println("처음 누적 결과값 " + savedResults.get(0));
                savedResults.remove(0);
                System.out.println(" 을 삭제했어요");
            }catch (Exception e){
                System.out.println("누적 결과값을 삭제할 수 없어요.");
            }
        }
    }
}
