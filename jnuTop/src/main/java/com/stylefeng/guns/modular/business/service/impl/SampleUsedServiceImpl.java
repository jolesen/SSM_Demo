package com.stylefeng.guns.modular.business.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

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
    public boolean insertSampleUsed(SampleUsed sampleUsed) {
        Integer i = sampleUsedDao.insert(sampleUsed);
        if(i > 0) {
            return true;
        } else {
            return false;
        }
       
    }


}
