package org.rcd.MathGame.Settings;

import org.rcd.MathGame.MainApp.MainView;
import org.rcd.MathGame.Settings.SettingsView;

public class SettingsDialog {

	public SettingsDialog(String config, final MainView parent) {


		final SettingsModel model = new SettingsModel(config);
		final SettingsView view = new SettingsView(parent, true);
		
		
		new SettingsController(view, model);	

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				view.setComponentValues(model.getProperties());
				if(parent!=null)
					view.setLookAndFeel(parent.getLookAndFeel());
				else
					view.setLookAndFeel("Classic");
				view.moveToCenter();
				view.setVisible(true);
				
			}
		});
	}

	public static void main(String[] args) {
		new SettingsDialog("config.ini", null);

	}// end main

}
