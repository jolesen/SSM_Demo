package com.stylefeng.guns.modular.system.warpper;

import com.stylefeng.guns.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.common.warpper.BaseControllerWarpper;

import java.util.List;
import java.util.Map;

/**
 * @author wuliepeng
 * @Description
 * @since 2017/8/22 9:04
 */
public class RecordWrapper extends BaseControllerWarpper {

    public RecordWrapper(List<Map<String,Object>> list) {
        super(list);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        map.put("userAccount", ConstantFactory.me().getUserAccountById((Integer) map.get("userId")));
    }
}
