package com.stylefeng.guns.modular.business.service;

import com.stylefeng.guns.modular.business.entity.Sample;

/**
 * 样本Service
 *
 * @author fengshuonan
 * @Date 2017-08-09 14:40:25
 */
public interface ISampleService {
		/**
		 * 添加样品
		 * @param sample
		 */
		public void add(Sample sample);
		/**
		 * 校验输入是否有空
		 * @param sample
		 * @return
		 */
		public String checkInput(Sample sample);
}
