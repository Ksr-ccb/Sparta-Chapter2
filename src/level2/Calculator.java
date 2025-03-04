package level2;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Calculator {
    Scanner sc = new Scanner(System.in);
    private int[] values = new int[2];
    private int result= 0;
    private String resultStr;
    private char operator;

    private List<Integer> savedResults= new ArrayList<>();

    void inputValues(){
        for(int i = 0; i < 2; i++){
            System.out.print("연산을 진행할 "+ (i+1) +"번째 정수를 입력해주세요 : ");
            try{
                values[i] = sc.nextInt();
                if(values[i] < 0){
                    System.out.println("0 이상의 값만 입력이 가능해요.");
                    i--;
                }else{
                    setValues(values[i], i);
                }
            }catch (InputMismatchException e) {
                System.out.println("정수 값을 입력해주세요!");
                //sc.nextLine();
                i--;
            }
            sc.nextLine();
        }
    }

    void inputOperator(){
        System.out.print(values[0] + "와" + values[1] + " 사이 계산할 사칙연산 기호를 정해주세요: ");
        String temp = sc.nextLine();

        if( operationException(temp)) {
            setOperator( (char)temp.charAt(0));
            //operationException(temp);
        }else{
            System.out.println(operator + "은 유효한 연산 기호가 아니에요 ");
        }
    }

    boolean operationException(String temp) {
        if(temp.length() != 1){
            return false;
        }else{
            char operator = temp.charAt(0);
            return (operator == '+') || (operator == '-') || (operator == '*') || (operator == '/');
        }
    }

    void operate() {
        try{
            switch (operator){
                case '+':
                    result = (values[0] + values[1]);
                    resultStr = "더하기 계산 결과는 " + result + "입니다.";
                    savedResults.add(result);
                    break;
                case '-':
                    result = (values[0] - values[1]);
                    resultStr = "빼기 계산 결과는 " + result + "입니다.";
                    savedResults.add(result);
                    break;
                case '*':
                    result = (values[0] * values[1]);
                    resultStr = "곱하기 계산 결과는 " + result + "입니다.";
                    savedResults.add(result);
                    break;
                case '/':
                    result = (values[0] / values[1]);
                    resultStr = "나누기 계산 결과는 " + result + "입니다.";
                    savedResults.add(result);
                    break;
            }
            getResultStr();
        }catch (Exception e){
            System.out.println("연산을 진행할 수 없어요. 에러를 확인해주세요 : " + e.getMessage());
        }
    }

    void getResultStr(){
        System.out.println(resultStr);
    }


     boolean confirmContinue(String str) {
        if(str.equalsIgnoreCase("exit")){
            System.out.println("계산기를 종료합니다.");
            sc.close();
            return false;
        }
        else{
            System.out.println("입력된 값 : " + str);
            System.out.println("계산을 다시 시작합니다.");
            return true;
        }
    }

    void printSavedResult() {
        for (int i=0; i<savedResults.toArray().length; i++) {
            System.out.print("[" + i + "] :" + getResult(i) + ", ");
        }
    }

    boolean checkContinue() {
        System.out.println("//////////////////////////////////////");
        System.out.println("계산기를 계속 진행하시겠습니까?");
        System.out.print("계산을 종료하시려면 'exit' , 누적 계산 결과를 출력하시려면 'total' 을 입력하세요 : ");
        String finishStr = sc.nextLine();

        if(finishStr.equalsIgnoreCase("total")){
            pickHandleRecord();
            return checkContinue();
        }else{
            System.out.println("//////////////////////////////////////");
            return confirmContinue(finishStr);
        }
    }

    void setValues(int value, int index) {

        this.values[index] = value;
    }

    void setOperator(char operator) {
        this.operator = operator;
    }

    void deleteSavedResult(int index){
        String totalStr;
        totalStr = sc.nextLine();
        if(totalStr.equalsIgnoreCase("delete")){ //int값이면
            try{
                System.out.println("처음 누적 결과값 " + getResult(index));
                savedResults.remove(index);
                System.out.println(" 을 삭제했어요");
            }catch (Exception e){
                System.out.println("누적 결과값을 삭제할 수 없어요.");
            }
        }
    }

    void handleSavedResult(int index) {
        System.out.print("수정할 값 혹은 'delete'를 입력하여 값을 변경할 수 있어요. 돌아가시려면 'back' 를 입력하세요 : ");

        String totalStr;
        totalStr = sc.nextLine();

        if(totalStr.matches("\\d+")){ //int값이면
            try{
                int value = Integer.parseInt(totalStr);
                setResult(index, value);
                System.out.println("수정을 완료했어요. 처음으로 돌아가겠습니다.");

            }catch (Exception e){
                System.out.println("누적 결과값을 불러올 수 없어요");
                System.out.println("에러 메시지 : " + e.toString());
            }
        } else if (totalStr.equalsIgnoreCase("delete")){
            deleteSavedResult(index);
        } else if (totalStr.equalsIgnoreCase("back")){
            return;
        }else {
            System.out.println("유효하지 않은 입력이에요.");
            System.out.println("/////////////////////////////////////");
        }
    }

    void pickHandleRecord(){
        printSavedResult();
        System.out.print("수정/삭제 할 결과 값의 순서를 입력하시거나 (0부터시작), 돌아가시려면 'back' 를 입력하세요 : ");

        String totalStr;
        totalStr = sc.nextLine();

        if(totalStr.matches("\\d+")){ //int값이면
            try{
                int index = Integer.parseInt(totalStr);
                System.out.println( index + "번 째 결과값 " + getResult(index));
                handleSavedResult(index);
            }catch (Exception e){
                System.out.println("누적 결과값을 불러올 수 없어요");
                System.out.println("에러 메시지 : " + e.toString());
            }
        } else if (totalStr.equalsIgnoreCase("back")){
            return;
        }else {
            System.out.println("유효하지 않은 입력이에요.");
            System.out.println("/////////////////////////////////////");
        }
    }

    int getResult(int index) {
        return savedResults.get(index);
    }
    void setResult(int index, int value){
        savedResults.set(index, value);
    }

}
