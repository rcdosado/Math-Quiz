package org.rcd.MathGame.Helpers;

import java.util.Random;

public class Operand {
    int value;
    Random r;

//privates
    private int randomRange(int start, int end)
    {
     	r = new Random();
        return  start+(Math.abs(r.nextInt()) % (end-start+1));
    }
    
//public    
    
    //constructors
    public Operand(int value)
    {
        this.value = value;
    }
    public Operand()
    {
        this.value = 0;
    }
    public Operand(Level lvl)
    {
        genRandomValue(lvl);
    }
    public void setOPerand(int what)
    {
    	this.value = what;
    }

    //methods
    public boolean isNegative()
    {
        if (this.value < 0)
            return true;
        else
            return false;
    }
    public int getOperand()
    {
    	return this.value;
    }

    public void genRandomValue(Level lvl)
    {  
        this.value = this.randomRange(lvl.getMin(), lvl.getMax());

    }

}

