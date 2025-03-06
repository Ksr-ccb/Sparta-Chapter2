package level3_ver01;
import java.util.Scanner;

public class App {
    /// ///////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        boolean exitFlag = true;
        boolean isCalc;
        UserInputManager userInput = new UserInputManager();
        ArithmeticCalculator calculator = new ArithmeticCalculator();
        RecordController recordController = new RecordController();

        while(exitFlag) {
            //어떤 기능을 이용할까?
            isCalc = userInput.selectService(recordController);

            //계산기 기능
            if(isCalc){
                userInput.inputValue(calculator);
                calculator.calculate(userInput.inputOperator(), recordController);
            }else {
                //handlingRecords
                if(userInput.handleSavedResult(recordController)){ //정렬하기
                    userInput.customSortingRecord(recordController);
                }else{ //수정/삭제하기?
                    userInput.pickHandleRecord(recordController);
                }
            }
            exitFlag = userInput.checkContinue();
        }
    }
}
