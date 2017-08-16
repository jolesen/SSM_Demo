package com.stylefeng.guns.common.exception;


public class DBErrorException extends Exception{
	//错误的信息
	private String errorInfo;
	//需要返回给前端的提示
	private String prompt;
	
	public DBErrorException(String prompt,String errorInfo){
		this.prompt=prompt;
		this.errorInfo=errorInfo;
	}
	
	public String getErrorInfo() {
		return errorInfo;
	}
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
	public String getPrompt() {
		return prompt;
	}
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}
	
}