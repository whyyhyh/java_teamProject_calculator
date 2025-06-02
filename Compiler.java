
public class Compiler {
	//private String operrationExpression;
	private String lodeData;
	private String storeData;
	private String operationData;
	private int operand_1, operand_2;
	
	public Compiler() {//컴파일러 생성할 때 문자열 생성(반복관리 때문에 메소드로)
//		//this.operrationExpression = operationExpression;
//		//char letter;
//		
//		String[] splited = operationExpression.split("[+\\-*/ ]+");
//		value_1 = Integer.parseInt(splited[0]);
//		value_2 = Integer.parseInt(splited[1]);
//		for (int i = 0; i < operationExpression.length(); i++) {
//			if(operationExpression.charAt(i) == '+') {
//				operationData = "ADD 19";
//			}
//			else if(operationExpression.charAt(i) == '-') {
//				operationData = "SUB 19";
//			}
//			else if(operationExpression.charAt(i) == '/') {
//				operationData = "DIV 19";
//			}
//			else if(operationExpression.charAt(i) == '*') {
//				operationData = "MUL 19";
//			}
//		}
//		lodeData = "LOAD 18";
//		storeData = "STORE 20";
		
	}
	public String getLodeData() {
		return lodeData;
	}
	public String getstoreData() {
		return storeData;
	}
	public String getoperationData() {
		return operationData;
	}
	public int getOperand_1() {
		return operand_1;
	}
	public int getOperand_2() {
		return operand_2;
	}
	void compile(String operationExpression) {//메소드로 문자열 분리
		String[] splited = operationExpression.split("[+\\-*/ ]+");		
		operand_1 = Integer.parseInt(splited[0]);		
		operand_2 = Integer.parseInt(splited[1]);		
		for (int i = 0; i < operationExpression.length(); i++) {		
			if(operationExpression.charAt(i) == '+') {			
				operationData = "ADD 19";			}		
			else if(operationExpression.charAt(i) == '-') {			
				operationData = "SUB 19";			}		
			else if(operationExpression.charAt(i) == '/') {			
				operationData = "DIV 19";			}		
			else if(operationExpression.charAt(i) == '*') {			
				operationData = "MUL 19";			}	
			}	
		lodeData = "LOAD 18";	
		storeData = "STORE 20";
	}
	
}
