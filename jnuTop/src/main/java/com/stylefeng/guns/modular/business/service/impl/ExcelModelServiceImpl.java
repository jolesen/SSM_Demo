package com.stylefeng.guns.modular.business.service.impl;




import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.javassist.expr.NewArray;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataConsolidateFunction;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.stylefeng.guns.modular.business.dao.ExcelModelDao;
import com.stylefeng.guns.modular.business.entity.ExcelModel;
import com.stylefeng.guns.modular.business.entityenum.IEntityMap;
import com.stylefeng.guns.modular.business.service.IExcelModelService;


/**
 * excelModelService
 *
 * @author zhuangziwei 
 * @Date 2017-08-17 13:32:55
 */
@Service
public class ExcelModelServiceImpl implements IExcelModelService {
	
	@Resource
    private ExcelModelDao excelModelDao;
	@Override
	public int addExcelModel(String modelName, MultipartFile uploadFile) throws IOException {
		// TODO Auto-generated method stub
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String diskUrl ="d:\\Excelmodel\\";
		String nowDateFormat = format.format(new Date());
		String modelUrl = diskUrl+nowDateFormat+modelName+".xlsx";
		File file = new  File(modelUrl);
		System.out.println(modelUrl);
		if(!file.exists()){
	 		//新的模板
	 		file.createNewFile();
	 		uploadFile.transferTo(file);
	 	}else {
	 		uploadFile.transferTo(file);
		}
		ExcelModel newModel = new ExcelModel();
		newModel.setModelName(modelName);
		newModel.setModelUrl(modelUrl);
		if(excelModelDao.countModel(modelName)==0){
			//未导入过的模板,插入到数据库
			 return excelModelDao.insert(newModel);
		}else{
			//导入过的模板，替换成新的模板
			 return excelModelDao.updateById(newModel);
		}
		
	}
	
	@Override
	public XSSFWorkbook createExcel(String processName, String[] detectionItem,String[] labCode,String[] sampleNumber) 
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		// 对应的实体类
		Class entityObjectClass = Class.forName("com.stylefeng.guns.modular.business.entity."+processName);
		//对应实体类的映射map
		Class entityObjectMapClass = Class.forName("com.stylefeng.guns.modular.business.entityenum."+processName+"Map");
		//获取该实体类，并获取属性字段
		IEntityMap mapping = (IEntityMap) entityObjectMapClass.newInstance();	
		//这个类的所有字段
		Field[] entityFields = entityObjectClass.getDeclaredFields();
		
		//获取表头 注意：实体类第一个属性是序列号ID，第二个是独有的id，故从第三个属性开始获取
		ArrayList<String> header = new ArrayList<String>(entityFields.length - 2);
		for(int i=2;i<entityFields.length;i++){
			entityFields[i].setAccessible(true);  
			//System.out.println(entityFields[i].getName()+"    "+mapping.getChinaName(entityFields[i].getName()));
			//获取映射后的中文名
			header.add(mapping.getChinaName(entityFields[i].getName()));
		}
		
		//生成excel
		 XSSFWorkbook wb = new XSSFWorkbook();
	    // 创建第一个sheet（页），创建表头
		 XSSFSheet sheet = wb.createSheet(processName);
		 Row row = sheet.createRow((short) 0);
		 for(int i=0;i<entityFields.length - 2;i++){
		        Cell cell = row.createCell(i);
		        cell.setCellValue(header.get(i));
		    }
		 //写表
		 int excelLength;
		 if(detectionItem!=null){
		 excelLength= detectionItem.length;}
		 else{
			 excelLength = 0;
		 }
		 for(int i=0 ;i<excelLength;i++){
			 row = sheet.createRow((short) i+1);
			Cell cell = row.createCell(0);
			cell.setCellValue(detectionItem[i]);
			cell = row.createCell(1);
			cell.setCellValue(labCode[i]);
			cell = row.createCell(2);
			cell.setCellValue(sampleNumber[i]);
		 }
		return wb;
		
	}
	public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException{
		ExcelModelServiceImpl  testExcelModelServiceImpl = new ExcelModelServiceImpl();
		
	}
}
