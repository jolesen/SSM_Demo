package com.stylefeng.guns.core.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookType;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.stylefeng.guns.common.exception.NonullException;
import com.stylefeng.guns.modular.business.entity.Sample;



public class ExcelReader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public static List<Sample> read(InputStream file) throws NonullException, InvalidFormatException, IOException, ParseException, org.apache.poi.openxml4j.exceptions.InvalidFormatException
	{
		List<Sample> list = new ArrayList<Sample>();
		
	            XSSFWorkbook xwb = new XSSFWorkbook(file); 
	            // 读取第一章表格内容 
	            XSSFSheet sheet = xwb.getSheetAt(0); 
	            // 定义 row、cell 
	            XSSFRow row; 
	            String cell; 
	            
	            SimpleDateFormat toDate=new SimpleDateFormat("yyyy/MM/dd");
	            // 循环输出表格中的内容 
	            for (int i = sheet.getFirstRowNum()+1; i < sheet.getPhysicalNumberOfRows(); i++) { 
	            	Sample sample = new Sample();
	                row = sheet.getRow(i); 
	                cell = row.getCell(0).toString();//检测项目
	                if(cell==null||cell.equals("")){
	                	throw new NonullException(i, 0);
	                }
	                sample.setDetectionItem(cell);
	                
	                cell = row.getCell(1).toString();//实验室编码
	                if(cell==null||cell.equals("")){
	                	throw new NonullException(i, 1);
	                }
	                sample.setLabCode(cell);
	                
	                cell = row.getCell(2).toString();//样本编码
	                if(cell==null||cell.equals("")){
	                	throw new NonullException(i, 2);
	                }
	                sample.setSampleNumber(cell);
	                
	                cell = row.getCell(3).toString();//受检者姓名
	                if(cell==null||cell.equals("")){
	                	throw new NonullException(i, 3);
	                }
	                sample.setSubjectName(cell);
	                
	                cell = row.getCell(4).toString();//销售
	                if(cell==null||cell.equals("")){
	                	throw new NonullException(i, 4);
	                }
	                sample.setSalesman(cell);
	                
	                cell = row.getCell(5).toString();//收样日期
	                if(cell==null||cell.equals("")){
	                	throw new NonullException(i, 5);
	                }
	                sample.setAcceptDate(toDate.parse(cell));
	                
	                cell = row.getCell(6).toString();//样本类型
	                if(cell==null||cell.equals("")){
	                	throw new NonullException(i, 6);
	                }
	                sample.setSampleType(cell);
	                
	                cell = row.getCell(7).toString();//运输条件
	                if(cell==null||cell.equals("")){
	                	throw new NonullException(i, 7);
	                }
	                sample.setTransportCondition(cell);
	                
	                cell = row.getCell(8).toString();//血液到样温度
	                if(cell!=null&&!cell.equals(""))
	                sample.setBloodTemperature(new Float(cell).floatValue());
	                
	                cell = row.getCell(9).toString();//理论出报告时间
	                if(cell==null||cell.equals("")){
	                	throw new NonullException(i, 9);
	                }
	                sample.setExpectedReportTime(toDate.parse(cell));
	                
	                cell = row.getCell(10).toString();//备注
	                if(cell==null||cell.equals("")){
	                	throw new NonullException(i, 10);
	                }
	                sample.setRemark(cell);
	                
	                cell = row.getCell(11).toString();//样本来源
	            
	                sample.setSampleSource(cell);
	                
	                cell = row.getCell(12).toString();//是否已提取
	                sample.setExtracted(cell);
	                
	                cell = row.getCell(13).toString();//样本储存位置
	                if(cell==null||cell.equals("")){
	                	throw new NonullException(i, 13);
	                }
	                
	                cell = row.getCell(14).toString();//报告周期
	                if(cell!=null&&!cell.equals(""))
	                sample.setDetectionDuration(new Integer(cell));
	                list.add(sample);
	            }
	          
		 return list;
	}
	
}
