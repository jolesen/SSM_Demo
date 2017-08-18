
package com.stylefeng.guns.modular.business.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.modular.business.entity.ExcelModel;
import com.stylefeng.guns.modular.business.entity.Sample;

/**
 * excelModelDao
 *
 * @author zhuangziwei
 * @Date 2017-08-17 13:32:55
 */
public interface ExcelModelDao extends BaseMapper<ExcelModel> {
		/**
		 * 统计相同name的excel模板的条数
		 * @param modelName
		 * @return
		 */
		public int countModel(String modelName);

}
