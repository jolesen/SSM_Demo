package com.stylefeng.guns.modular.business.service;

import java.io.IOException;

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
}
