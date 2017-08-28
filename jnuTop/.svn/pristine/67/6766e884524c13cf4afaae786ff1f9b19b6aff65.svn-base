package com.stylefeng.guns.common.exception;
/**
 * 文件不存在异常
 * @author djb
 *
 */
public class FileNotExistException extends Exception{
	//错误的信息
	private String errorInfo;
	private String FileName;
	

    public FileNotExistException(String FileName){
 	   this.FileName=FileName;
 	   errorInfo=FileName+"不存在";
    }
	
	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public String getFileName() {
		return FileName;
	}

	public void setFileName(String fileName) {
		FileName = fileName;
	}
}
