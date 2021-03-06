package com.stylefeng.guns.modular.business.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.stylefeng.guns.modular.business.dao.SampleUsedDao;
import com.stylefeng.guns.modular.business.entity.SampleUsed;
import com.stylefeng.guns.modular.business.service.ISampleUsedService;

/**
 * 样本领用Service
 *
 * @author
 * @Date 2017-08-24 10:05:50
 */
@Service
public class SampleUsedServiceImpl implements ISampleUsedService {

    @Resource
    private SampleUsedDao sampleUsedDao;
    @Override
    public boolean saveSampleUsed(String sampleIds, SampleUsed sampleUsed) {
        if(sampleIds != null && !"".equals(sampleIds) ) {
            String[] ids = sampleIds.split(",");
            for (int i = 0; i < ids.length; i++) {
                sampleUsed.setSampleId(Integer.parseInt(ids[i]));
                sampleUsedDao.insert(sampleUsed);
            }
        }
      
      return true;


}
    public  List<Map<String, Object>> listHadSampleUsed(Integer sampleId) {
        List<Map<String, Object>> listHadSampleUsed ;
        if (sampleId == -1) {
             listHadSampleUsed = sampleUsedDao.listHadSampleUsed();
        } else {
            listHadSampleUsed = sampleUsedDao.listHadSampleUsedById(sampleId);
        }
        return listHadSampleUsed;
    }
    @Transactional
    public void importExcel(MultipartFile uploadFile) throws Exception {
        InputStream inputStream = uploadFile.getInputStream();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        List<SampleUsed> sampleUsedList = null;
        //工作簿
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        //读取工作表
        XSSFSheet sheet = workbook.getSheetAt(0);
        System.out.println(sheet.getPhysicalNumberOfRows());
        for (int i = 0;i<sheet.getLastRowNum();i++) {
            XSSFRow row = sheet.getRow(i);
            for (int j = 0;j < row .getLastCellNum(); j++) {
                System.out.print(row.getCell(j) + " ");
            }
            System.out.println();
        }
       
        
    }
    
    }
