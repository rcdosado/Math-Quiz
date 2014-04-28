package org.rcd.MathGame.Helpers;

import org.rcd.MathGame.Enums.LevelTypes;

public class Settings{

    Level level;
    int numberOfItems;
    int maxDiff;
    Operator op;

    public Settings(Level level, Operator op, int numberOfItems, int maxDiff)
    {
        this.level = level;
        this.op = op;
        this.numberOfItems = numberOfItems;
        this.maxDiff = maxDiff;
    }
    
    public Settings(Settings fromAnotherSettings)
    {
        this.level = fromAnotherSettings.level;
        this.op = fromAnotherSettings.op;
        this.numberOfItems = fromAnotherSettings.numberOfItems;
        this.maxDiff = fromAnotherSettings.maxDiff;
    }   

    public Settings()
    {
        this.level = new Level(1, 10,LevelTypes.Level1);
        this.op = new Operator();
        this.numberOfItems = 5;
        this.maxDiff = 10;
    }


    public Level getLevel()
    {
        return this.level;
    }

    public void setLevel(Level level)
    {
        this.level = level;
    }
    public int getNumberOfItems()
    {
        return numberOfItems;
    }
    public void setNumberOfItems(int numberOfItems)
    {
    	this.numberOfItems = numberOfItems;

    }
    public int getMaxDiff()
    {
        return this.maxDiff;
    }
    public int getMaxDiffLess(int than)
    {
        return than - this.maxDiff;
    }

    public int getMaxDiffHigh(int than)
    {
    	
        return than + this.maxDiff;
    }

    public void setMaxDiff(int MaxDiff)
    {
        if (MaxDiff < 1)
            throw new IllegalArgumentException("Settings, setMaxDiff : invalid maxdiff value");
        this.maxDiff = MaxDiff;        
    }
    public void setOperator(Operator op)
    {
    
        this.op = op;
    }

    public Operator getOperator()
    {
        return op;
    }
    

}







