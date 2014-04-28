package org.rcd.MathGame.MainApp;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import org.rcd.MathGame.Helpers.Level;
import org.rcd.MathGame.Helpers.Operands;
import org.rcd.MathGame.Helpers.Operator;
import org.rcd.MathGame.Helpers.Question;
import org.rcd.MathGame.Helpers.Score;
import org.rcd.MathGame.Helpers.Settings;
import org.rcd.MathGame.Settings.SettingsDialog;
import org.rcd.MathGame.util.Tools;
import org.rcd.MathGame.util.Tools.StopWatch;

public class MainController {

		
	private MainView view;
	private MainModel model;
	private StopWatch stopWatch;

	private int questionAskedCounter;
	private int lockerValue = 0; 
	


	public MainModel getModel() {
		return model;
	}

	public void setModel(MainModel model) {
		this.model = model;
	}

	public MainController(MainView view, MainModel model) {

		this.view = view;
		this.model = model;

		this.view.setSettingsObjectFrom(this.model.getProperties());
		
		installFrameListeners();
		installControlButtonsListeners();
		installChoiceButtonsListeners();
	}

	private void installFrameListeners(){
		this.view.mainViewFrameListener(new MainViewFrameListener());
	}
	private void installChoiceButtonsListeners() {
		ChoicesButtonListener choicesListener = new ChoicesButtonListener();
		this.view.choiceOneButtonListener(choicesListener);
		this.view.choiceTwoButtonListener(choicesListener);
		this.view.choiceThreeButtonListener(choicesListener);
		this.view.choiceFourButtonListener(choicesListener);
	}

	private void installControlButtonsListeners() {
		this.view.startQuizButtonListener(new StartQuizButtonListener());
		this.view.closeButtonListener(new CloseButtonListener());
		this.view.settingsButtonListener(new SettingsButtonListener());
	}

	private void onClosingEvent(){
        //default icon, custom title
        int reply = JOptionPane.showConfirmDialog(
            null,
            "Are you sure, you want to exit ?",
            "Goodbye",
            JOptionPane.YES_NO_OPTION);
        if(reply==JOptionPane.YES_OPTION){
			view.dispose();
        	System.exit(0);
        }        
	}
	class MainViewFrameListener implements WindowListener {

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent e) {

			onClosingEvent();
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	class StartQuizButtonListener implements ActionListener {

		JButton startButton;
		JButton stopButton;
		JButton settingsButton;
		Settings settings;

		Question sessionQuestionObj;

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JButton startButton = (JButton) arg0.getSource();
			if (startButton.getText().compareTo("Start") == 0) {
				startQuiz();
				lockerValue++;
			} else { // if Next
				view.enableChoicesButtons();
				if(++lockerValue>1){
					view.displayErrorMessage("Select first from choices before clicking next");	
					return;
				}
				askNewQuestion();
							
			}
		}

		private void reset() {
			// initial text has been save during view constructor, this restores
			// it
			view.restorePanelTexts();
			model.reloadSettings();
			
		}

		private void startQuiz() {
			Tools tools = new Tools();
			
			reset();
			startButton = view.getControlButtonHaving("Start");
			stopButton = view.getControlButtonHaving("Close");
			settingsButton = view.getControlButtonHaving("Settings");
			settings = model.getSettingsObject();
			
			stopWatch = tools.new StopWatch();

			model.setScoreObject(new Score());

			startButton.setText("Next");
			stopButton.setText("Stop");
			settingsButton.setEnabled(false);

			stopWatch.Start();
			
			questionAskedCounter = 1;			
			askNewQuestion();

		}

		private void askNewQuestion() {
			// generate next question based on level,operator,maxdiff,items
			Question question = new Question();

			question.setLevelObject(new Level(settings.getLevel()));
			question.setOperatorObject(new Operator(settings.getOperator().getOperation()));
			question.setOperandsObject(new Operands(question.getLevelObject()));
			question.setSettings(settings);

			Operands operands = question.getOperandsObject();
			Operator operator = question.getOperatorObject();

			question.getOperandsObject().generateRandomOperands();
			question.setLeftOperand(operands.getFirstOperandValue());
			question.setOperation(operator.toChar());
			question.setRightOperand(operands.getSecondOperandValue());
											
			model.setQuestionObject(question);
			
			generateChoicesFrom(question);			
			view.setQuestionsContent(question);
			
		}

		private void generateChoicesFrom(Question question) {			
			question.computeAnswer();
			int ans = question.getAnswer();
			int maxDiffLow = settings.getMaxDiffLess(ans);
			int maxDiffHigh = settings.getMaxDiffHigh(ans);	
			int[] array  = model.generateChoices(maxDiffLow,maxDiffHigh,ans);
			view.setChoicesValuesFrom( array);
		}

	}

	class CloseButtonListener implements ActionListener {
		JButton settingsButton = view.getControlButtonHaving("Settings");

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JButton pressedButton = (JButton) arg0.getSource();
			if (pressedButton.getText().compareTo("Close") == 0) {				
				onCloseClicked();
			}else{ // its Stop			
				onStopClicked();
			}
		}// end method
		private void onCloseClicked(){
			onClosingEvent();
		}
		private void onStopClicked(){
			stopWatch.Stop();
			view.restorePanelTexts();
			view.enableAllControls();
		}

	}//end class

	class SettingsButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			new SettingsDialog("config.ini", view);

		}

	}

	class ChoicesButtonListener implements ActionListener {

		Question question = null;
	

		@Override
		public void actionPerformed(ActionEvent arg0) {

			JButton choiceButton = (JButton) arg0.getSource();
			if( !hasStarted()){ //if button is not Next then
				return;
			}
			if(model.getScoreObject()!=null){
				onChoiceClicked(choiceButton);
			}else{
				view.displayErrorMessage("clicked start button first");
			}
		}


		private void onChoiceClicked(JButton pressedButton) {
			String userAnswer = pressedButton.getText();
			Score score = model.getScoreObject();
			question = model.getQuestionObject();

			int numberOfItems = model.getSettingsObject().getNumberOfItems();
						
			if (model.isCorrect(Integer.parseInt(userAnswer), question)) {
				score.incrementCorrect();				
			} else {
				score.incrementWrong();				
				beep();
			}
			displayStatusAndAnswer(score, "");
			if(questionAskedCounter>=numberOfItems){				
				stopWatch.Stop();
				view.restorePanelTexts();
				view.enableAllControls();beep();	
				displayQuizResult(score);
				return;
			}
			view.setRemarks(questionAskedCounter+" of "+numberOfItems+ " items");
			questionAskedCounter++;
			view.disableChoicesButtons();			
			lockerValue= 0;

		}

		private void beep(){
			java.awt.Toolkit.getDefaultToolkit().beep();
		}
		private void displayQuizResult(Score score) {
			StringBuilder sb = new StringBuilder();
			DecimalFormat formatter = new DecimalFormat("##.00");
			sb.append("Number of Items : " + score.getTotalItems()+"\n");
			sb.append("Correct Answers : "+score.getCorrectScore()+"\n");
			sb.append("Wrong Answers : "+score.getWrongScore()+"\n");
			sb.append("Quiz Duration: "+stopWatch.GetMinutes()+" mins "+
										stopWatch.GetSeconds()+" seconds\n");
			sb.append("Grade : "+formatter.format(score.ComputeGrade(50.0))+"%\n");				
			JOptionPane.showMessageDialog(view, sb.toString());
		}

		private void displayStatusAndAnswer(Score score, String remarks) {
			view.setStatusPanelTexts(score.getCorrectScore(),
					score.getWrongScore(), remarks);				
			model.getQuestionObject().computeAnswer();
			view.setQuestionsContentWithAnswer(question);
		}
		
		public boolean hasStarted(){
			return isStartButtonContains("Next");
		}
		private boolean isStartButtonContains(String text){			
				try{
					view.getControlButtonHaving(text);
					return true;
				}catch(IllegalArgumentException e){
					return false;
				}			
		}

	}


	public static void main(String[] args) {
		System.out.println("You are in MainController");

	}

}
