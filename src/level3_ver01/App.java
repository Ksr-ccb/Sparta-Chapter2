package level3_ver01;


import java.util.Scanner;

public class App {
    static Scanner sc = new Scanner(System.in);
    static ArithmeticCalculator calculator = new ArithmeticCalculator();
    static RecordController recordController = new RecordController();

    /// ///////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        boolean exitFlag = true;
        boolean isCalc;

        while(exitFlag) {
            //어떤 기능을 이용할까?
            isCalc = selectService();

            //계산기 기능
            if(isCalc){
                inputValue();
                calculator.calculate(inputOperator(), recordController);
            }else {
                //handlingRecords
                pickHandleRecord();
            }
            exitFlag = checkContinue();
        }
    }

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void inputValue() {
        boolean nextFlag = true;
        while (nextFlag) {
            System.out.print("연산을 진행할 1번째 양수를 입력해주세요 : ");
            String input = sc.nextLine().trim();
            try{
                if (input.matches("\\d+\\.\\d+")) {
                    // 소수점이 있으면 Double로 처리
                    double doubleValue = Double.parseDouble(input);
                    if (doubleValue < 0) {
                        throw new NumberFormatException("0 이상의 숫자 값만 유효해요!");
                    }
                    calculator.setValueT(doubleValue);
                    nextFlag= false;
                } else if (input.matches("\\d+")) {
                    // 소수점이 없으면 Integer로 처리
                    int intValue = Integer.parseInt(input);
                    if (intValue < 0) {
                        throw new NumberFormatException("0 이상의 숫자 값만 유효해요!");
                    }
                    calculator.setValueT(intValue);

                    nextFlag=false;
                } else {
                    throw new NumberFormatException("숫자 값만 유효해요!");
                }
            }catch (NumberFormatException e){
                System.out.println("!!!! " + e);
                System.out.println("다시 입력해주세요.");
            }
        }
        nextFlag =true;
        while (nextFlag) {
            System.out.print("연산을 진행할 2번째 양수를 입력해주세요 : ");
            String input = sc.nextLine().trim();
            try{
                if (input.matches("\\d+\\.\\d+")) {
                    // 소수점이 있으면 Double로 처리
                    double doubleValue = Double.parseDouble(input);
                    if (doubleValue < 0) {
                        throw new NumberFormatException("0 이상의 숫자 값만 유효해요!");
                    }
                    calculator.setValueU(doubleValue);
                    nextFlag= false;
                } else if (input.matches("\\d+")) {
                    // 소수점이 없으면 Integer로 처리
                    int intValue = Integer.parseInt(input);
                    if (intValue < 0) {
                        throw new NumberFormatException("0 이상의 숫자 값만 유효해요!");
                    }
                    calculator.setValueU(intValue);
                    nextFlag=false;
                } else {
                    throw new NumberFormatException("숫자 값만 유효해요!");
                }
            }catch (NumberFormatException e){
                System.out.println("!!!! " + e.getMessage());
                System.out.println("다시 입력해주세요.");
            }
        }
    }
    static OperatorType inputOperator(){
        while (true) { // 잘못된 입력이 들어오면 계속 반복

            System.out.print("계산할 사칙연산 기호를 정해주세요 (➕,➖,*,/) : ");
            String temp = sc.nextLine();

            if (temp.length() == 1) { //입력값이 1글자이상이면 정상적이지않음
                try {
                    return OperatorType.findSymbol(temp); // 올바른 값이면 반환 후 종료
                } catch (Exception e) {
                    System.out.println("!!!! " + e.getMessage());
                    System.out.println("연산 기호를 다시 입력해주세요.");
                }
            } else {
                System.out.println(temp + "은 유효한 연산 기호가 아니에요.");
            }
        }
    }
    static boolean checkContinue() {
        System.out.println("//////////////////////////////////////");
        System.out.println("계산기를 계속 진행하시겠습니까?");
        System.out.print("계산을 종료하시려면 'exit' , 서비스 목록으로 가시려면 아무거나 입력을 해주세요 : ");
        String finishStr = sc.nextLine();

        if(finishStr.equalsIgnoreCase("exit")){
            System.out.println("//////////////////////////////////////");
            System.out.println("계산기를 종료합니다.");
            sc.close();
            return false;
            //pickHandleRecord();
        }else{
            return true;
        }
    }
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    static void handleSavedResult(int index) {
        System.out.print("수정할 값 혹은 'delete'를 입력하여 값을 변경할 수 있어요. 돌아가시려면 'back' 를 입력하세요 : ");

        String totalStr;
        totalStr = sc.nextLine();

        if(totalStr.matches("\\d+")){ //int값이면
            try{
                int value = Integer.parseInt(totalStr);
                recordController.setResult(index, value);
                System.out.println("수정을 완료했어요. 처음으로 돌아가겠습니다.");

            }catch (Exception e){
                System.out.println("누적 결과값을 불러올 수 없어요");
                System.out.println("에러 메시지 : " + e.getMessage());
            }
        } else if (totalStr.equalsIgnoreCase("delete")){
            recordController.deleteSavedResult(index);
        } else if (totalStr.equalsIgnoreCase("back")){
            pickHandleRecord();
        }else {
            System.out.println("유효하지 않은 입력이에요.");
            System.out.println("/////////////////////////////////////");
        }
    }

     static void pickHandleRecord(){
        System.out.println("<기록중인 계산 결과 값>");
        recordController.printSavedResult();
        System.out.print("수정/삭제 할 결과 값의 순서를 입력하시거나 (0부터시작), 돌아가시려면 'back' 를 입력하세요 : ");

        String totalStr;
        totalStr = sc.nextLine();

        if(totalStr.matches("\\d+")){ //int값이면
            try{
                int index = Integer.parseInt(totalStr);
                System.out.println( index + "번 째 결과값 " + recordController.getResult(index));
                handleSavedResult(index);
            }catch (Exception e){
                System.out.println("누적 결과값을 불러올 수 없어요");
                System.out.println("에러 메시지 : " + e.getMessage());
            }
        } else if (totalStr.equalsIgnoreCase("back")){
        }else {
            System.out.println("유효하지 않은 입력이에요.");
            System.out.println("/////////////////////////////////////");
        }
    }
    static boolean selectService() {
        System.out.println("//////////////////////////////////////");
        System.out.println("이용할 기능을 입력하세요. \n 1. 계산기(default)  \t 2. 계산 기록 관리 하기 \n");
        System.out.println("현재 기록된 계산기 기록은 "+ recordController.getResultsLength()+"개 입니다.");
        System.out.println("//////////////////////////////////////");

        String finishStr = sc.nextLine();

        if(finishStr.equalsIgnoreCase("2")){

            if(recordController.getResultsLength() == 0){
                System.out.println("현재 계산 기록중인 데이터가 없습니다. 계산기 실행으로 넘어갑니다.");
                return true;
            }
            System.out.println("//////////////////////////////////////");
            System.out.println("계산기 기록 관리 하기를 시작합니다.");
            return false;
            //pickHandleRecord();
        }else{
            System.out.println("//////////////////////////////////////");
            System.out.println("계산기를 시작합니다.");
            return true;
        }
    }
}
