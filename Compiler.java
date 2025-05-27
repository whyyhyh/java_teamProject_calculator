package pck01;

public class Compiler {

    private Register[] memory;
    private int commandIndex = 0;      
    private int dataIndex = 15;   
    private Bus bus;

    public Compiler(Register[] memory, Bus bus) {
        this.memory = memory;
        this.bus = bus;
    }

    public void compile(String expression) {
        String[] tokens = expression.split(" "); 

        String leftOperand = tokens[0];
        String operator = tokens[1];
        String rightOperand = tokens[2];

        int leftDataAddr = dataIndex--;
        int rightDataAddr = dataIndex--;
        int resultAddr = dataIndex--;

        bus.compToReg(leftOperand, memory[leftDataAddr]);
        bus.compToReg(rightOperand, memory[rightDataAddr]);

        bus.compToReg("load " + leftDataAddr, memory[commandIndex++]);
        bus.compToReg(opToCommand(operator) + " " + rightDataAddr, memory[commandIndex++]);
        bus.compToReg("store " + resultAddr, memory[commandIndex++]);

        System.out.println("\n[컴파일 완료] 결과는 memory[" + resultAddr + "]에 저장됩니다.");
    }

    private String opToCommand(String op) {
        return switch (op) {
            case "+" -> "add";
            case "-" -> "sub";
            case "*" -> "mul";
            case "/" -> "div";
            default -> throw new IllegalArgumentException("지원되지 않는 연산자: " + op);
        };
    }
}
