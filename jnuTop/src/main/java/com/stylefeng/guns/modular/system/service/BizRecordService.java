package com.stylefeng.guns.modular.system.service;

import com.stylefeng.guns.common.persistence.model.BizRecord;

import java.util.List;
import java.util.Map;

public interface BizRecordService {

    List<Map<String, Object>> list(String bizType, String bizId);

}
