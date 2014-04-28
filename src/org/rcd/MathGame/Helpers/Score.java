package org.rcd.MathGame.Helpers;


public class Score {

    private int correct;
    private int wrong;

    public void reset(){
        this.correct = 0;
        this.wrong = 0;
    }

    public void setCorrect(int num)
    {
    	this.correct = num;
    }
    
    public void setWrong(int num)
    {
    	this.correct = num;
    }

    public Score(int correct, int wrong)
    {
        this.correct = correct;
        this.wrong = wrong;
    }
    public Score()
    {
    	reset();
    }
    public int getTotalItems()
    {
        return correct + wrong;
    }

    public void incrementCorrect()
    {
        correct++;
    }
    public void incrementWrong()
    {
        wrong++;
    }
    public void addCorrect(int howmuch)
    {
        if (howmuch < 1)
        {
            incrementCorrect();
            return;
        }

        correct += howmuch;

    }

    public void addWrong(int howmuch)
    {
        if (howmuch < 1)
        {
            incrementWrong();
            return;
        }

        wrong += howmuch;

    }
    public int getCorrectScore()
    {
        if (correct < 0)
            return 0;
        return correct;
    }

    public int getWrongScore()
    {
        if (wrong < 0)
            return 0;
        return wrong;
    }
    public double ComputeGrade(double passingRate)
    {
        double correct = (double) this.correct;
        double totalitems = (double) this.getTotalItems();
        double totalOf = 100.0,remains = 0.0;

        
        remains = totalOf - passingRate;
        totalOf = passingRate + remains;
        if(totalOf!= 100.0)
        {
        	//throw Exception
        	//or use default calculation
        	return (correct / totalitems) * 50 + 50;
        }
        if (correct < 1 || totalitems < 1)
            return remains;
        return  ( correct / totalitems)*passingRate+remains;
    }

    public int getAverage()
    {
        return this.correct / getTotalItems();
    }



}
