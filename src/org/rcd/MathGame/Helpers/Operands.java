package org.rcd.MathGame.Helpers;

public class Operands {

	private Operand one;
    private Operand two;
    private Level level;

    public Operands(Level lvl)
    {
        one = new Operand();
        two = new Operand();
        this.level = lvl;
        
        generateRandomOperands(lvl);
        

    }

    public void setFirstOperand(Operand one){
    	this.one = one;
    }
    public void setSecondOperand(Operand two){
    	this.two= two;
    }
    public void generateRandomOperands(Level lvl)
    {
        if(lvl == null){
        	throw new IllegalArgumentException();
        }
        one.genRandomValue(lvl);        
        two.genRandomValue(lvl);
    }
    public void generateRandomOperands()
    {
        if(this.level == null){
        	throw new IllegalStateException();
        }
        one.genRandomValue(this.level);        
        two.genRandomValue(this.level);
    }

    public int getSum()
    {
        return one.getOperand()+two.getOperand();
    }
    public int getDiff()
    {
        return one.getOperand()-two.getOperand();   
    }

    public int getProduct()
    {
    	return one.getOperand()*two.getOperand();
    }
    
    public int getQuotient()
    {
    	if(two.getOperand()==0)
    		throw new IllegalArgumentException("Operands, getQuotient : Divide by zero is imminent");
    	return one.getOperand()/two.getOperand();
    }
    
    //returns there handles
    public Operand getOperandOne()
    {
        return this.one;
    }
    public Operand getOperandTwo()
    {
        return this.two;
    }
    
    //returns the values of operands
    public int getFirstOperandValue()
    {
        return this.one.getOperand();
    }
    public int getSecondOperandValue()
    {
        return this.two.getOperand();
    }
}

