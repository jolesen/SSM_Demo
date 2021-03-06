package com.stylefeng.guns.modular.business.service.impl;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.stylefeng.guns.common.constant.tips.ErrorTip;
import com.stylefeng.guns.common.constant.tips.SuccessTip;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.modular.business.dao.DetectionDao;
import com.stylefeng.guns.modular.business.entity.Detection;
import com.stylefeng.guns.modular.business.entity.Sample;
import com.stylefeng.guns.modular.business.entity.SampleExtractedDna;
import com.stylefeng.guns.modular.business.entity.model.DnaExcel;
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
	 * 通过detectionIds数组批量删除
	 */
	@Override
	public void deleteById(String[] detectionIds) {
		for(String detectionId : detectionIds){
			detectionDao.deleteById(Integer.parseInt(detectionId));
		}
	}
	
	

	/**
	 * 
	 * @param uploadFile  上传文件
	 * @param importFieldName   需要导入的excel表中对应的列名
	 * @param importFieldMethod 列名对应的set方法名
	 * @return 返回一个object对象，存储错误或者成功信息
	 * @throws Exception
	 * @author ZhuangJieXian
	 */
	@Override
	public Object importExcel(MultipartFile uploadFile, String importFieldName,
			String importFieldMethod) throws Exception {
		//将需要上传的列切割成一个数组
		String[] FieldNames = importFieldName.split(",");
		//map集合用来保存需要导入的列名的index和set方法的对应关系
		Map<Integer,String> indexMap = new HashedMap<Integer, String>();
		//将需要导入的列名对应的set方法切割成一个数组
		String[] FieldMethods = importFieldMethod.split(",");
		InputStream inputStream = uploadFile.getInputStream();
		
		
		//读取工作簿
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		//读取工作表
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow firstRow = sheet.getRow(0);
		//获取所有的表头列名
		String[] excelFieldNames = new String[firstRow.getPhysicalNumberOfCells()];
		for (int i = 0; i < firstRow.getPhysicalNumberOfCells(); i++) {
			excelFieldNames[i] = firstRow.getCell(i).toString().trim();
			System.out.println("第" + i + "个excelFieldNames的值：" +
			excelFieldNames[i]);
			//获取表头对应的列号，并将列号和对应的set方法通过map集合的key-value存储起来
			for(int j=0;j<FieldNames.length;j++){
				if(excelFieldNames[i].equals(FieldNames[j])){
					indexMap.put(i, FieldMethods[j]);
				}
			}
		}
		
		
		List<Detection> detectionList = new ArrayList<Detection>();
	
		Detection detection = null;
		//遍历excel表格的每一行数据
		for(int r=1;r<sheet.getPhysicalNumberOfRows();r++){
			Row row = sheet.getRow(r);
			detection = new Detection();
			Class clz = detection.getClass();
			
			//遍历当前行的每一列
			for(int c=0;c<row.getPhysicalNumberOfCells();c++){

				if(indexMap.containsKey(c)){
					//当前列为日期时进行特殊处理
					if(indexMap.get(c).equals("setDetectionDate")){
						SimpleDateFormat toDate = new SimpleDateFormat("yyyy-MM-dd");
						Cell date = row.getCell(c);
				        Date d = date.getDateCellValue();
			            String dateFormat = DateFormatUtils.format(d, "yyyy-MM-dd");
			            Date parseDate = toDate.parse(dateFormat);
			            detection.setDetectionDate(parseDate);
			        //当前列为普通列时进行此处理
					}else{
						row.getCell(c).setCellType(Cell.CELL_TYPE_STRING);
						String value = row.getCell(c).getStringCellValue();
						Method m = clz.getDeclaredMethod(indexMap.get(c),String.class);
						m.invoke(detection,value);
					}
				}
			}

			//特殊字段处理
			//操作者
			Integer userId = ShiroKit.getUser().getId();
			detection.setDetectorId(userId.toString());
			
			//通过实验室编号和样本编号获取样本id

			System.out.println(detection.getLabCode());
			Integer sampleId = detectionDao.selectSampleIdByLabCodeAndSampleNum(detection.getLabCode(), detection.getSampleNumber());
			System.out.println(sampleId);
			if(sampleId == null){
				return new ErrorTip(501, "第"+(r+1)+"行对应的样本不存在");
			}else{
				detection.setSampleId(sampleId.toString());
				Detection dete = detectionDao.selectBySampleId(sampleId.toString());
				
				if(dete != null){
					return new ErrorTip(500, "第"+(r+1)+"行数据重复");
				}
			}
			
						

			detectionList.add(detection);

		}
		//批量插入数据库
		if(detectionList != null){
			detectionDao.insertByBatch(detectionList);
		}
		
		return new SuccessTip();
		
	}
	
	/**
	 * 获取要导出的dnaExcel对象集合
	 * @param detectionId 检测结果id
	 * @return
	 * @author ZhuangJieXian
	 */
	@Override
	public List<DnaExcel> dnaDetectionId(String[] detectionId) {
		List<DnaExcel> dnaExcelLists = new ArrayList<DnaExcel>();
		List<String> detectionList = Arrays.asList(detectionId);
		List<String> sampleList = detectionDao.selectSampleIdList(detectionList);
		List<SampleExtractedDna> extractedDanList = detectionDao.
				selectSampleExtractedDnaList(sampleList);
		DnaExcel dExcel = null;
		Sample sample = null;
		Detection detection = null;
		for(SampleExtractedDna sedDna : extractedDanList){
			dExcel = new DnaExcel();
			dExcel.setNanodrop(sedDna.getNanodrop());
			dExcel.setOd260280(sedDna.getOd260280());
			dExcel.setOd260230(sedDna.getOd260230());
			dExcel.setExtractDate(sedDna.getExtractDate());
			dExcel.setExtractPeopleName(sedDna.getExtractPeopleName());
			
			sample = detectionDao.selectSampleById(sedDna.getSampleId());
			dExcel.setDetectionItem(sample.getDetectionItem());
			dExcel.setLabCode(sample.getLabCode());
			dExcel.setSampleNumber(sample.getSampleNumber());
			dExcel.setSubjectName(sample.getSubjectName());
			dExcel.setSampleType(sample.getSampleType());
			
			detection = detectionDao.selectBySampleId(sedDna.getSampleId().toString());
			dExcel.setResult(detection.getResult());
			dExcel.setMutationSite(detection.getMutationSite());
			dExcel.setDetectionDate(detection.getDetectionDate());
			dExcel.setDetectorName(detection.getDetectorName());
			dExcel.setRemarks(detection.getRemarks());
			
			dnaExcelLists.add(dExcel);
			
		}
		System.out.println(dnaExcelLists);
		return dnaExcelLists;
	}


	/**
	 * 导出dna检测结果Excel
	 * @param dnaExcelList
	 * @param outputStream
	 * @author ZhuangJieXian
	 */
	@Override
	public void exportExcel(List<DnaExcel> dnaExcelList,
			ServletOutputStream outputStream) {
		try {
			//创建工作簿
			XSSFWorkbook workbook = new XSSFWorkbook();
			
			//列标题样式
			//XSSFCellStyle style2 = createCellStyle(workbook, (short)13);
			
			//创建工作表
			XSSFSheet sheet = workbook.createSheet("DNA检测列表");

			//设置默认列宽
			sheet.setDefaultColumnWidth(12);
			
			//3、创建行
			XSSFRow row2 = sheet.createRow(0);
			String[] titles = {"项目名","实验编号", "取样编号", "患者姓名", "样本类型","ND","OD260/280","OD260/230","提取时间","提取者","检测结果","突变位点","检测时间","检测者","备注"};
			for(int i = 0; i < titles.length; i++){
				XSSFCell cell2 = row2.createCell(i);
				//加载单元格样式
//				cell2.setCellStyle(style2);
				cell2.setCellValue(titles[i]);
			}
			
			//操作单元格；将信息写入excel
			if(dnaExcelList != null){
				for(int j = 0; j < dnaExcelList.size(); j++){
					XSSFRow row = sheet.createRow(j+1);
					XSSFCell cell = row.createCell(0);
					cell.setCellValue(dnaExcelList.get(j).getDetectionItem());
					
					cell = row.createCell(1);
					cell.setCellValue(dnaExcelList.get(j).getLabCode());
					
					cell = row.createCell(2);
					cell.setCellValue(dnaExcelList.get(j).getSampleNumber());
					
					cell = row.createCell(3);
					cell.setCellValue(dnaExcelList.get(j).getSubjectName());
					
					cell = row.createCell(4);
					cell.setCellValue(dnaExcelList.get(j).getSampleType());
					
					cell = row.createCell(5);
					cell.setCellValue(dnaExcelList.get(j).getNanodrop());
					
					cell = row.createCell(6);
					cell.setCellValue(dnaExcelList.get(j).getOd260280());
					
					cell = row.createCell(7);
					cell.setCellValue(dnaExcelList.get(j).getOd260230());
					
					cell = row.createCell(8);
					cell.setCellValue(dnaExcelList.get(j).getExtractDate());
					
					cell = row.createCell(9);
					cell.setCellValue(dnaExcelList.get(j).getExtractPeopleName());
					
					cell = row.createCell(10);
					cell.setCellValue(dnaExcelList.get(j).getResult());
					
					cell = row.createCell(11);
					cell.setCellValue(dnaExcelList.get(j).getMutationSite());
					
					cell = row.createCell(12);
					cell.setCellValue(dnaExcelList.get(j).getDetectionDate());
					
					cell = row.createCell(13);
					cell.setCellValue(dnaExcelList.get(j).getDetectorName());
					
					cell = row.createCell(14);
					cell.setCellValue(dnaExcelList.get(j).getRemarks());
					
				}
			}
			//5、输出
			workbook.write(outputStream);
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	/**
	 * 创建单元格样式
	 * @param workbook 工作簿
	 * @param fontSize 字体大小
	 * @return 单元格样式
	 * @author ZhuangJieXian
	 */
	private static XSSFCellStyle createCellStyle(XSSFWorkbook workbook, short fontSize) {
		XSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
		//创建字体
		XSSFFont font = workbook.createFont();
//		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗字体
		font.setFontHeightInPoints(fontSize);
		//加载字体
		style.setFont(font);
		return style;
	}

}
