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
		/**
		 * 通过id查询样本信息
		 * @param sample_id
		 * @return
		 */
         Sample findSampleById(Integer sample_id);
         /**
          * 通过id更新样本信息
          * @param sample
          */
         void updateSample(Sample sample);
}
