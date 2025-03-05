package level3_ver01;


public class ArithmeticCalculator<T extends Number, U extends Number>{
    T t;
    U u;

    public void setValueT(T t){
        this.t = t;
    }

    public void setValueU(U u){
        this.u = u;
    }

    public T getValueT(){
        return t;
    }

    public U getValueU(){
        return u;
    }


    public void calculate(OperatorType operator , RecordController recordController) {
        String resultStr = "";
        double result = 0;

        try {
            switch (operator) {
                case ADD:
                    result = add(t, u);
                    resultStr = "더하기 계산 결과는 " + result + "입니다.";
                    //savedResults.add(result);
                    break;
                case SUBTRACT:
                    result = subtract(t, u);
                    resultStr = "빼기 계산 결과는 " + result + "입니다.";
                    //savedResults.add(result);
                    break;
                case MULTIPLY:
                    result = multiply(t, u);
                    resultStr = "곱하기 계산 결과는 " + result + "입니다.";
                    //savedResults.add(result);
                    break;
                case DIVIDE:
                    result = divide(t, u);
                    resultStr = "나누기 계산 결과는 " + result + "입니다.";
                    //savedResults.add(result);
                    break;
            }
            System.out.println(resultStr);
            recordController.addResult(result);
        } catch (Exception e) {
            System.out.println("연산을 진행할 수 없어요." + "\n" + " 에러를 확인해주세요 : " + e.getMessage());
            //return 0;
        }
    }

    private static <T extends Number, U extends Number> double add(T a, U b) {
        return a.doubleValue() + b.doubleValue();
    }

    private static <T extends Number, U extends Number> double subtract(T a, U b) {
        return a.doubleValue() - b.doubleValue();
    }

    private static <T extends Number, U extends Number> double multiply(T a, U b) {
        return a.doubleValue() * b.doubleValue();
    }

    private static <T extends Number, U extends Number> double divide(T a, U b) {
        if (b.doubleValue() == 0) {
            throw new ArithmeticException("0으로 나눌 수 없습니다.");
        }
        return a.doubleValue() / b.doubleValue();
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

    public static OperatorType findSymbol(String symbol) throws IllegalArgumentException{
        for (OperatorType op : OperatorType.values()) {
            if (op.symbol.equals(symbol)) {
                return op;
            }
        }
        throw new IllegalArgumentException(symbol+ "은 유효한 연산 기호가 아니에요 ");
    }
}