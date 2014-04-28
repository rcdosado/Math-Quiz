package org.rcd.MathGame.MainApp;

public class MainDialog {

	public MainDialog() {
		

		final MainModel model = new MainModel(); 
		final MainView view = new MainView();

		
		new MainController(view, model);

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				view.setLookAndFeel("Classic");
				view.moveToCenter();
				view.setVisible(true);
			}
		});
	}

	public static void main(String[] args) {
		new MainDialog();

	}// end main

}
