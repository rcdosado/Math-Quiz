package org.rcd.MathGame.MainApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;

import org.rcd.MathGame.Enums.LevelTypes;
import org.rcd.MathGame.Enums.OperationUsed;
import org.rcd.MathGame.Helpers.Level;
import org.rcd.MathGame.Helpers.Operands;
import org.rcd.MathGame.Helpers.Operator;
import org.rcd.MathGame.Helpers.Properties;
import org.rcd.MathGame.Helpers.Question;
import org.rcd.MathGame.Helpers.Score;
import org.rcd.MathGame.Helpers.Settings;
import org.rcd.MathGame.util.Tools.StopWatch;

public class MainModel {



	Properties properties = null;
	String configFileName = "";
	Settings settings;
	StopWatch stopwatch;
	Score score;
	Question question;
	
	private static final String default_settings_values[] = new String[] {
		"level", "operation", "max_difference", "items", "custom_min",
		"custom_max" };
	
	public MainModel(String file) {
		initializeConfiguration(file);
	}
	public MainModel(){
		initializeConfiguration("config.ini");
		this.settings = this.getSettingsObject();
	}
	
	
	public static String[] getDefaultSettingsValues() {
		return default_settings_values;
	}
	
	public Question getQuestionObject() {
		return question;
	}
	public void setQuestionObject(Question question) {
		this.question = question;
	}
	
	
	public Score getScoreObject() {
		return score;
	}
	public void setScoreObject(Score score) {
		this.score = score;
	}
	
	
	public StopWatch getStopWatchObject() {
		return stopwatch;
	}
	public void setStopWatchObject(StopWatch stopwatch) {
		this.stopwatch = stopwatch;
	}
	
	

	
	public void setSettingsObject(Properties properties){		
		this.settings = extractSettingsFrom(properties);
	}
	
	public Settings getSettingsObject(){		
		return extractSettingsFrom(properties);
	}
	public void reloadSettings(){
		initializeConfiguration(this.getConfigFileName());		
	}
	

	
	
	
	
	public Properties getProperties() {
		if (properties == null) {
			throw new IllegalArgumentException("Properties has not been set or is null");
		}
		return properties;
	}

	public void setProperties(Properties properties) {
		if (properties == null) {
			throw new IllegalArgumentException("Properties has not been set or is null");
		}
		this.properties = properties;
	}
	
	
	

	
	public String getConfigFileName() {
		if (configFileName == "") {
			throw new IllegalArgumentException(
					"Configuration file has not been set");
		}
		return configFileName;
	}

	public void setConfigFileName(String configFileName) {
		this.configFileName = configFileName;
	}
	
	
	
    public int[] generateChoices(int start,int end,int ans)
    {
    	int size = end-start,ctr = 0;
    	int[] src = new int[size];
    	int[] dst=new int[4];
    	
    	if(end <= start){
    		throw new IllegalArgumentException("generateChoices: Invalid argument(s)");
    	}
    	
    	for(int i = start; i < end ;i++)
    	{
    		src[ctr++] = i;
    	}
    	shuffle(src);   
    	// copy only 4 values
    	java.lang.System.arraycopy(src,0,dst, 0, dst.length);

    	
    	if( !contains(dst,ans))
    		dst[ genOneRandomNumber(0,3) ] = ans;
    	
    	return dst;
    }
   
	
	public boolean isCorrect(int answer, Question question){
		Operands operands = question.getOperandsObject();
		Operator operator = question.getOperatorObject();
		OperationUsed opUsed = operator.getOperation() ;
		
		switch(opUsed){		
		
		case Addition:
			return operands.getSum()==answer;
		case Subtraction:
			return operands.getDiff()==answer;
		case Multiplication:
			return operands.getProduct()==answer;	
		case Division:
			return operands.getQuotient()==answer;
		default:
			return operands.getSum()==answer;
				
		}
	}
	private int genOneRandomNumber(int start,int end){
	    	java.util.Random r = new java.util.Random();
	    	return start+(Math.abs(r.nextInt()) % (end-start+1));
	}
    /*
     * Not Mine, i think this came from wikipedia
     */
    private static void shuffle(int[] input)
    { 
        int N = input.length;
        // shuffle
        for (int i = 0; i < N; i++) {
            int r = i + (int) (Math.random() * (N - i));
            int swap = input[r];
            input[r] = input[i];
            input[i] = swap;
        }
      
    }
		
    private boolean contains(final int[] array, final int key) {
        for (final int i : array) {
            if (i == key) {
                return true;
            }
        }
        return false;
    }	
    private Settings extractSettingsFrom(Properties properties){
	
		if(properties==null){
			throw new IllegalArgumentException("MainModel,extractSettingsFrom:properties parameter is Invalid");
		}
		Settings settings = new Settings();
		setSettingsLevel(settings, properties.getString("level"),
				properties.getInt("custom_min"),
				properties.getInt("custom_max"));	
		setSettingsOperator(settings,properties.getString("operation"));		
		settings.setMaxDiff(properties.getInt("max_difference"));
		settings.setNumberOfItems(properties.getInt("items"));
		return settings;
	}
	private void setSettingsLevel(Settings settings, String level,
			int custom_min, int custom_max) {
		if (level.compareTo("Level1") == 0) {
			settings.setLevel(new Level(1,10,LevelTypes.Level1));
		} else if (level.compareTo("Level2") == 0) {
			settings.setLevel(new Level(11,100,LevelTypes.Level2));
		} else {
			settings.setLevel(new Level(custom_min,custom_max,LevelTypes.Custom));
		}
	}
	private void setSettingsOperator(Settings settings, String operation) {
		if (operation.compareTo("Addition") == 0) {
			settings.setOperator(new Operator(OperationUsed.Addition));
		} else if (operation.compareTo("Subtraction") == 0) {
			settings.setOperator(new Operator(OperationUsed.Subtraction));
		} else if (operation.compareTo("Multiplication") == 0) {
			settings.setOperator(new Operator(OperationUsed.Multiplication));
		} else {
			settings.setOperator(new Operator(OperationUsed.Division));
		}
	}
	
	private void initializeConfiguration(String file) {

		if (!isExisting(file)) {
			createDefaultConfiguration(file);
			this.properties = loadConfigFile(file);
		} else {
			this.properties = loadConfigFile(file);
		}// end else

		this.setConfigFileName(file);
	}	
	private boolean isExisting(String file) {
		File f = new File(file);
		boolean returnme = false;
		returnme = f.isFile() && f.canRead();

		return returnme;
	}

	private boolean createDefaultConfiguration(String file) {

		File f = new File(file);
		PrintWriter out = null;
		try {
			out = new PrintWriter(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		out.println("level=Level1");
		out.println("operation=Addition");
		out.println("max_difference=10");
		out.println("items=5");
		out.println("custom_min=1");
		out.println("custom_max=100");
		out.close();

		return true;
	}

	private Properties loadConfigFile(String file) {
		Properties tmpProp = null;
		try {
			File f = new File(file);
			tmpProp = Properties.load(f.getAbsolutePath());
			if (isPropertiesValid(tmpProp)) {
				return tmpProp;
			} else {
				throw new IllegalArgumentException();
			}
		} catch (IOException e) {
					
		} // end catch	
		return tmpProp;
	}


	
	private boolean isPropertiesValid(Properties properties) {
		try {
			//bare essentials
			properties.contains(MainModel.getDefaultSettingsValues());			
			
		} catch (NoSuchElementException e) {
			return false;
		}
		return true;
	}
	
	
	/*
	 * =======================================================================
	 */
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
