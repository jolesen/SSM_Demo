package com.stylefeng.guns.common.exception;

public class NonullException extends Exception{
		private String errorInfo;
		public NonullException(int i,int j){
			this.errorInfo = "第 " + (i+1) +"行 , 第" + (j+1) +"列不能为空";
		}
		public String getErrorInfo() {
			return errorInfo;
		}

		public void setErrorInfo(String errorInfo) {
			this.errorInfo = errorInfo;
		}
		
}
