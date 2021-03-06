package com.stylefeng.guns.modular.business.util;



import java.lang.reflect.Method;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import com.stylefeng.guns.modular.business.entity.Project;
import com.stylefeng.guns.modular.business.service.IProjectService;
/**
 * Excel处理字段可能会用的到的工具
 * @author djb
 *
 */
public class ExcelSpecialHanding {
	
	/**
	 * 这是一个填充字符串的方法，前面补0
	 * 
	 * @param needFill
	 *            需要操作的字段名
	 * @param number
	 *            需要补充到多少位
	 * @throws Exception
	 * 
	 */
	public static String FillString(String needFill,int number){
		    String result=null;
		    int len=needFill.length();
		    if(len>=number){
		    	result=needFill;
		    }else{
		    	for(int i=0;i<(number-len);i++){
		    		needFill="0"+needFill;
		    	}
		    	result=needFill;
		    }   
		    return result;
	}
}
