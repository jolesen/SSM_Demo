package com.stylefeng.guns.common.constant.dictmap;

import com.stylefeng.guns.common.constant.dictmap.base.AbstractDictMap;

/**
 * 用户的字典
 *
 * @author fengshuonan
 * @date 2017-05-06 15:01
 */
public class SampleDict extends AbstractDictMap {

    @Override
    public void init() {
        put("sampleId","样品Id");
        put("detectionItem","检测项目");
        put("labCode","实验室编号");
        put("sampleNumber","样本编号");
        put("subjectName","受检者姓名");
        put("salesman","销售员姓名");
        put("acceptDate","收样日期");
        put("sampleType","样本类型");
        put("transportCondition","运输条件");
        put("expectedReportTime","理论出报告时间");
        put("remark","备注");
        put("extracted","是否已提取");
        put("sampleStorage","样本存储位置");
        put("detectionDuration","检测所需时间");
        put("picture","上传图片");
        put("plasmaSeparation","血浆分离情况");
    }

    @Override
    protected void initBeWrapped() {
    }
}
