package org.rcd.MathGame.Helpers;

import org.rcd.MathGame.Enums.LevelTypes;

public class Level {
	int minimum;
	int maximum;
	LevelTypes levelType;
	
	public Level(Level level)
	{
		this.minimum = level.getMin();
		this.maximum = level.getMax();
		this.levelType = level.getLevelType();
		checkValidity();
	
	}
	public Level(int min, int max, LevelTypes levelType) {

		this.minimum = min;
		this.maximum = max;
		this.levelType = levelType;
		checkValidity();
	}


	private void loadDefaults(){
		this.minimum = 1;
		this.maximum = 10;
		this.levelType = LevelTypes.Level1;
	}
	
	private void checkValidity(){
		if(this.minimum >= this.maximum){
			loadDefaults();
			throw new IllegalArgumentException();
		}		
	}
	
	public LevelTypes getLevelType() {
		return this.levelType;
	}

	public void setLevelType(LevelTypes type) {
		this.levelType = type;
	}

	public int getMin() {
		return this.minimum;
	}

	public int getMax() {
		return this.maximum;
	}

}
