package org.rcd.MathGame.Settings;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;

import org.rcd.MathGame.Helpers.Properties;
import org.rcd.MathGame.MainApp.MainModel;



public class SettingsModel {

	Properties properties = null;
	
	 
	public Properties getProperties() {
		if (properties == null) {
			throw new IllegalArgumentException("Properties has not been set");
		}
		return properties;
	}

	public void setProperties(Properties properties) {
		if (properties != null) {
			throw new IllegalStateException("Properties has been already set");
		}
		this.properties = properties;
	}

	String configFileName = "";

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

	public SettingsModel(String file) {

		if (!isExisting(file)) {
			createDefaultSettings(file);
			loadConfigFile(file);
		} else {
			loadConfigFile(file);
			
		}// end else

		this.setConfigFileName(file);
	}// end method

	public SettingsModel(Properties properties) {
		if (isPropertiesValid(properties)) {
			this.properties = properties;
		} else {
			throw new IllegalArgumentException();
		}

	}

	private boolean isExisting(String file) {
		File f = new File(file);
		boolean returnme = false;
		returnme = f.isFile() && f.canRead();
		return returnme;
	}

	private boolean createDefaultSettings(String file) {

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

	private void loadConfigFile(String file) {
		Properties tmpProp = null;
		try {
			File f = new File(file);
			tmpProp = Properties.load(f.getAbsolutePath());
			checkValidProperties(tmpProp);
			
		} catch (IOException e) {
			
		} // end catch
	}

	private void checkValidProperties(Properties tmpProp) {
		if (isPropertiesValid(tmpProp)) {
			this.properties = tmpProp;
		} else {
			throw new IllegalArgumentException();
		}
	}

	private boolean isPropertiesValid(Properties properties) {
		try {
				properties.contains(MainModel.getDefaultSettingsValues());
		} catch (NoSuchElementException e) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) throws Exception {
		// String md5 = MD5Checksum.getMD5Checksum("config.ini");
		// System.out.println(md5);

	}

}// end of SettingsModel class

