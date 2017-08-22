package com.stylefeng.guns.modular.business.entityenum;

import java.util.HashMap;
/**
 * 用于映射数据库英文字段与excel中文字段
 * @author zhuangziwei
 *
 */
public class ProcessDnaExtractMap implements IEntityMap {
	public static HashMap<String, String> mapping;
	static{
		mapping = new HashMap<String, String>();
		mapping.put("detectionItem", "检测项目");
		mapping.put("labCode", "实验室编码");
		mapping.put("sampleNumber", "样本编号");
		mapping.put("extractMan", "提取人");
		mapping.put("extractTime", "提取时间");
		mapping.put("nanodrop", "NANODROP（ng/ul）");
		mapping.put("qubit", "QUBIT (ng/ul）");
		mapping.put("dnaLocation", "dna储存位置");
		mapping.put("remark", "备注");
		mapping.put("usingMan1", "使用人1");
		mapping.put("usingTime1", "使用时间1");
		mapping.put("returnTime1", "归还时间1");
		mapping.put("usingMan2", "使用人2");
		mapping.put("usingTime2", "使用时间2");
		mapping.put("returnTime2", "归还时间2");
	}
	@Override
	public String getChinaName(String dbTableCol) {
	
		return mapping.get(dbTableCol);
	}
}
