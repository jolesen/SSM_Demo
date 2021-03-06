package com.stylefeng.guns.modular.business.service;

import java.io.File;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.stylefeng.guns.modular.business.entity.ExcelModel;

/**
 * excelModelService
 *
 * @author
 * @Date 2017-08-17 13:32:55
 */
public interface IExcelModelService {
		/**
		 * 传入excel模板名称以及上传的文件，并把记录插入到数据库,成功返回1
		 * @param excelModel
		 * @return
		 * @throws IOException 
		 */
		public int addExcelModel(String modelName,MultipartFile  uploadFile) throws IOException;
		/**
		 * 通过实验过程名，和对应的样本编码id，生成excel
		 * @param processName
		 * @param itemId
		 * @return
		 * @throws ClassNotFoundException 
		 * @throws IllegalAccessException 
		 * @throws InstantiationException 
		 */
		public XSSFWorkbook createExcel(String processName,
									String[] detectionItem,String[] labCode,String[] sampleNumber)
				throws ClassNotFoundException, InstantiationException, IllegalAccessException;
}
