package level3;

public class CalculateValue<T extends Number>{
    private T value;

    public CalculateValue(T value) {
        // double 타입으로 변환 후 비교
        this.value = value;
    }

    public void setValue(T value){
        this.value = value;
    }
    public T getValue(){
        return this.value;
    }

    public double toDouble() {
        return value.doubleValue();
    }
}
