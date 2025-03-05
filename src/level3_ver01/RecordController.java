package level3_ver01;

import java.util.ArrayList;
import java.util.List;

public class RecordController {
    private final List<Double> savedResults= new ArrayList<>();

    //값추가
    public void addResult(double result){
        savedResults.add(result);
    }

    //값 출력
    public  void printSavedResult() {
        for (int i=0; i<savedResults.toArray().length; i++) {
            System.out.println("[" + i + "] :" + getResult(i) + ", ");
        }
    }
    public double getResult(int index) throws IndexOutOfBoundsException{ //특정 인덱스 값 가져오기
        if(index >= savedResults.size()){
            throw new IndexOutOfBoundsException("선택가능한 순서가 아니에요.");
        }
        return savedResults.get(index);
    }
    public void setResult(int index, double value) throws IndexOutOfBoundsException{ //특정 인덱스 값 수정하기
        if(index >= savedResults.size()){
            throw new IndexOutOfBoundsException("선택가능한 순서가 아니에요.");
        }
        savedResults.set(index, value);
    }

    public int getResultsLength(){ //전체 리스트 길이 구하기
        return savedResults.size();
    }
    public void deleteSavedResult(int index) throws IndexOutOfBoundsException{ //특정 인덱스 값 삭제하기
        if(index >= savedResults.size()){
            throw new IndexOutOfBoundsException("선택가능한 순서가 아니에요.");
        }
        savedResults.remove(index);
    }

    public void sortingAboveValue(double value){ //특정값 받으면 그 값 이상의 값들 삭제하기
        savedResults.stream() //읽기 전용으로 연다
                .filter(listValue -> listValue >= value).sorted() //파라미터 이상 값만 남기고, 그대로 오름차순 정렬
                        .forEach(listValue->{ //정렬된것들 for문돌려서
                            int index = savedResults.indexOf(listValue); //각각 인덱스 어딘지 알아내고
                            System.out.println("["+ index+ "]" + "번 째 : "+ listValue + ", "); //출력
                        });
    }
}
