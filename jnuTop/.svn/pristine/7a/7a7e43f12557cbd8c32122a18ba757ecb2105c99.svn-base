package com.stylefeng.guns.modular.system.service.impl;

import com.stylefeng.guns.common.persistence.dao.BizRecordMapper;
import com.stylefeng.guns.modular.system.dao.RecordDao;
import com.stylefeng.guns.modular.system.service.BizRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class BizRecordServiceImpl implements BizRecordService {

    @Resource
    private BizRecordMapper bizRecordMapper;

    @Resource
    private RecordDao recordDao;

    @Override
    public List<Map<String, Object>> list(String bizType, String bizId) {
        List<Map<String, Object>> records = recordDao.listBizRecords(bizType, bizId);
        return records;
    }
}
