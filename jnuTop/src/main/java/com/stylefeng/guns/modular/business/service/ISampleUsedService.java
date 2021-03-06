package com.stylefeng.guns.modular.business.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.stylefeng.guns.modular.business.entity.SampleUsed;

/**
 * 样本领用Service
 *
 * @author 罗健金
 * @Date 2017-08-24 10:05:50
 */
public interface ISampleUsedService {

    /**
     * 添加样本领用记录
     * @author 罗健金
     * @date 2017年8月30日
     * @param sampleIds 领用样本的id数组
     * @param sampleUsed 样本领用的信息
     * @return
     */
    boolean saveSampleUsed(String sampleIds, SampleUsed sampleUsed);

    /**
     * 查看样本领用记录
     * @author 罗健金
     * @param id 
     * @date 2017年8月30日
     * @return
     */
    List<Map<String, Object>> listHadSampleUsed(Integer id);

    /**
     * excel导入处理方法
     * @author 罗健金
     * @date 2017年8月31日
     * @param uploadFile
     * @throws Exception 
     */
    void importExcel(MultipartFile uploadFile) throws Exception;

}
