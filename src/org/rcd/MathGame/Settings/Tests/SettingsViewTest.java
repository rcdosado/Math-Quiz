package org.rcd.MathGame.Settings.Tests;

import static org.junit.Assert.*;

import javax.swing.JFrame;

import org.junit.Before;
import org.junit.Test;
import org.rcd.MathGame.Enums.LevelTypes;
import org.rcd.MathGame.Enums.OperationUsed;
import org.rcd.MathGame.Settings.SettingsView;

public class SettingsViewTest {
	SettingsView view;

	@Before
	public void setUp() throws Exception {
		view = new SettingsView(new JFrame(), true);

	}

	@Test
	public void differentInputsTest() {

		view.selectLevel(LevelTypes.Level2);
		assertEquals(LevelTypes.Level2, view.getSelectedLevelButton());

		view.selectLevel(LevelTypes.Custom);
		assertEquals(LevelTypes.Custom, view.getSelectedLevelButton());

		view.selectOperation(OperationUsed.Addition);
		assertEquals(OperationUsed.Addition, view.getSelectedOperationButton());

		view.selectOperation(OperationUsed.Subtraction);
		assertEquals(OperationUsed.Subtraction,
				view.getSelectedOperationButton());

		view.selectOperation(OperationUsed.Multiplication);
		assertEquals(OperationUsed.Multiplication,
				view.getSelectedOperationButton());

		view.selectOperation(OperationUsed.Division);
		assertEquals(OperationUsed.Division, view.getSelectedOperationButton());

		for (int i = 10; i < 100; i += 10) {
			view.setNumberOfItemsValue(i);
			view.setMaxDiffValue(i);
			assertEquals(i, view.getNumberOfItemsValue());
			assertEquals(i, view.getMaxDiffValue());

		}

	}

	@Test(expected = IllegalArgumentException.class)
	public void setMaxDiffValueTest() {
		view.setMaxDiffValue(5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setNumberOfItemsValueTest() {
		view.setNumberOfItemsValue(4);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setCustomLevelValuesTest() {
		view.setCustomLevelValues(25, 5);
	}

}
