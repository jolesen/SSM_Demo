package com.stylefeng.guns.core.record.factory;

import com.stylefeng.guns.common.constant.state.BizType;
import com.stylefeng.guns.common.persistence.model.BizRecord;

import java.util.Date;

/**
 * @author wuliepeng
 * @Description
 * @since 2017/8/21 11:29
 */
public class RecordFactory {

    public static BizRecord createBizRecord(String userAccount, BizType bizType, String bizId, String message) {
        BizRecord bizRecord = new BizRecord();
        bizRecord.setUserAccount(userAccount);
        bizRecord.setCreatetime(new Date());
        bizRecord.setBizType(bizType.toString());
        bizRecord.setBizId(bizId);
        bizRecord.setMessage(message);
        return bizRecord;
    }

}
