package org.rcd.MathGame.Helpers;

import org.rcd.MathGame.Enums.OperationUsed;

public class Question {
 

		Operands operands;
		Operator operator;
		Level level;
		Settings settings;
		
		
		
		public Operands getOperandsObject() {
			return operands;
		}
	
		public void setOperandsObject(Operands operands) {
			this.operands = operands;
		}
	
		public Operator getOperatorObject() {
			return operator;
		}
	
		public void setOperatorObject(Operator operator) {
			this.operator = operator;
		}
	
		public Level getLevelObject() {
			return level;
		}
	
		public void setLevelObject(Level level) {
			this.level = level;
		}
	
		public Settings getSettingsObject() {
			return settings;
		}
	
	
		public void setSettings(Settings settings) {
			this.settings = settings;
		}

	
		
		
		public Question(int lOperand, char operation, int rOperand,
				int ans) {
			
			this.leftOperand = lOperand;
			this.rightOperand = rOperand;
			this.operation = operation;
			this.answer = ans;
			

		}
		
		public Question(int lOperand, char operation, int rOperand) {
			
			this.leftOperand = lOperand;
			this.rightOperand = rOperand;
			this.operation = operation;
			this.answer = 0;

		}

		public Question() {
			leftOperand = 0;
			rightOperand = 0;
			operation = '+';
			answer = 0;
		}

		public int getLeftOperand() {
			return leftOperand;
		}

		public void setLeftOperand(int leftOperand) {
			this.leftOperand = leftOperand;
		}

		public char getOperation() {
			return operation;
		}

		public void setOperation(char operation) {
			this.operation = operation;
		}

		public int getRightOperand() {
			return rightOperand;
		}

		public void setRightOperand(int rightOperand) {
			this.rightOperand = rightOperand;
		}

		public int getAnswer() {			
			return answer ;
		}
		
		public void setAnswer(int answer) {
			this.answer = answer;
		}
		
		public void computeAnswer(){
			this.answer = compute();
		}
		
		private int compute(){
			
			OperationUsed operation = this.getOperatorObject().getOperation();
			
			operands.setFirstOperand( new Operand(this.leftOperand) );
			operands.setSecondOperand( new Operand(this.rightOperand) );
			
			
			switch(operation){
				case Addition:
					return operands.getSum();
				case Subtraction:
					return operands.getDiff();
				case Multiplication:
					return operands.getProduct();
				case Division:				
					return operands.getQuotient();
				default:
					return 0;
			}
		}

		public boolean isValidState(){
			if( (operands!=null  || operator!=null ||
				level != null  || settings!=null ) &&
				leftOperand !=0 || rightOperand!=0||
				operation != 0x20)
				
				return true;
			else
				return false;
		}
		int leftOperand;
		char operation;
		int rightOperand;
		int answer;
	

}
