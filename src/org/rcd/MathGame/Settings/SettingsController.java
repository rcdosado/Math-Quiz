/**
 * 
 */
package org.rcd.MathGame.Settings;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JRadioButton;

import org.rcd.MathGame.Enums.LevelTypes;
import org.rcd.MathGame.Helpers.Properties;

/**
 * @author m1k3y
 * 
 */
public class SettingsController {

	private SettingsView view;
	private SettingsModel model;

	public SettingsView getView() {
		return view;
	}

	public SettingsModel getModel() {
		return model;
	}

	public SettingsController(SettingsView view, SettingsModel model) {

		this.view = view;
		this.model = model;

		// model->load configuration , get level,get operation,get max diff
		
		// etc..
		// view->set configuration , set the GUI using above values

		this.view.levelPanelListener(new LevelPanelListener());
		this.view.settingsWindowListener(new SettingsWindowListener());
		this.view.numItemsFocusListener(new NumItemsFocusListener());
		this.view.maxDiffFocusListener(new MaxDiffFocusListener());

	}

	class MaxDiffFocusListener implements FocusListener {

		@Override
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void focusLost(FocusEvent e) {

			view.getMaxDiffValue();
		}

	}

	class NumItemsFocusListener implements FocusListener {

		@Override
		public void focusGained(FocusEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void focusLost(FocusEvent arg0) {

			view.getNumberOfItemsValue();
		}

	}

	class SettingsWindowListener implements WindowListener {

		public void windowClosing(WindowEvent e) {
			FileOutputStream f = null;
			try {
				f = new FileOutputStream(new File(model.getConfigFileName()));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			

			
			saveViewSettings();
			try {
				Properties.getParentProperties().store(f,
						"Settings has been saved");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

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

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub

		}

	}

	class LevelPanelListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			JRadioButton button = (JRadioButton) arg0.getSource();

			switch (button.getText()) {

			case "Level 1 (1-10)":
				view.customRangeToggle(false);
				break;
			case "Level 2 (11-100)":
				view.customRangeToggle(false);
				break;
			case "Custom Level":
				view.customRangeToggle(true);
				break;
			default:
				break;

			}

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

	}// end levelPanelListener

	private void saveViewSettings() {
		model.getProperties();
		java.util.Properties p = Properties.getParentProperties();
		
		if(view.getSelectedLevelButton()==LevelTypes.Custom){			
			p.setProperty("custom_min", String.valueOf(view.getCustomMinValue()));
			p.setProperty("custom_max", String.valueOf(view.getCustomMaxValue()));
		}
		p.setProperty("level",view.getSelectedLevelButton().toString());
		p.setProperty("operation", view.getSelectedOperationButton().toString());
		p.setProperty("max_difference", String.valueOf(view.getMaxDiffValue()));
		p.setProperty("items", String.valueOf(view.getNumberOfItemsValue()));
	

		

	}

	public static void main(String[] args) {
		System.out.println("Im on the controller");
	}

}
