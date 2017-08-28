package com.stylefeng.guns.modular.business.util;


import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import com.stylefeng.guns.common.exception.NonullException;
import com.stylefeng.guns.modular.business.entity.Sample;

/**
 * Excel导入工具
 * 
 * @author djb
 * @version v1.0
 * @param <T>
 *            应用泛型，代表任意一个符合javabean风格的类
 *            注意这里为了简单起见，boolean型的属性xxx的get器方式为getXxx(),而不是isXxx()
 *            byte[]表jpg格式的图片数据
 */
public class ImportExcel {

	/**
	 * 这是一个通用的方法，利用了JAVA的反射机制，根据excel文件内容创建对象 时间数据默认为"yyy-MM-dd"
	 * 注意该excel表格第一列必须是对应project的唯一字段
	 * 
	 * @param inputStream
	 *            Excel表格的二进制文件流，注意要有表头
	 * @param entityClass
	 *            要转换成List的bean类
	 * @param mapping
	 *            Excel表格的中文表头对应的英文转换（要与bean类的属性名一致）
	 * @param requiredField
	 *            必填字段（用于校验excel格式）
	 * @param specialfieldName
	 *            需要特殊操作的字段，会调用ExcelSpecialHanding,如果Excel有字段需要根据表格内的字段值生成，务必要该字段要所需字段的后面！！！例如xxx1,xxx2
	 *            (2需要1)
	 * @param handing
	 *            ExcelSpecialHanding的实例，调用这方法前，应该已经初始化了该实例
	 * @throws Exception
	 * 
	 */
	public static <T> List<T> inportExcel(InputStream inputStream, Class<T> entityClass,
			HashMap<String, String> mapping, String[] requiredField, String[] specialfieldName,
			ExcelSpecialHanding handing) throws Exception {

		List<T> list = new ArrayList<T>();
		int count = 0;

		XSSFWorkbook xwb = new XSSFWorkbook(inputStream);
		// 读取第一章表格内容
		XSSFSheet sheet = xwb.getSheetAt(0);
		// 定义 row、cell
		XSSFRow row;
		String cell, fieldName = null;
		XSSFRow firstRow = sheet.getRow(0);
		String[] excelFieldNames = new String[firstRow.getPhysicalNumberOfCells()];
		T entity = null;

		// 获取Excel中的列名
		for (int i = 0; i < firstRow.getPhysicalNumberOfCells(); i++) {
			excelFieldNames[i] = firstRow.getCell(i).toString().trim();
			// System.out.println("第" + i + "个excelFieldNames的值：" +
			// excelFieldNames[i]);
		}

		/* 初始化handing的projectId */
		row = sheet.getRow(1);
		row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
		cell = row.getCell(0).toString();// 获取excel内容
		handing.setProjectName(cell);

		// 循环输出表格中的内容
		for (int i = sheet.getFirstRowNum() + 1; i < sheet.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			entity = entityClass.newInstance();
			for (int k = 0; k < row.getPhysicalNumberOfCells(); k++) {
				fieldName = (String) mapping.get(excelFieldNames[k]);

				row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
				cell = row.getCell(k).toString();// 获取excel内容

				if (cell == null || cell.equals("")) {

					for (String theFieldName : requiredField) {
						if (theFieldName.equals(fieldName)) {
							throw new NonullException(i, k);
						}
					}
					continue;
				}
				setValue(fieldName, entityClass, cell, entity);
			}
			// 上面的for结束后是指这一行能填的都填了，接下来则处理特殊字段
			for (String thefieldName : specialfieldName) {
				cell = handing.SpecialHanding(thefieldName, entityClass, entity);
				System.out.println("thefieldName=" + thefieldName + " cell=" + cell);
				setValue(thefieldName, entityClass, cell, entity);
			}
			
			count++;
			System.out.println("往list插入：" + ((Sample) entity).getLabCode());
			list.add(entity);
			System.out.println("共导入了" + count + "条");
		}

		return list;
	}

	/**
	 * @MethodName : setFieldValueByType
	 * @Description : 根据字段类型给对象的字段赋值
	 * @param getMethod
	 *            将要执行的方法
	 * @param cell
	 *            字段值
	 * @param fieldType
	 *            检测到的字段类型
	 * @param entity
	 *            要被赋值的对象
	 */
	private static <T> T setFieldValueByType(Method getMethod, String cell, Class<?> fieldType, T entity)
			throws Exception {
		SimpleDateFormat toDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		if (String.class == fieldType) {
			Object value = getMethod.invoke(entity, cell);
		} else if (Integer.class == fieldType) {
			Object value = getMethod.invoke(entity, Integer.parseInt(cell));
		} else if (Long.class == fieldType) {
			Object value = getMethod.invoke(entity, Long.parseLong(cell));
		} else if (Float.class == fieldType) {
			Object value = getMethod.invoke(entity, Float.parseFloat(cell));
		} else if (Short.class == fieldType) {
			Object value = getMethod.invoke(entity, Short.parseShort(cell));
		} else if (Double.class == fieldType) {
			Object value = getMethod.invoke(entity, Double.parseDouble(cell));
		} else if (Date.class == fieldType) {
			Object value = getMethod.invoke(entity, toDate.parse(cell));
		} else {
			// throw new Exception("导入excel为对象赋值时出错");
		}
		return entity;
	}

	/**
	 * @MethodName : getFieldByName
	 * @Description : 根据字段名获取字段
	 * @param fieldName
	 *            字段名
	 * @param clazz
	 *            包含该字段的类
	 * @return 字段
	 */
	private static Field getFieldByName(String fieldName, Class<?> clazz) {
		// 拿到本类的所有字段
		Field[] selfFields = clazz.getDeclaredFields();

		// 如果本类中存在该字段，则返回
		for (Field field : selfFields) {
			if (field.getName().equals(fieldName)) {
				return field;
			}
		}

		// 否则，查看父类中是否存在此字段，如果有则返回
		Class<?> superClazz = clazz.getSuperclass();
		if (superClazz != null && superClazz != Object.class) {
			return getFieldByName(fieldName, superClazz);
		}

		// 如果本类和父类都没有，则返回空
		return null;
	}

	/**
	 * @MethodName : setValue
	 * @Description : 根据反射给对象赋值
	 * @param fieldName
	 *            字段名
	 * @param clazz
	 *            包含该字段的类
	 * @return 字段
	 * @throws Exception
	 */
	private static <T> void setValue(String fieldName, Class<T> entityClass, String cell, T entity) throws Exception {
		String setMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		Class<?> fieldType = getFieldByName(fieldName, entityClass).getType();
		Method getMethod = entityClass.getMethod(setMethodName, fieldType);
		setFieldValueByType(getMethod, cell, fieldType, entity);
	}

}
