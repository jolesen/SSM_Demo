package com.stylefeng.guns.common.exception;

/**
 * 错误信息的打包类
 * 让前端显示和后台日志记录分开
 * @author djb
 *
 */
public class ErrorWrapException extends Exception{
	//错误的信息
	private String errorInfo;
	//需要返回给前端的提示
	private String prompt;
	
	public ErrorWrapException(String prompt,String errorInfo){
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