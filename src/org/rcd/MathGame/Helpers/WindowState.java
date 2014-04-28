package org.rcd.MathGame.Helpers;

import java.util.ArrayList;

public class WindowState {

	
	private ArrayList<String> question;
	private ArrayList<String> choices;
	private ArrayList<String> status;
	private ArrayList<String> control;
	


	public WindowState(){
		question = new ArrayList<String>();
		choices  = new ArrayList<String>();
		status	 = new ArrayList<String>();
		control	 = new ArrayList<String>();	
	}
	
	public ArrayList<String> getQuestionListObject() {		
		return question;
	}
	public void setQuestionListObject(ArrayList<String> question) {
		checkNull(question);
		this.question = question;
	}
	public ArrayList<String> getChoicesListObject() {
		return choices;
	}
	public void setChoicesListObject(ArrayList<String> choices) {
		checkNull(choices);
		this.choices = choices;
	}
	public ArrayList<String> getStatusListObject() {
		return status;
	}
	public void setStatusListObject(ArrayList<String> status) {
		checkNull(status);
		this.status = status;
	}
	public ArrayList<String> getControlObject() {		
		return control;
	}

	public void setControlObject(ArrayList<String> control) {
		checkNull(control);
		this.control = control;
	}
	private void checkNull(Object obj){
		if(obj==null){
			throw new IllegalArgumentException("Invalid Argument, NULL value");
		}
	}
	
	public static void main(String[] args){
		WindowState state = new WindowState();
		ArrayList<String> choices = state.getChoicesListObject();
		choices.add("Button1");
		choices.add("Button2");
		
		System.out.println(choices.get(0));
		System.out.println(choices.get(1));
		
		
	}

}
