package com.practice.sequence.dto;

public class RequestNumberVO {

	private String goal;
	private String step;
	
	public String getGoal() {
		return goal;
	}
	public void setGoal(String goal) {
		this.goal = goal;
	}
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}
	@Override
	public String toString() {
		return "RequestNumberVO [goal=" + goal + ", step=" + step + "]";
	}
}
