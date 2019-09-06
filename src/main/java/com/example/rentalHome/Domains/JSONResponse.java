package com.example.rentalHome.Domains;

import lombok.*;

public class JSONResponse {
	//JSONStatus
	public static String FAIL="FAIL";
	public static String ERROR = "ERROR";
	public static String SUCCESS="SUCCESS";
	private String result;
	private String message;
	private Object data;
	public JSONResponse(String result, String message, Object data) {
		super();
		this.result = result;
		this.message = message;
		this.data = data;
	}
	public static String getFAIL() {
		return FAIL;
	}
	public static void setFAIL(String fAIL) {
		FAIL = fAIL;
	}
	public static String getERROR() {
		return ERROR;
	}
	public static void setERROR(String eRROR) {
		ERROR = eRROR;
	}
	public static String getSUCCESS() {
		return SUCCESS;
	}
	public static void setSUCCESS(String sUCCESS) {
		SUCCESS = sUCCESS;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public JSONResponse() {
		super();
	}
	
}
