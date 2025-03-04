package level3;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class ArithmeticCalculator{
    Scanner sc = new Scanner(System.in);
    //public List<calcValues> calcRecords = new ArrayList<calcValues>();

    private double result= 0;
    private String resultStr;


    private List<Double> savedResults= new ArrayList<>();

    public <S> void setOperand(S value){
    }

    CalculateValue inputValue(int i){
        System.out.print("연산을 진행할 "+ (i+1) +"번째 정수를 입력해주세요 : ");
        String input = sc.nextLine().trim();

        CalculateValue<?> value;

        if (input.matches("\\d+\\.\\d+")) {
            // 소수점이 있으면 Double로 처리
            double doubleValue = Double.parseDouble(input);
            value = new CalculateValue<>(doubleValue);
            return value;
        } else if( input.matches("\\d+")){
            // 소수점이 없으면 Integer로 처리
            int intValue = Integer.parseInt(input);
            value = new CalculateValue<>(intValue);
            return value;
        }else{
            System.out.println("0 이상의 숫자 값을 입력해주세요!");
            return inputValue(i);
        }
    }
    OperatorType inputOperator(){
        System.out.print("계산할 사칙연산 기호를 정해주세요: ");
        String temp = sc.nextLine();
        OperatorType operator;

        if(temp.length() == 1){
            try{
                operator = OperatorType.findSymbol(temp);
                //System.out.println("입력한 연산자: " + operator);
                return operator;
            }catch (Exception e){
                System.out.println("에러메시지 :" + e);
                System.out.println(temp + "은 유효한 연산 기호가 아니에요 ");

            }
        }
        return inputOperator();
    }

    //extends Number == 숫자로만 제한건다!!!!!!!!!!!!!!!!
    public void cacultate(CalculateValue calculateValueA, CalculateValue calculateValueB, OperatorType operator) {
        //oper종류에 따라서 뭐...... 계산ㄱ하기
        double a = calculateValueA.toDouble();
        double b = calculateValueB.toDouble();

        switch (operator){
            case ADD :
                result = a+b;
                resultStr = "더하기 계산 결과는 " + result + "입니다.";
                savedResults.add(result);
                break;
            case SUBTRACT :
                result = a-b;
                resultStr = "빼기 계산 결과는 " + result + "입니다.";
                savedResults.add(result);
                break;
            case MULTIPLY :
                result = a*b;
                resultStr = "곱하기 계산 결과는 " + result + "입니다.";
                savedResults.add(result);
                break;
            case DIVIDE:
                result = a/b;
                resultStr = "나누기 계산 결과는 " + result + "입니다.";
                savedResults.add(result);
                break;
        }
        System.out.println(resultStr);
    }
    boolean checkContinue() {
        System.out.println("//////////////////////////////////////");
        System.out.println("계산기를 계속 진행하시겠습니까?");
        System.out.print("계산을 종료하시려면 'exit' , 누적 계산 결과를 출력하시려면 'total' 을 입력하세요 : ");
        String finishStr = sc.nextLine();

        if(finishStr.equalsIgnoreCase("total")){
            //pickHandleRecord();
            return checkContinue();
        }else{
            System.out.println("//////////////////////////////////////");
            return confirmContinue(finishStr);
        }
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
}

enum OperatorType {
    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("*"),
    DIVIDE("/");

    private final String symbol;

    OperatorType(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static OperatorType findSymbol(String symbol) throws Exception {
        for (OperatorType op : OperatorType.values()) {
            if (op.symbol.equals(symbol)) {
                return op;
            }
        }
        throw new Exception(symbol+ "은 유효한 연산 기호가 아니에요 ");
    }
}