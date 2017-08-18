package com.stylefeng.guns.modular.business.service;

import java.io.Serializable;
import java.util.List;

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
         int updateSample(Sample sample);
         /**
          * 通过excel导入时调用的插入service
          * @param list
          * @param start
          * @param end
          * @return
          * @throws Exception
          */
         public String inputSamples(List<Sample> list , int start , int end) throws Exception;
         /**
          * 通过传入的id批量删除样本信息
          * @param sampleId
          */
        public void deleteById(String sampleId);
          /**
           * 通过传入的id批量查询样本信息
           * @param Ids 格式为 xxx,xxx,xxx
           * @return
         * @throws Exception 
           */
        public List<Sample> selectListByIds(String Ids) throws Exception;
        
}
