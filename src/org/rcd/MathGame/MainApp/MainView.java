package org.rcd.MathGame.MainApp;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.rcd.MathGame.Enums.LevelTypes;
import org.rcd.MathGame.Enums.OperationUsed;
import org.rcd.MathGame.Helpers.Level;
import org.rcd.MathGame.Helpers.Operator;
import org.rcd.MathGame.Helpers.Properties;
import org.rcd.MathGame.Helpers.Question;
import org.rcd.MathGame.Helpers.Settings;
import org.rcd.MathGame.Helpers.WindowState;


import net.miginfocom.swing.MigLayout;


public class MainView extends JFrame {

	
	private Settings settings;
	private Properties properties;
	private String lookAndFeel = "";
	private WindowState windowState = null;



	public MainView() {
		initStates();
		initComponents();			
	}

	private void initStates(){
			
		windowState = new WindowState();
	}
	public void setSettingsObjectFrom(Properties properties){		
		this.settings = extractFrom(properties);
	}
	
	public Settings getFromProperties(){		
		return extractFrom(properties);
	}
	
	private Settings extractFrom(Properties properties){
		
		if(properties==null){
			throw new IllegalArgumentException("MainView, extractFrom : properties parameter is Invalid");
		}		
		Settings settings = new Settings();		
		setSettingsLevel(settings, properties.getString("level"));
		setSettingsOperation(settings, properties.getString("operation"));
		settings.setMaxDiff(properties.getInt("max_difference"));
		settings.setNumberOfItems(properties.getInt("items"));
		
		return settings;
	}

	private void setSettingsOperation(Settings settings, String operation) {
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

	private void setSettingsLevel(Settings settings, String level) {
		if (level.compareTo("Level1") == 0) {
			settings.setLevel(new Level(1,10,LevelTypes.Level1));
		} else if (level.compareTo("Level2") == 0) {
			settings.setLevel(new Level(11,100,LevelTypes.Level2));
		} else {
			settings.setLevel(new Level(1,100,LevelTypes.Level2));
		}
	}
	
	public void setProperties(Properties properties){
		this.properties = properties;
	}
	
	public Properties getProperties() {
		return properties;
	}
	
	
	/*
	 *	saving and restoring panel texts 
	 */
	
	public void savePanelTexts(){
		saveQuestionsPanelTexts();
		saveChoicesPanelTexts();
		saveStatusPanelTexts();
		saveControlPanelTexts();
	}
	
	public void restorePanelTexts(){
		
		restoreQuestions();
		restoreChoices();		
		restoreStatus();
		restoreControl();
	}
	private void saveQuestionsPanelTexts(){
		windowState.setQuestionListObject(getWindowTextsOf(questionPanel));
	}
	private void saveChoicesPanelTexts(){
		windowState.setChoicesListObject(getWindowTextsOf(choicesPanel));	
	}	
	private void saveStatusPanelTexts(){
		windowState.setStatusListObject(getWindowTextsOf(statusPanel));
	}
	
	private void saveControlPanelTexts(){
		windowState.setControlObject(getWindowTextsOf(controlPanel));
	}

	private void restoreQuestions(){
		if(windowState==null){
			throw new IllegalArgumentException("MainView, restoreQuestions: windowState is null");
		}
		ArrayList<String> questions = windowState.getQuestionListObject();
		
	    Component[] components = questionPanel.getComponents();
	    for(int i = 0 ; i< components.length;i++){
          ((JLabel)components[i]).setText(questions.get(i));
	    }
	}
	private void restoreChoices() {
		if(windowState==null){
			throw new IllegalArgumentException("MainView, restoreChoices: windowState is null");
		}
		ArrayList<String> choices = windowState.getChoicesListObject();
		Component[] components = choicesPanel.getComponents();
	    for(int i = 0 ; i< components.length;i++){
          ((JButton)components[i]).setText(choices.get(i));
	    }
	}
	private void restoreStatus(){
		if(windowState==null){
			throw new IllegalArgumentException("MainView, restoreStatus: windowState is null");
		}		
		ArrayList<String> status= windowState.getStatusListObject();
		Component[] components = statusPanel.getComponents();
		Class<?> textFieldType = JTextField.class;
		
	    for(int i = 0 ; i< components.length;i++){
	    	if(textFieldType.isInstance(components[i]) )
	    		((JTextField)components[i]).setText(status.get(i));
	    }
	}
	private void restoreControl(){
		if(windowState==null){
			throw new IllegalArgumentException("MainView, restoreControl: windowState is null");
		}		
		ArrayList<String> control= windowState.getControlObject();
		Component[] components = controlPanel.getComponents();
		Class<?> textFieldType = JButton.class;
		
	    for(int i = 0 ; i< components.length;i++){
	    	if(textFieldType.isInstance(components[i]) )
	    		((JButton)components[i]).setText(control.get(i));
	    }
	}
	private ArrayList<String> getWindowTextsOf(JPanel panel){
		
		if(panel==null){
			throw new IllegalArgumentException("MainView, getWindowTextsOf : Invalid Jpanel argument");
		}		
	    Component[] components = panel.getComponents();	    	   
	    ArrayList<String> widgetNames = new ArrayList<String>();
	    
		    for (int i = 0; i < components.length; i++) {
		    	String object = components[i].getClass().getName().toString();
		        if(object.equals("javax.swing.JLabel")){
		        	
		            widgetNames.add( ((JLabel)components[i]).getText() );
		        }
		        if(object.equals("javax.swing.JButton")){
		            
		            widgetNames.add( ((JButton)components[i]).getText());
		        }
		        if(object.equals("javax.swing.JTextField")){		            
		            widgetNames.add( ((JTextField)components[i]).getText() );
		        }
		    }
		    
	    return widgetNames;	
	}
	
	
	
	/*
	 * Start of Initialization of GUI components
	 */
	private void initComponents() {

		leftPanel = new javax.swing.JPanel(new MigLayout());
		rightPanel = new javax.swing.JPanel(new MigLayout());
		mainPanel = new javax.swing.JPanel(new MigLayout());

	
		createAndInitializeQuestionsPanel(new java.awt.Font("Tahoma", 0, 33));
		createAndInitializeChoicesPanel(new java.awt.Font("Tahoma", Font.BOLD, 14));
		createAndInitializeControlButtons();
		createAndInitializeStatusPanel(new java.awt.Font("Tahoma", Font.BOLD,14));	
				
		leftPanel.add(questionPanel,"wrap");
		leftPanel.add(choicesPanel, "split2");
		leftPanel.add(statusPanel);

		
		rightPanel.add(controlPanel);

		mainPanel.add(leftPanel, "top");
		mainPanel.add(rightPanel, "top");						

		savePanelTexts();
		
		this.add(mainPanel);
		this.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		this.setTitle("Math Quiz");
		
		this.setBounds(100, 100, 435, 300);

		this.setResizable(false);

	}
	private void createAndInitializeQuestionsPanel(Font fontObject) {

		questionPanel = new javax.swing.JPanel(new MigLayout());

		lbl_OperandLeft = new javax.swing.JLabel();
		lbl_Operation = new javax.swing.JLabel();
		lbl_OperandRight = new javax.swing.JLabel();
		lbl_Equals = new javax.swing.JLabel();
		lbl_Answer = new javax.swing.JLabel();

		questionPanel.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Questions"));
		
		questionPanel.setToolTipText("math questions here");
		

		lbl_OperandLeft.setFont(fontObject);
		lbl_Operation.setFont(fontObject);
		lbl_OperandRight.setFont(fontObject);
		lbl_Equals.setFont(fontObject);
		lbl_Answer.setFont(fontObject);

		questionPanel.add(lbl_OperandLeft, "gapx 10px");
		questionPanel.add(lbl_Operation, "gapx 10px");
		questionPanel.add(lbl_OperandRight, "gapx 10px");
		questionPanel.add(lbl_Equals, "gapx 10px");
		questionPanel.add(lbl_Answer, "gapx 10px");

		setQuestionsContentWithAnswer(new Question(12, '+', 345,67));
		
		
	}

	private void createAndInitializeChoicesPanel(Font fontObject) {
		choicesPanel = new javax.swing.JPanel(new MigLayout());
		choicesButtonGroup = new javax.swing.ButtonGroup();
		
		
		
		btn_Choice1 = new javax.swing.JButton("A");
		btn_Choice2 = new javax.swing.JButton("B");
		btn_Choice3 = new javax.swing.JButton("C");
		btn_Choice4 = new javax.swing.JButton("D");

		choicesButtonGroup.add(btn_Choice1);
		choicesButtonGroup.add(btn_Choice2);
		choicesButtonGroup.add(btn_Choice3);
		choicesButtonGroup.add(btn_Choice4);

		setChoiceButtonsSize(new java.awt.Dimension(80, 10));
		
		btn_Choice1.setFont(fontObject);
		btn_Choice2.setFont(fontObject);
		btn_Choice3.setFont(fontObject);
		btn_Choice4.setFont(fontObject);

		choicesPanel.add(btn_Choice1, "wrap");
		choicesPanel.add(btn_Choice2, "wrap");
		choicesPanel.add(btn_Choice3, "wrap");
		choicesPanel.add(btn_Choice4, "wrap");

	}
	private void createAndInitializeStatusPanel(Font fontObject) {
		statusPanel = new javax.swing.JPanel(new MigLayout());
		tb_Correct = new javax.swing.JTextField(6);
		tb_Wrong = new javax.swing.JTextField(6);
		tb_Remarks = new javax.swing.JTextField(13);
		lbl_RightAnswer = new javax.swing.JLabel();
		lbl_WrongAnswer = new javax.swing.JLabel();
		lbl_Remarks = new javax.swing.JLabel();

		statusPanel.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Status"));

		lbl_RightAnswer.setFont(fontObject);
		lbl_WrongAnswer.setFont(fontObject);
		lbl_Remarks.setFont(fontObject);

		lbl_RightAnswer.setText("Correct");
		lbl_WrongAnswer.setText("Wrong");
		lbl_Remarks.setText("Remarks");
		

		tb_Correct.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		tb_Wrong.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		tb_Remarks.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		
		tb_Correct.setText("0");
		tb_Wrong.setText("0");
		tb_Remarks.setText("");
		
		tb_Correct.setEditable(false);
		tb_Wrong.setEditable(false);

		statusPanel.add(lbl_RightAnswer, "split2,gapx 10px");
		statusPanel.add(lbl_WrongAnswer, "gapx 15px,wrap");
		statusPanel.add(tb_Correct, "split2");
		statusPanel.add(tb_Wrong, "wrap");
		statusPanel.add(lbl_Remarks, "wrap");
		statusPanel.add(tb_Remarks);

	}
	private void createAndInitializeControlButtons() {

		btn_StartQuiz = new javax.swing.JButton();
		btn_Close = new javax.swing.JButton();
		btn_Settings = new javax.swing.JButton();
		controlPanel = new javax.swing.JPanel(new MigLayout());
		controlButtonGroup = new javax.swing.ButtonGroup();
		
		controlPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		// .createTitledBorder("Score"));

		btn_StartQuiz.setText("Start");
		btn_Close.setText("Close");
		btn_Settings.setText("Settings");
		
		controlButtonGroup.add(btn_StartQuiz);
		controlButtonGroup.add(btn_Close);
		controlButtonGroup.add(btn_Settings);
		
		controlPanel.add(btn_StartQuiz, "wrap,growx");
		controlPanel.add(btn_Close, "wrap,growx");
		controlPanel.add(btn_Settings, "growx");

	}
	
	private void setChoiceButtonsSize(Dimension dimension){
		if(choicesButtonGroup==null){
			throw new IllegalStateException("MainView,setChoiceButtonSize : button group is null!");
		}
		this.setPreferredButtonSizeOf(choicesButtonGroup, dimension );
	}
	public void setQuestionsContent(Question question) {

		lbl_OperandLeft.setText(String.valueOf(question.getLeftOperand()));
		lbl_Operation.setText(String.valueOf(question.getOperation()));
		lbl_OperandRight.setText(String.valueOf(question.getRightOperand()));
		lbl_Equals.setText("=");
		lbl_Answer.setText("?");
	}
	public void setQuestionsContentWithAnswer(Question question) {
		setQuestionsContent(question);
		lbl_Answer.setText(String.valueOf(question.getAnswer()));
	}


	
	public void mainViewFrameListener(WindowListener listener){
		this.addWindowListener( listener);
	}
	
	
	/*
	 * Event handlers : control buttons
	 */
	public void startQuizButtonListener(ActionListener listener){
		btn_StartQuiz.addActionListener(listener);
	}
	public void closeButtonListener(ActionListener listener){
		btn_Close.addActionListener(listener);
	}	
	public void settingsButtonListener(ActionListener listener){
		btn_Settings.addActionListener(listener);
	}
		
	/*
	 * Event Handlers : choices buttons
	 */
	public void choiceOneButtonListener(ActionListener listener){
		btn_Choice1.addActionListener(listener);
	}

	public void choiceTwoButtonListener(ActionListener listener){
		btn_Choice2.addActionListener(listener);
	}
	
	public void choiceThreeButtonListener(ActionListener listener){
		btn_Choice3.addActionListener(listener);
	}
	
	public void choiceFourButtonListener(ActionListener listener){
		btn_Choice4.addActionListener(listener);
	}
	
	/*
	 * Frame Components setters
	 */
	
	public void setQuestionsContentObject(
			Question questionsContentObject) {
		if (this.questionsContentObject == null) {
			throw new IllegalArgumentException("MainView,setQuestionsContentObject: Null Argument");
		}
		this.questionsContentObject = questionsContentObject;
	}
	
	public void setStatusPanelTexts(int correct,int wrong,String remarks){
		setCorrectPoints(correct);
		setWrongPoints(wrong);
		setRemarks(remarks);
	}
	private void setCorrectPoints(int howmany){
		tb_Correct.setText(String.valueOf(howmany));
	}
	private void setWrongPoints(int howmany){
		tb_Wrong.setText(String.valueOf(howmany));
	}
	public void setRemarks(String remarks){
		tb_Remarks.setText(remarks);
	}
	
	/*
	 * Frame Components Getters 
	 */
	


	
	public Question getQuestionsContentObject() {
		if (this.questionsContentObject == null) {
			throw new IllegalStateException();
		}
		return this.questionsContentObject;
	}	
	public int getCorrectPoints(){
		return Integer.parseInt(tb_Correct.getText());
	}
	public int getWrongPoints(){
		return Integer.parseInt(tb_Wrong.getText());
	}	
	public String getRemarks(){
		return tb_Remarks.getText();
	}
	
	public JButton getSelectedChoiceButton(){
		return getSelectedButtonFromGroup(choicesButtonGroup);
	}
	public JButton getSelectedControlButton(){
		return getSelectedButtonFromGroup(controlButtonGroup);
	}
	private JButton getSelectedButtonFromGroup(ButtonGroup bg){
		return getSelectedButton(bg);
	}
	
	public void enableAllControls(){
		enableChoicesButtons();
		enableStatusComponents();
		enableControlButtons();		
	}
	public void disableAllControls(){
		disableChoicesButtons();
		disableStatusComponents();
		disableControlButtons();
	}
	
	public void enableChoicesButtons(){
		toggleButtons(choicesButtonGroup,true);
	}
	public void enableStatusComponents(){
		toggleStatusComponents(true);
	}
	public void enableControlButtons(){
		toggleButtons(controlButtonGroup,true);
	}
	
	public void disableStatusComponents(){
		toggleStatusComponents(false);
	}	
	public void disableControlButtons(){
		toggleButtons(controlButtonGroup,false);
	}
	public void disableChoicesButtons(){
		toggleButtons(choicesButtonGroup,false);
	}
	
	public JButton getControlButtonHaving(JButton thisButton){
		return getButtonFromButtonGroupUsing(thisButton,controlButtonGroup);
	}
	public JButton getControlButtonHaving(String thisString){
		return getButtonFromButtonGroupUsing(thisString,controlButtonGroup);
	}
	public JButton getChoicesButtonHaving(JButton thisButton){
		return getButtonFromButtonGroupUsing(thisButton,choicesButtonGroup);
	}	
	public JButton getChoicesButtonHaving(String thisString){
		return getButtonFromButtonGroupUsing(thisString,choicesButtonGroup);
	}
	
	private void setPreferredButtonSizeOf(ButtonGroup bg,Dimension dimension){
		
		Enumeration<AbstractButton> elements = bg.getElements();
		if(dimension==null){
			throw new IllegalArgumentException("MainView,setPreferredButtonSizeOf : Invalid dimension");
		}
	    while (elements.hasMoreElements()) {
	          AbstractButton button = (AbstractButton)elements.nextElement();
	          button.setPreferredSize(dimension);
	    }	    			
	}
	private void toggleStatusComponents(boolean flag){
		tb_Correct.setEnabled(flag);
		tb_Wrong.setEnabled(flag);
		tb_Remarks.setEnabled(flag);
	}	
	private JButton getButtonFromButtonGroupUsing(JButton buttonObject,ButtonGroup bg){		
		Enumeration<AbstractButton> elements = bg.getElements();
	    while (elements.hasMoreElements()) {
	          AbstractButton button = (AbstractButton)elements.nextElement();
	          if(button.equals(buttonObject)){
	        	  return (JButton)button;
	          }
	    }//will go here if parameter is an invalid Button
	    throw new IllegalArgumentException();
	}
	private JButton getSelectedButton( ButtonGroup bg) {
		Enumeration<AbstractButton> allJButton = bg.getElements();
		JButton temp = null;
		Class<?> buttonType = JButton.class;
		
		while (allJButton.hasMoreElements()) {
			
			temp = (JButton) allJButton.nextElement();
			if (buttonType.isInstance(temp) && temp.isSelected())
				return (JButton)temp;
			
		}
		return null; // none selected
	}	
	private JButton getButtonFromButtonGroupUsing(String buttonText,ButtonGroup bg){
		
		Enumeration<AbstractButton> elements = bg.getElements();
	    while (elements.hasMoreElements()) {
	          AbstractButton button = (AbstractButton)elements.nextElement();
	          if(button.getText().compareTo(buttonText)==0){
	        	  return (JButton)button;
	          }
	    }//will go here if parameter is an invalid Button
	    throw new IllegalArgumentException();
	}
	
	
	public void setChoicesValuesFrom(int[] array){
		setButtonGroupValuesFrom(array,choicesButtonGroup);
	}
	private void setButtonGroupValuesFrom(int[] array,ButtonGroup bg) {
		Enumeration<AbstractButton> allJButton = bg.getElements();
		JButton button = null;
		int i=0;		
		while (allJButton.hasMoreElements()) {
			if(i>array.length) 
				throw new IllegalArgumentException("MainView,setButtonGroupValuesFrom: Array Length Error");
			button = (JButton) allJButton.nextElement();
			button.setText(String.valueOf(array[i++]) );			
		}
	}
	
	
	private void toggleButtons(ButtonGroup bg,boolean enableDisableFlag){
		Enumeration<AbstractButton> elements = bg.getElements();
	    while (elements.hasMoreElements()) {
	          AbstractButton button = (AbstractButton)elements.nextElement();	          	          
	           button.setEnabled(enableDisableFlag);	            	          
	    }
	}

	/*
	 * GUI manipulation stuffs
	 */
	public void moveToCenter() {
		Point center = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getCenterPoint();
		int windowWidth = this.getWidth();
		int windowHeight = this.getHeight();

		this.setBounds(center.x - windowWidth / 2, center.y - windowHeight / 2,
				windowWidth, windowHeight);

	}

    public void setLookAndFeel(String lookAndFeel)
    {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if (lookAndFeel.equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
           System.out.println(ex.getMessage());
        } catch (InstantiationException ex) {
        	System.out.println(ex.getMessage());
        } catch (IllegalAccessException ex) {
        	System.out.println(ex.getMessage());
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        	System.out.println(ex.getMessage());
        }
     
    	this.lookAndFeel = lookAndFeel;
    }
	public String getLookAndFeel() {
		return lookAndFeel;
	}

    public static boolean isConfigAvailable(String configName)
    {  	//returns no slashes at end of output    
    	File f = new File(getWorkingDirectory()+configName);    	
    	return f.isFile() && f.canRead();
    }

    private static String getWorkingDirectory(){
    	return System.getProperty("user.dir")+"\\";
    }
	public Settings getSettings() {
		if(this.settings==null){
			throw new IllegalStateException();
		}
		return settings;
	}
	public void setSettings(Settings settings) {
		if(settings == null){
			throw new IllegalArgumentException("MainView, setSettings: Invalid Settings Argument");
		}
		this.settings = settings;
	}
	
	public void displayErrorMessage(String errorMessage) {
		java.awt.Toolkit.getDefaultToolkit().beep();
		JOptionPane.showMessageDialog(this, errorMessage);
	}
	
	
	public static void main(String[] args) {
		MainView view = new MainView();
		view.moveToCenter();
		view.setLookAndFeel("Metal");
		view.setVisible(true);
	}



	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.ButtonGroup choicesButtonGroup;
	private ButtonGroup controlButtonGroup;
	
	private javax.swing.JButton btn_Choice1;
	private javax.swing.JButton btn_Choice2;
	private javax.swing.JButton btn_Choice3;
	private javax.swing.JButton btn_Choice4;

	private javax.swing.JButton btn_Close;
	private javax.swing.JButton btn_Settings;
	private javax.swing.JButton btn_StartQuiz;
	private javax.swing.JLabel lbl_RightAnswer;
	private javax.swing.JLabel lbl_WrongAnswer;
	private javax.swing.JLabel lbl_Remarks;

	private javax.swing.JLabel lbl_Answer;
	private javax.swing.JLabel lbl_OperandLeft;
	private javax.swing.JLabel lbl_OperandRight;
	private javax.swing.JLabel lbl_Operation;
	private javax.swing.JLabel lbl_Equals;
	


	private javax.swing.JTextField tb_Correct;
	private javax.swing.JTextField tb_Remarks;
	private javax.swing.JTextField tb_Wrong;

	private javax.swing.JPanel questionPanel;
	private javax.swing.JPanel choicesPanel;
	private javax.swing.JPanel statusPanel;
	private javax.swing.JPanel controlPanel;

	private javax.swing.JPanel leftPanel;
	private javax.swing.JPanel rightPanel;

	private Question questionsContentObject = null;

	private javax.swing.JPanel mainPanel;
	private static final long serialVersionUID = 3528855438945160412L;
}
