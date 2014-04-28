/**
 * 
 */
package org.rcd.MathGame.Settings;

import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;

import java.util.Enumeration;

import javax.swing.*;

import org.rcd.MathGame.Enums.LevelTypes;
import org.rcd.MathGame.Enums.OperationUsed;
import org.rcd.MathGame.Helpers.Properties;

import net.miginfocom.swing.MigLayout;

/**
 * @author m1k3y
 */

// use setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);

public class SettingsView extends javax.swing.JDialog {

	private static final long serialVersionUID = 835851927430645008L;

	private String lookAndFeel=""; 


	public SettingsView(java.awt.Frame parent, boolean modal) {
		super(parent, modal);

		initComponents();

	}

	
	public void setComponentValues(Properties properties) {

		String level = properties.getString("level");
		String operation = properties.getString("operation");
		String maxdiff = properties.getString("max_difference");
		String items = properties.getString("items");
		String min = properties.getString("custom_min");
		String max = properties.getString("custom_max");

		if (level.compareTo("Level1") == 0) {
			customRangeToggle(false);
			rb_Level1.setSelected(true);
		} else if (level.compareTo("Level2") == 0) {
			customRangeToggle(false);
			rb_Level2.setSelected(true);
		} else {
			rb_CustomLevel.setSelected(true);
			customRangeToggle(true);
			tb_RangeLo.setText(min);
			tb_RangeHi.setText(max);			
		}

		if (operation.compareTo("Addition") == 0) {
			rb_Addition.setSelected(true);
		} else if (operation.compareTo("Subtraction") == 0) {
			rb_Subtraction.setSelected(true);
		} else if (operation.compareTo("Multiplication") == 0) {
			rb_Multiplication.setSelected(true);
		} else {
			rb_Division.setSelected(true);
		}
		tb_maxDiff.setText(maxdiff);
		tb_NumberOfItems.setText(items);

	}

	private void initComponents() {
		LevelButtons = new ButtonGroup();
		OperatorButtons = new ButtonGroup();

		leftPanel = new JPanel(new MigLayout());
		rb_Level1 = new JRadioButton();
		rb_Level2 = new JRadioButton();
		rb_CustomLevel = new JRadioButton();
		tb_RangeLo = new JTextField(2);
		tb_RangeHi = new JTextField(2);
		jLabel3 = new JLabel();

		rightPanel = new JPanel(new MigLayout());
		rb_Addition = new JRadioButton();
		rb_Subtraction = new JRadioButton();
		rb_Multiplication = new JRadioButton();
		rb_Division = new JRadioButton();

		jLabel1 = new JLabel();
		tb_NumberOfItems = new JTextField(2);
		jLabel2 = new JLabel();
		tb_maxDiff = new JTextField(2);

		mainPanel = new JPanel(new MigLayout());
		
		/*
		 * Window settings for the main dialog
		 */
		this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		this.setTitle("Settings ");

		this.setResizable(false);

		/*
		 * settings for the Level Panel
		 */

		leftPanel.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Level"));
		leftPanel.setToolTipText("Left Panel!");

		LevelButtons.add(rb_Level1);
		rb_Level1.setMnemonic('1');
		rb_Level1.setText("Level 1 (1-10)");

		LevelButtons.add(rb_Level2);
		rb_Level2.setMnemonic('2');
		rb_Level2.setText("Level 2 (11-100)");

		LevelButtons.add(rb_CustomLevel);
		rb_CustomLevel.setMnemonic('s');
		rb_CustomLevel.setText("Custom Level");

		tb_RangeHi.setEnabled(false);
		tb_RangeLo.setEnabled(false);

		/*
		 * MigLayout Settings
		 */
		leftPanel.add(rb_Level1, "wrap");
		leftPanel.add(rb_Level2, "wrap");
		leftPanel.add(rb_CustomLevel, "wrap");

		tb_RangeLo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		leftPanel.add(tb_RangeLo, "gapx 20px,split3");

		jLabel3.setText("--");
		leftPanel.add(jLabel3);

		tb_RangeHi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		leftPanel.add(tb_RangeHi);

		mainPanel.add(leftPanel, "top,split2");

		/*
		 * Right Panel settings
		 */
		rightPanel.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Operator"));
		rightPanel.setToolTipText("Right Panel!");

		OperatorButtons.add(rb_Addition);
		rb_Addition.setMnemonic('i');
		rb_Addition.setText("Addition");

		OperatorButtons.add(rb_Subtraction);
		rb_Subtraction.setMnemonic('s');
		rb_Subtraction.setText("Subtraction");

		OperatorButtons.add(rb_Multiplication);
		rb_Multiplication.setMnemonic('m');
		rb_Multiplication.setText("Multiplication");

		OperatorButtons.add(rb_Division);
		rb_Division.setMnemonic('v');
		rb_Division.setText("Division");

		/*
		 * MigLayout Settings for operations
		 */
		rightPanel.add(rb_Addition, "wrap");
		rightPanel.add(rb_Subtraction, "wrap");
		rightPanel.add(rb_Multiplication, "wrap");
		rightPanel.add(rb_Division, "wrap");

		/*
		 * Number of Items
		 */
		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabel1.setText("Number of Items :");
		jLabel1.setToolTipText("Number of Items");

		tb_NumberOfItems.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		tb_NumberOfItems.setText("5");

		/*
		 * Max Differences ( how far is the incorrect answer from the correct
		 * one)
		 */
		jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabel2.setText("Max Difference of Choice from the answer:");

		tb_maxDiff.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		tb_maxDiff.setText("10");

		/*
		 * add them using MigLayout
		 */

		mainPanel.add(rightPanel, "top,wrap");

		mainPanel.add(jLabel1, "left,split2,growx");
		mainPanel.add(tb_NumberOfItems, "wrap");

		mainPanel.add(jLabel2, "split2,growx");
		mainPanel.add(tb_maxDiff);

		this.add(mainPanel);
		this.pack();
	}

	public void settingsWindowListener(WindowListener listenForWinEvents) {
		this.addWindowListener(listenForWinEvents);
	}

	/*
	 * Level Event Handlers
	 */
	public void levelPanelListener(MouseListener listenLevelChange) {
		rb_Level1.addMouseListener(listenLevelChange);
		rb_Level2.addMouseListener(listenLevelChange);
		rb_CustomLevel.addMouseListener(listenLevelChange);
	}

	public void numItemsFocusListener(FocusListener listenFocus) {
		tb_NumberOfItems.addFocusListener(listenFocus);
	}

	public void maxDiffFocusListener(FocusListener listenFocus) {
		tb_maxDiff.addFocusListener(listenFocus);
	}

	public void moveToCenter() {
		Point center = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getCenterPoint();
		int windowWidth = this.getWidth();
		int windowHeight = this.getHeight();

		this.setBounds(center.x - windowWidth / 2, center.y - windowHeight / 2,
				windowWidth, windowHeight);

	}

	private void displayErrorMessage(String errorMessage) {
		java.awt.Toolkit.getDefaultToolkit().beep();
		JOptionPane.showMessageDialog(this, errorMessage);
	}

	/*
	 * SettingsView getters
	 */

	// 1. getLevel
	public LevelTypes getSelectedLevelButton() {
		JRadioButton op = getSelectedRadio(LevelButtons);
		if (op == null) {
			throw new IllegalArgumentException();
		}
		if (op.getText().equalsIgnoreCase("Level 1 (1-10)"))
			return LevelTypes.Level1;
		else if (op.getText().equalsIgnoreCase("Level 2 (11-100)"))
			return LevelTypes.Level2;
		else
			return LevelTypes.Custom;
	}

	// 2. getOperation
	public OperationUsed getSelectedOperationButton() {
		JRadioButton op = getSelectedRadio(OperatorButtons);

		if (op == null) {
			throw new IllegalArgumentException();
		}
		switch (op.getText()) {
		case "Addition":
			return OperationUsed.Addition;
		case "Subtraction":
			return OperationUsed.Subtraction;
		case "Multiplication":
			return OperationUsed.Multiplication;
		case "Division":
			return OperationUsed.Division;
		}
		return OperationUsed.Addition;
	}

	// 3. get number of items
	public int getNumberOfItemsValue() {
		int items = 0;
		try {
			String input = tb_NumberOfItems.getText();
			items = Integer.parseInt(input);
			if (items <= 4)
				throw new NumberFormatException();
		} catch (NumberFormatException ex) { // parseInt error
			displayErrorMessage("Invalid Input for Number of Items");
			tb_NumberOfItems.setText("5");
		}

		return items;
	}

	// 4. get maxdiff
	public int getMaxDiffValue() {
		int diff = 0;
		try {
			String input = tb_maxDiff.getText();
			diff = Integer.parseInt(input);
			if (diff < 10)
				throw new NumberFormatException();
		} catch (NumberFormatException ex) { // parseInt error
			displayErrorMessage("Invalid Input for Max Differences");
			tb_maxDiff.setText("10");
		}

		return diff;
	}
	
	
	public int getCustomMaxValue(){
		return getCustomTextValueOf(tb_RangeHi);
	}
	public int getCustomMinValue(){
		return getCustomTextValueOf(tb_RangeLo);
	}
	private int getCustomTextValueOf(JTextField field){
		String txtValue = field.getText();
		int intValue;
		if(txtValue!="")
			intValue = Integer.parseInt(txtValue);
		else
			throw new IllegalArgumentException();
		
		return intValue;
	}
	
	
	/*
	 * These are the class's Setters ============================
	 */

	// 1. view.selectLevel(LevelTypes );
	public void selectLevel(LevelTypes types) {
		if (types == LevelTypes.Level1)
			rb_Level1.setSelected(true);
		else if (types == LevelTypes.Level2)
			rb_Level2.setSelected(true);
		else
			rb_CustomLevel.setSelected(true);

	}

	public void selectOperation(OperationUsed op) {
		if (op == OperationUsed.Addition)
			rb_Addition.setSelected(true);
		else if (op == OperationUsed.Subtraction)
			rb_Subtraction.setSelected(true);
		else if (op == OperationUsed.Multiplication)
			rb_Multiplication.setSelected(true);
		else if (op == OperationUsed.Division)
			rb_Division.setSelected(true);
		else
			throw new IllegalArgumentException();
	}

	public void setCustomLevelValues(int min, int max) {
		if (min >= max || min == 0 || max == 0)
			throw new IllegalArgumentException();

		tb_RangeLo.setText(String.valueOf(min));
		tb_RangeHi.setText(String.valueOf(max));
	}

	public void customRangeToggle(boolean flag) {
		tb_RangeLo.setEnabled(flag);
		tb_RangeHi.setEnabled(flag);

	}

	public void setNumberOfItemsValue(int count_items) {
		if (count_items <= 4) // less than default
			throw new IllegalArgumentException();
		tb_NumberOfItems.setText(String.valueOf(count_items));
	}

	public void setMaxDiffValue(int maxDiff) {
		if (maxDiff < 10) // less than default
			throw new IllegalArgumentException();
		tb_maxDiff.setText(String.valueOf(maxDiff));
	}
	
	// courtesy of google
	private JRadioButton getSelectedRadio(ButtonGroup bg) {
		Enumeration<AbstractButton> allRadioButton = bg.getElements();
		JRadioButton temp = null;
		Class<?> radioType = JRadioButton.class;

		while (allRadioButton.hasMoreElements()) {

			temp = (JRadioButton) allRadioButton.nextElement();
			if (radioType.isInstance(temp) && temp.isSelected())
				return temp;

		}
		return null; // none selected
	}

	public void setLookAndFeel(String lookAndFeel){
		// got this code from NetBean's templates
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if (lookAndFeel.equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(SettingsView.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(SettingsView.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(SettingsView.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(SettingsView.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		}
		this.lookAndFeel = lookAndFeel;
	}	
	public String getLookAndFeel() {
		return lookAndFeel;
	}
	


	private javax.swing.ButtonGroup LevelButtons;
	private javax.swing.ButtonGroup OperatorButtons;

	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;

	private javax.swing.JPanel leftPanel;
	private javax.swing.JPanel rightPanel;

	private javax.swing.JRadioButton rb_Level1;
	private javax.swing.JRadioButton rb_Level2;
	private javax.swing.JRadioButton rb_CustomLevel;
	private javax.swing.JTextField tb_RangeHi;
	private javax.swing.JTextField tb_RangeLo;

	private javax.swing.JRadioButton rb_Addition;
	private javax.swing.JRadioButton rb_Subtraction;
	private javax.swing.JRadioButton rb_Multiplication;
	private javax.swing.JRadioButton rb_Division;

	private javax.swing.JTextField tb_NumberOfItems;
	private javax.swing.JTextField tb_maxDiff;
	private JPanel mainPanel;


}
