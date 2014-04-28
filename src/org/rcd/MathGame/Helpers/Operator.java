package org.rcd.MathGame.Helpers;

import java.util.Random;

import org.rcd.MathGame.Enums.OperationUsed;

public class Operator {
	OperationUsed operationUsed;
	Random r;

	public Operator(OperationUsed operation) {
		this.operationUsed = operation;

	}

	public Operator() {
		// default
		this.operationUsed = OperationUsed.Addition;
	}

	public void setOperation(OperationUsed operation) {
		this.operationUsed = operation;
	}

	public OperationUsed getOperation() {
		return operationUsed;
	}

	public OperationUsed genRandomOperation() {
		r = new Random();
		int start = 1, end = 3, result = 0;
		result = start + (r.nextInt() % (end - start + 1));
		if (result == 1) {
			this.operationUsed = OperationUsed.Addition;
		} else if (result == 2) {
			this.operationUsed = OperationUsed.Subtraction;
		} else if( result == 3){
			this.operationUsed = OperationUsed.Multiplication;
		}else {
			this.operationUsed = OperationUsed.Division;
		}
		return this.operationUsed;
	}

	public String toString() {
		return String.valueOf(this.toChar());
	}

	public char toChar() {
		switch (operationUsed) {
		case Addition:
			return '+';
		case Subtraction:
			return '-';
		case Multiplication:
			return '*';
		case Division:
			return '/';
		default:
			return '+';
		}
	}

}
