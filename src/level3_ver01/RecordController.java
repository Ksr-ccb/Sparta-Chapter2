package level3_ver01;

import level3.CalculateValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RecordController {
    private final List<Double> savedResults= new ArrayList<>();
    Scanner sc = new Scanner(System.in);


    public void addResult(double result){
        savedResults.add(result);
    }

    void printSavedResult() {
        for (int i=0; i<savedResults.toArray().length; i++) {
            System.out.print("[" + i + "] :" + getResult(i) + ", ");
        }
    }
    double getResult(int index) throws IndexOutOfBoundsException{
        if(index >= savedResults.size()){
            throw new IndexOutOfBoundsException("선택가능한 순서가 아니에요.");
        }
        return savedResults.get(index);
    }
    void setResult(int index, double value) throws IndexOutOfBoundsException{
        if(index >= savedResults.size()){
            throw new IndexOutOfBoundsException("선택가능한 순서가 아니에요.");
        }

        savedResults.set(index, value);
    }

    public int getResultsLength(){
        return savedResults.size();
    }
    public void deleteSavedResult(int index) throws IndexOutOfBoundsException{
        if(index >= savedResults.size()){
            throw new IndexOutOfBoundsException("선택가능한 순서가 아니에요.");
        }
        savedResults.remove(index);
    }
}
