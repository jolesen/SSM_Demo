package com.stylefeng.guns.modular.business.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.stylefeng.guns.modular.business.entity.Detection;

/**
 * 检测结果Service
 *
 * @author ZhuangJieXian
 * @Date 2017-08-23 15:52:04
 */
public interface IDetectionService {
	/**
	 * 通过id查询Detection对象
	 * @param id
	 * @return
	 */
	Detection findDetectionById(Integer id);
	
	/**
	 * 通过id数组批量删除
	 * @param detectionId
	 */
	void deleteById(Integer detectionId);
	/**
	 * 导入Excel文件
	 * @param uploadFile
	 * @throws Exception
	 */
	void importExcel(MultipartFile uploadFile)throws Exception;
}
