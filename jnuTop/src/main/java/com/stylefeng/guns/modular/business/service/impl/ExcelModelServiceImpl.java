package com.stylefeng.guns.modular.business.service.impl;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.ibatis.javassist.expr.NewArray;
import org.apache.poi.ss.usermodel.DataConsolidateFunction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.stylefeng.guns.modular.business.dao.ExcelModelDao;
import com.stylefeng.guns.modular.business.entity.ExcelModel;
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
	public static void main(String[] args) throws IOException{
		ExcelModelServiceImpl  testExcelModelServiceImpl = new ExcelModelServiceImpl();
		testExcelModelServiceImpl.addExcelModel("hello", null);
	}

}
