package com.stylefeng.guns.modular.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.druid.sql.ast.statement.SQLIfStatement.Else;
import com.alibaba.druid.sql.ast.statement.SQLIfStatement.ElseIf;
import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.stylefeng.guns.common.exception.BussinessException;
import com.stylefeng.guns.common.exception.NonullException;
import com.stylefeng.guns.common.exception.RepeatException;
import com.stylefeng.guns.modular.business.dao.SampleDao;
import com.stylefeng.guns.modular.business.entity.Sample;
import com.stylefeng.guns.modular.business.service.ISampleService;


/**
 * 样本Service
 *
 * @author fengshuonan
 * @Date 2017-08-09 14:40:25
 */
@Service
public class SampleServiceImpl implements ISampleService {
	
	@Resource
    private SampleDao sampleDao;

	@Override
	public void add(Sample sample) {
		
		try {
			sampleDao.insert(sample);
		} catch (Exception e) {
			throw new BussinessException(BizExceptionEnum.SERVER_ERROR);
		}
		
	}

	@Override
	public String checkInput(Sample sample){
		if(sample.getSampleStorage() == null){
			return "sampleStorage";
		}
		else if (sample.getDetectionItem() == null) {
			return "detectionItem";
		}else if (sample.getLabCode() == null) {
			return "labCode";
		}else if(sample.getSampleNumber() == null){
			return "sampleNumber";
		}else if (sample.getSubjectName() == null) {
			return "subjectName";
		}
		return "success";
	}

    public Sample findSampleById(Integer sample_id) {
        return sampleDao.selectById(sample_id);
    }
    
    public String inputSamples(List<Sample> list, int start, int end) throws Exception {
        System.out.println("准备插入数据库 start:"+start+" end:"+end);
        // TODO Auto-generated method stub
        int i=0;

    try{
            for(i = start; i<=end ;i++){
                sampleDao.insert(list.get(i));
            }
        }catch (Exception e) {
            // TODO: handle exception
            System.out.println("第"+(i+1)+"条记录数据重复，"+e.getMessage());
            throw new RepeatException(i);
        }
        return "SUCCEED";
    }

    
    public void deleteById(String sampleId) {
        //将传过来的id串拆分成数组,再循环删除 即 传过来的可能是 "1,2,3,4,5"
        if(sampleId != null && !"".equals(sampleId)){
            String[] sampleIds = sampleId.split(",");
                try {
                    for (String id : sampleIds) {
                        sampleDao.deleteById(Integer.parseInt(id));
                      
                    }
                } catch (Exception e) {
                  throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
               
            }
        }

    }
    
    public int updateSample(Sample sample) {
        int i = sampleDao.updateById(sample);
       
        return i;
        
    }

    public void setSampleDao(SampleDao sampleDao) {
        this.sampleDao = sampleDao;
    }


	public List<Sample> selectListByIds(String Ids) throws Exception {
		List<Sample> list=new ArrayList<Sample>();
		if(Ids != null && !"".equals(Ids)){
            String[] sampleIds = Ids.split(",");
            for (String id : sampleIds) {
            	Sample sample=sampleDao.selectById(Integer.parseInt(id));
            	if(sample==null) throw new Exception("id为："+id+"为空！");
            	list.add(sample);
            }
        }
		return list;
	}
    

}
