package level1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainCalculator {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int[] values = new int[2];
        boolean exitFlag = true;
        String str;

        while(exitFlag){
            for(int i = 0; i < 2; i++){
                System.out.println("연산을 진행할 "+ (i+1) +"번째 정수를 입력해주세요 : ");
                try{
                    values[i] = sc.nextInt();
                    if(values[i] < 0){
                        System.out.println("0 이상의 값만 입력이 가능해요.");
                        sc.nextLine();
                        i--;
                    }
                }catch (InputMismatchException e) {
                    System.out.println("정수 값을 입력해주세요!");
                    sc.nextLine();
                    i--;
                }


            }

            System.out.println(values[0] + "와" + values[1] + " 사이 계산할 사칙연산 기호를 정해주세요: ");
            char operator = sc.next().charAt(0);

            sc.nextLine();
            switch (operator){
                case '+':
                    System.out.println(values[0] + values[1]);
                    break;
                case '-':
                    System.out.println(values[0] - values[1]);
                    break;
                case '*':
                    System.out.println(values[0] * values[1]);
                    break;
                case '/':
                    if(values[1] != 0){
                        System.out.println(values[0] / values[1]);
                    }else {
                        System.out.println("나눗셈 연산에서 분모(두번째 정수)에 0이 입력될 수 없어요");
                    }
                    break;
                default:
                    System.out.println("사칙 연산 기호는 +,-,*,/만 사용가능해요.");
            }
            System.out.print("계산을 종료하시려면 'exit'를 입력해주세요 : ");
            str = sc.nextLine();
            if(str.equals("exit")){
                exitFlag = false;
                System.out.println("계산기를 종료합니다.");
            }
            else{
                System.out.println("입력된 값 : " + str);
                System.out.println("계산을 다시 시작합니다.");
            }
        }
        sc.close();
    }
}
