package com.stylefeng.guns.modular.business.entityenum;

import java.util.HashMap;
/**
 * 
 * @author zhuangziwei
 *	
 */
public interface IEntityMap {
	/**
	 * 通过实体类的属性名获取对应的excel中文名
	 * @param entityCol
	 * @return
	 */
	public String  getChinaName(String entityCol);
}
