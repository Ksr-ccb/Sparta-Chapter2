package level2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Calculator calculator = new Calculator();

        Scanner sc = new Scanner(System.in);
        int[] values = new int[2];
        boolean exitFlag = true;
        //int result = 0;
        String finishStr;

        while(exitFlag){
            //계산기에 들어갈 수 입력 받기
            for(int i = 0; i < 2; i++){
                System.out.print("연산을 진행할 "+ (i+1) +"번째 정수를 입력해주세요 : ");
                try{
                    values[i] = sc.nextInt();
                    if(values[i] < 0){
                        System.out.println("0 이상의 값만 입력이 가능해요.");
                        sc.nextLine();
                        i--;
                    }else{
                        calculator.setValues(values[i], i);
                    }
                }catch (InputMismatchException e) {
                    System.out.println("정수 값을 입력해주세요!");
                    sc.nextLine();
                    i--;
                }
            }

            // 입력 유효성 검사
            //////////// 입력 유효성 검사///////////
            // //            boolean chkValidation = true;
            // //            char operator = 0;
            // //            while(chkValidation){
            // //                System.out.println(values[0] + "와" + values[1] + " 사이 계산할 사칙연산 기호를 정해주세요: ");
            // //                operator = sc.next().charAt(0);
            // //                sc.nextLine();
            // //
            // //                if(operator == '+'){
            // //                    chkValidation = false;
            // //                }else if (operator == '-'){
            // //                    chkValidation = false;
            // //                }else if (operator == '*'){
            // //                    chkValidation = false;
            // //                }else if (operator == '/'){
            // //                    if(values[1] == 0){
            // //                        System.out.println("나눗셈 연산에서 분모(두번째 정수)에 0이 입력될 수 없어요");
            // //                    }
            // //                    else{
            // //                        chkValidation = false;
            // //                    }
            // //                }else{
            // //                    System.out.println("사칙 연산 기호는 +,-,*,/만 사용가능해요.");
            // //                }
            // //            }
            /// /////////입력 끝 ///////////////
            //exception
            System.out.print(values[0] + "와" + values[1] + " 사이 계산할 사칙연산 기호를 정해주세요: ");
            char operator = sc.next().charAt(0);
            sc.nextLine();
            if(operator == '+' || operator == '-'||operator == '*'||operator == '/'){
                calculator.setOperator(operator);
            }else{
                System.out.println(operator + "은 유효한 연산 기호가 아니에요 ");
            }

            calculator.operationException();

            System.out.println("계산 결과 값은 : " + calculator.getResult() + " 입니다");
            /// ///////////////계산기는 끝/////////////////

            System.out.println("//////////////////////////////////////");
            System.out.println("계산기를 계속 진행하시겠습니까?");
            System.out.print("계산을 종료하시려면 'exit' , 누적 계산 결과를 출력하시려면 'total' 을 입력하세요 : ");
            finishStr = sc.nextLine();
            if(finishStr.equalsIgnoreCase("total")){
                for (Integer i : calculator.getSavedResults()) {
                    System.out.print(i + ", ");
                }
                System.out.println("처음 누적 결과값을 삭제할 수 있어요.");
                System.out.print("처음 누적 결과값을 삭제하려면 'delete' , 계산을 종료하시려면 'exit' 를 입력하세요 : ");

                finishStr = sc.nextLine();
                if(finishStr.equalsIgnoreCase("delete")){
                    calculator.deleteSavedResult();
                }
            }
            exitFlag = calculator.checkContinue(finishStr);
            System.out.println("//////////////////////////////////////");

        }

    }
}
