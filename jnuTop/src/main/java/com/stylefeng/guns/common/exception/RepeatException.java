package com.stylefeng.guns.common.exception;

public class RepeatException extends Exception{
		private String errorInfo;


		public String getErrorInfo() {
			return errorInfo;
		}


		public void setErrorInfo(String errorInfo) {
			this.errorInfo = errorInfo;
		}


		public RepeatException(int i){
			this.errorInfo = "第" + (i+1) +"行数据重复";
		}
}
