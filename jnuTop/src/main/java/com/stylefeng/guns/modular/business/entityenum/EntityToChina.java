package com.stylefeng.guns.modular.business.entityenum;

import java.util.HashMap;


/**
 * 获取实体类对应的模板的中文名
 * 
 * @author zhuangziwei
 *
 */
public class EntityToChina {
		public static HashMap<String, String>map;
		static{
			map=new HashMap<String, String>();
			map.put("ProcessDnaExtract","dna提取模板");
		}
		public static String getByEntityName(String name){
			return map.get(name);
		}
}
