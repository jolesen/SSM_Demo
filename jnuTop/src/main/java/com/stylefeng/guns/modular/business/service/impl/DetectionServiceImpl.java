package com.stylefeng.guns.modular.business.service.impl;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.stylefeng.guns.common.exception.NonullException;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.modular.business.dao.DetectionDao;
import com.stylefeng.guns.modular.business.entity.Detection;
import com.stylefeng.guns.modular.business.service.IDetectionService;
import com.stylefeng.guns.modular.business.service.IExcelDBCheckService;

/**
 * 检测结果Service
 *
 * @author ZhuangJieXian
 * @Date 2017-08-23 15:52:04
 */
@Service
public class DetectionServiceImpl implements IDetectionService {
	@Resource
	private DetectionDao detectionDao;
	@Resource
	private IExcelDBCheckService excelDBCheck;
	@Resource
	private IDetectionService detectionService;
	/**
	 * 通过id查询Detection对象
	 * @param id
	 * @return
	 */
	@Override
	public Detection findDetectionById(Integer id) {
		Detection detection = detectionDao.findDetectionById(id);
		return detection;
	}

	/**
	 * 通过detection数组批量删除
	 */
	@Override
	public void deleteById(Integer detectionId) {
		detectionDao.deleteById(detectionId);
    

	}
	
	
	/**
	 * 导入Excel文件
	 * @param uploadFile
	 * @throws Exception
	 */
	@Transactional
	@Override
	public void importExcel(MultipartFile uploadFile) throws Exception {
		InputStream inputStream = uploadFile.getInputStream();
		SimpleDateFormat toDate = new SimpleDateFormat("yyyy-MM-dd");
		List<Detection> detectionList = null;
	
		//读取工作簿
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		//读取工作表
		XSSFSheet sheet = workbook.getSheetAt(0);
		//读取行
		System.out.println(sheet.getPhysicalNumberOfRows());
		if(sheet.getPhysicalNumberOfRows()>1){
			Detection detection = null;
			detectionList = new ArrayList<Detection>();
			for(int k=1;k<sheet.getPhysicalNumberOfRows();k++){
				
				//读取单元格
				Row row = sheet.getRow(k);
				if(row==null){
					continue;
				}
				detection = new Detection();
				String labCode = null;
				//判断是否重复
//				Integer sampleId = detectionDao.selectSampleIdByLabCode(labCode);
//				if(sampleId!=null){
//					//重复则报错回滚
//					throw new RepeatException(k+1);
//				}
//				
//				//不重复则插入sampleId继续执行
//				detection.setSampleId(sampleId.toString());
			
				detection.setSampleId("3");
				detection.setRisk("无");
				//检测结果
				Cell result = row.getCell(10);
				if(result==null)
					throw new NonullException(k,10);
				detection.setResult(result.getStringCellValue());
				

				//突变点
				Cell site = row.getCell(11);
				if(site!=null){
					detection.setMutationSite(site.getStringCellValue());
				}

				//检测者
				Integer userId = ShiroKit.getUser().getId();
				detection.setDetectorId(userId.toString());
				
				//检测时间
				
				Cell date = row.getCell(12);
				boolean flag = HSSFDateUtil.isCellDateFormatted(date);
				//为日期格式
				if(flag){
					 Date d = date.getDateCellValue();
				     String dateFormat = DateFormatUtils.format(d, "yyyy-MM-dd");
				     Date parseDate = toDate.parse(dateFormat);
				     detection.setDetectionDate(parseDate);
				}else{
					double value = date.getNumericCellValue();
			        DecimalFormat df = new DecimalFormat("0");
			        String dateFormat = df.format(value);
			        Date parseDate = toDate.parse(dateFormat);
				    detection.setDetectionDate(parseDate);
				}

				//备注
				Cell remarks = row.getCell(14);
				if(remarks!=null){
					detection.setRemarks(remarks.getStringCellValue());
				}

				detectionList.add(detection);
			}

			if(detectionList != null){
				detectionDao.insertByBatch(detectionList);
			}
		}
		
	}





}
