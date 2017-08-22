package com.stylefeng.guns.modular.business.service;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.stylefeng.guns.common.constant.tips.Tip;
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
           * @author djb
           * @param Ids 格式为 xxx,xxx,xxx
           * @return List<Sample>
           * @throws Exception 
           */
        public List<Sample> selectListByIds(String Ids) throws Exception;
        /**
         * 导出excel文件
         * @author djb
         * @date 2017年8月18日
         * @param ids 需要导出的样本的id。title 需要导出的表头。needExports 需要导出的字段。以上均为（xxx,xxxx,xxx格式） 
         * @return 异常或者JSONObject(含路径名filedir和文件名filename)
         */
        public JSONObject outputExcel(String ids,String title,String needExports,String exportURL,String exportName) throws Exception;
        /**
         * 导入excel文件
         * @author djb
         * @date 2017年8月18日
         * @param uploadFile 上传的文件
         * @return 没返回异常则成功
         * @throws Exception
         */
        public void insertExcel( @RequestParam("uploadFile") MultipartFile  uploadFile) throws Exception;
        /**
         * 将生成的文件网络传输到客户端
         * @author djb
         * @date 2017年8月18日
         * @param 
         * @return 异常或者成功
         * @throws Exception
         */
        public void ajaxDownload(HttpServletResponse response,HttpServletRequest request) throws Exception;
}
