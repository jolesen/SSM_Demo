package com.stylefeng.guns.core.record.factory;

import com.stylefeng.guns.common.constant.state.BizType;
import com.stylefeng.guns.common.persistence.dao.BizRecordMapper;
import com.stylefeng.guns.common.persistence.model.BizRecord;
import com.stylefeng.guns.core.db.Db;
import com.stylefeng.guns.core.log.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

/**
 * @author wuliepeng
 * @Description
 * @since 2017/8/21 11:29
 */
public class RecordTaskFactory {

    private static Logger logger = LoggerFactory.getLogger(LogManager.class);
    private static BizRecordMapper bizRecordMapper = Db.getMapper(BizRecordMapper.class);

    public static TimerTask bizRecord(final String userAccount, final BizType bizType, final String bizId, final String message) {
        return new TimerTask() {
            @Override
            public void run() {
                BizRecord bizRecord = RecordFactory.createBizRecord(userAccount, bizType, bizId, message);
                try {
                    bizRecordMapper.insert(bizRecord);
                } catch (Exception e) {
                    logger.error("创建业务记录异常!", e);
                }
            }
        };
    }

}
