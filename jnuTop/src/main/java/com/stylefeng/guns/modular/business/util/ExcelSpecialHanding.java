package com.stylefeng.guns.modular.business.util;



import java.lang.reflect.Method;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import com.stylefeng.guns.modular.business.entity.Project;
import com.stylefeng.guns.modular.business.service.IProjectService;
/**
 * Excel处理字段工具
 * @author djb
 *
 */
public class ExcelSpecialHanding {


	private IProjectService projectService;
	
	private String title;
	
	private String projectName;
	private HashMap<String, String> holiday;
	private Integer tableCount;
	private Project project=null;
	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
        project= projectService.getProjectByName(projectName);
	}

	/**
	 * 初始化工具，填入必填内容
	 * 
	 * @param title
	 *            Excel的表头
	 * 
	 */
	public ExcelSpecialHanding(String title,IProjectService projectService,Integer tableCount) {
		this.title = title;
        this.projectService=projectService;
        this.tableCount = tableCount;
        
		// 目前holiday先这样
		holiday = new HashMap<String, String>();
//		holiday.put("2017-08-28", "公司游玩日");
//		holiday.put("2017-09-11", "xxx节日");
	}

	/**
	 * 这是一个根据表名，字段名来对特殊的字段进行操作的函数
	 * 
	 * @param fieldName
	 *            需要操作的字段名
	 * @param entityClass
	 *            需要修改字段的类的Class
	 * @param entity
	 *            需要修改字段的类
	 * @throws Exception
	 * 
	 */
	public <T> String SpecialHanding(String fieldName, Class<T> entityClass, T entity) throws Exception {
		String result = null;
		
		if ("Sample".equals(title))
			System.out.println("handing开始调用方法："+fieldName);
		    result=SampleExcelHanding(fieldName, entityClass, entity);
		return result;
	}

	private <T> String SampleExcelHanding(String fieldName, Class<T> entityClass, T entity) throws Exception {
		String result = null;
		
		/*
		 * 录入时间操作 
		 * 生成规则：今天的日期
		 */
		if("acceptDate".equals(fieldName)){
			System.out.println("进入了"+fieldName+"方法");
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
			Method getAcceptDate = entityClass.getMethod("getAcceptDate", new Class[] {});
			Object startDate =  getAcceptDate.invoke(entity, new Object[] {});
			
			if(startDate!=null){
				result=sdf.format((Date)startDate);
			}
			else{
				Date date=new Date();
				result=sdf.format(date);
			}
			
		}
		  
		/*
		 * 理论报告时间操作 
		 * 生成规则：从项目得到是否非工作日，录入日期+报告所需时间=理论日期
		 */
		else if ("expectedReportTime".equals(fieldName)) {
			System.out.println("进入了"+fieldName+"方法");
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
			
			Method getExpectedReportTime = entityClass.getMethod("getExpectedReportTime", new Class[] {});
			Object expectedReportTime = getExpectedReportTime.invoke(entity, new Object[] {});
			
			if(expectedReportTime!=null){
				result=sdf.format((Date)expectedReportTime);
			}
			else{
				
				Method getDetectionDuration = entityClass.getMethod("getDetectionDuration", new Class[] {});
				int workDay = (int) getDetectionDuration.invoke(entity, new Object[] {});
				
				Method getAcceptDate = entityClass.getMethod("getAcceptDate", new Class[] {});
				Date startDate = (Date) getAcceptDate.invoke(entity, new Object[] {});
				
				
				System.out.println("projectName="+projectName+" project为："+project);
				if (project.getProjectOrder() == 0) {
					result=sdf.format(CalendarUtil.getDay(startDate, workDay));
				} else {
					
					result=sdf.format(CalendarUtil.getWorkDay(startDate, workDay, holiday));
				}
			}
			
		}
		
		/*
		 * 实验试编码操作 
		 * 生成规则：一共10位， 年份（后两位）+项目的id（占3位，不足用0前面补齐）+顺序（该项目当前的条数增加，5位，不足前面补0）
		 */
		else if("labCode".equals(fieldName)){
			 result="";
			 String first=CalendarUtil.getYear(); 
			 String second=FillString(project.getId().toString(),3);
			 String third=FillString(tableCount.toString(),5);
			 result=first+second+third;
			 tableCount++;
		}
		return result;
	}
		
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
	private static String FillString(String needFill,int number){
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
