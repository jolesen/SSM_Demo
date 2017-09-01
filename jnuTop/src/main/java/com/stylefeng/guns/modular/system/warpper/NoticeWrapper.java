package com.stylefeng.guns.modular.system.warpper;

import com.stylefeng.guns.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.common.warpper.BaseControllerWarpper;

import java.util.Map;

/**
 * 部门列表的包装
 *
 * @author zmj
 * @since 2017年8月26日下午3:23:03
 */
public class NoticeWrapper extends BaseControllerWarpper {

    public NoticeWrapper(Object list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
        Integer creater = (Integer) map.get("creater");
        Integer status =(Integer) map.get("readed");
        map.put("createrName", ConstantFactory.me().getUserNameById(creater));
        map.put("readed", ConstantFactory.me().getNoticeStatusName(status));
    }

}
