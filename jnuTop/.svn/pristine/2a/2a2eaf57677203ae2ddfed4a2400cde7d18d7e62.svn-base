package com.stylefeng.guns.modular.business.util.excelhanding;



import java.lang.reflect.Method;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.stereotype.Component;

import com.alibaba.druid.sql.visitor.functions.If;
import com.stylefeng.guns.core.util.SpringContextHolder;
import com.stylefeng.guns.modular.business.entity.Project;
import com.stylefeng.guns.modular.business.entity.Sample;
import com.stylefeng.guns.modular.business.service.IProjectService;
import com.stylefeng.guns.modular.business.util.CalendarUtil;
import com.stylefeng.guns.modular.business.util.ExcelSpecialHanding;
import com.stylefeng.guns.modular.business.util.IBaseExcelSpecialHanding;
/**
 * Excel处理字段工具
 * @author djb
 *
 */
@Component
public class SampleHanding implements IBaseExcelSpecialHanding<Sample>{

    @Resource
	private IProjectService projectService;
	
	private String projectName;

	private HashMap<String, String> holiday;
	private Integer tableCount;
	private Project project=null;
	
	@Override
	public String SpecialHanding(String fieldName, Sample entity) {
		String result = null;
		holiday=new HashMap<String, String>();
		/*
		 * 录入时间操作 
		 * 生成规则：今天的日期
		 */
		if("acceptDate".equals(fieldName)){
			System.out.println("进入了"+fieldName+"方法");
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 

			Object startDate =  entity.getAcceptDate();
			
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
			
			Object expectedReportTime = entity.getExpectedReportTime();
			
			if(expectedReportTime!=null){
				result=sdf.format((Date)expectedReportTime);
			}
			else{
				int workDay = (int) entity.getDetectionDuration();	
				Date startDate = (Date)entity.getAcceptDate();

//				projectService=SpringContextHolder.getBean("com.stylefeng.guns.modular.business.service.IProjectService");
				project= projectService.getProjectByName(projectName);
				
				System.out.println("project："+project+" 服务类"+projectService+" projectName："+projectName);
				
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
			tableCount=entity.selectCount(null);
			
			 result="";
			 String first=CalendarUtil.getYear(); 
			 String second=ExcelSpecialHanding.FillString(project.getId().toString(),3);
			 String third=ExcelSpecialHanding.FillString(tableCount.toString(),5);
			 result=first+second+third;
			 tableCount++;
		}
		
		return result;
	}

	@Override
	public void getParameter(XSSFSheet sheet) {
		/* 初始化handing的projectId */
		XSSFRow row = sheet.getRow(1);
		row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
		String cell = row.getCell(0).toString();// 获取excel内容
		this.projectName=cell;
	}
}
