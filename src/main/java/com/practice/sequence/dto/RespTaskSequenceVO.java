package com.practice.sequence.dto;

import java.util.List;

public class RespTaskSequenceVO {

	private String status;
	private List<String> result;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<String> getResult() {
		return result;
	}
	public void setResult(List<String> result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return "RespTaskSequenceVO [status=" + status + ", result=" + result + "]";
	}
}
