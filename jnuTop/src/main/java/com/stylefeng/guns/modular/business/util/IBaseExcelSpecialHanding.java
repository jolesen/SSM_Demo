package com.stylefeng.guns.modular.business.util;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.stereotype.Component;

import com.stylefeng.guns.core.util.SpringContextHolder;

/**
 * Excel导入的字段处理接口，有excel导入的类需要继承此接口
 * 继承的类必须放在com.stylefeng.guns.modular.business.util.excelhanding里面！
 * 继承的类的命名必须是 类名+Handing 例如 SampleHanding
 * 
 * 处理字段时，需要的信息除了要Excel表格内的会调用getParameter使你获得
 * 
 * 其他的请使用SpringContextHolder.getBean（类的路径名）来获取实例，从来再获取想要的东西
 * @author djb
 * 
 */
@Component
public interface IBaseExcelSpecialHanding<T> {
	/**
	 * 处理字段会调用的函数
	 * 
	 * 日期类请返回：yyyy/MM/dd HH:mm:ss 格式
	 * @param fieldName 要处理的字段名
	 * @param entity 对应的实例
	 * @return 将要改变的数据
	 */
       public String SpecialHanding(String fieldName, T entity);
       
   	/**
   	 * 你可能要需要Excel表格别的字段信息来进行处理特殊字段，这个函数满足你
   	 * 这个函数会在SpecialHanding之前被调用
   	 * 
   	 * @param sheet  正在导入的Excel表格的sheet
   	 */
      public void getParameter(XSSFSheet sheet);
}
