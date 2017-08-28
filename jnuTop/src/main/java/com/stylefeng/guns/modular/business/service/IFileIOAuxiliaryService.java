package com.stylefeng.guns.modular.business.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.stylefeng.guns.modular.business.entity.Sample;

/**
 *
 * 文件导入导出，数据库访问的辅助服务
 * entityName都要是路径全名
 * @author djb
 * @Date 2017-08-25
 */
public interface IFileIOAuxiliaryService {
	/**
	 * 根据特殊的主键来判断是否可以插入
	 * 
	 * @param entity
	 *            要进入数据库的类
	 * @param entityName
	 *            该类的类名
	 * @return 成功或异常
	 */
	public <T> void InsertCheck(List<T> list, String entityName) throws Exception;

	/**
	 * 通过传入的id批量查询样本信息
	 * 
	 * @author djb
	 * @param Ids
	 *            格式为 xxx,xxx,xxx
	 * @return List<Sample>
	 * @throws Exception
	 */
	public List<Sample> selectListByIds(String Ids) throws Exception;

	/**
	 * 导入附件
	 * 
	 * @author djb
	 * @date 2017年8月25日
	 * @param uploadFile
	 *            要保存的文件
	 * @param entityName
	 *            要调用的类的名字
	 * @param id
	 *            具体要保存路径的类id
	 * @return 成功返回保存的路径名字 失败则异常
	 * @throws IOException
	 * @throws Exception
	 */
	public void saveAttachment(MultipartFile uploadFile, String entityName, String id) throws IOException;

	/**
	 * 导入excel文件
	 * 
	 * @author djb
	 * @date 2017年8月18日
	 * @param uploadFile
	 *            上传的文件
	 * @param entityClass
	 *            要转换成List的bean类
	 * @param mapping
	 *            Excel表格的中文表头对应的英文转换（要与bean类的属性名一致）
	 * @param requiredNum
	 *            必填字段的个数，在map中，请把必填项放在前面，否则不保证录入一定有值（用于校验excel格式）
	 * @param specialfieldName
	 *            需要特殊操作的字段，会调用ExcelSpecialHanding
	 * @param title
	 *            Excel的表头
	 * @return 没返回异常则成功
	 * @throws Exception
	 */
	public <T> void insertExcel(@RequestParam("uploadFile") MultipartFile uploadFile, String entityName, String map,
			String requiredField, String title, String specialfieldName) throws Exception;

	/**
	 * 导入的excel中的中文标题与英文的对应关系
	 * 
	 * @author djb
	 * @date 2017年8月18日
	 * @param 传来的字符串格式应该为：
	 *            "中文名字1,英文名字1,中文名字2,英文名字2,...."
	 * @return 异常或者成功
	 * @throws Exception
	 */
	public HashMap<String, String> stringToMap(String needChange);

	/**
	 * 导出excel文件
	 * 
	 * @author djb
	 * @date 2017年8月18日
	 * @param ids
	 *            需要导出的样本的id。
	 * @param title
	 *            需要导出的表头。
	 * @param needExports
	 *            需要导出的字段。以上均为（xxx,xxxx,xxx格式）
	 * @return 异常或者JSONObject(含路径名filedir和文件名filename)
	 */
	public JSONObject outputExcel(String ids, String title, String needExports) throws Exception;

	/**
	 * 将生成的文件网络传输到客户端
	 * 
	 * @author djb
	 * @date 2017年8月18日
	 * @param
	 * @return 异常或者成功
	 * @throws Exception
	 */
	public void ajaxDownload(HttpServletResponse response, HttpServletRequest request) throws Exception;

	/**
	 * 下载附件
	 * 
	 * @author djb
	 * @date 2017年8月18日
	 * @param entityName
	 *            需要下载附件的类的类名
	 * @param id
	 *            需要下载附件的类的id
	 * @return 异常或者JSONObject(含路径名filedir和文件名filename)
	 */
	public JSONObject downloadAttachment(String entityName,String id) throws Exception;
}
