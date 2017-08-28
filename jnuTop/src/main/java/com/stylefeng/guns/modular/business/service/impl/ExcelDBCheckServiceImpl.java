package com.stylefeng.guns.modular.business.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.stylefeng.guns.common.exception.NonullException;
import com.stylefeng.guns.common.exception.RepeatException;
import com.stylefeng.guns.modular.business.dao.SampleDao;
import com.stylefeng.guns.modular.business.entity.Sample;
import com.stylefeng.guns.modular.business.service.IExcelDBCheckService;

@Service
public class ExcelDBCheckServiceImpl implements IExcelDBCheckService {
	@Resource
	private SampleDao sampleDao;

	public <T> void InsertCheck(List<T> list, String entityName) throws NonullException,RepeatException,Exception  {

		int start = 0, end = list.size() - 1;
		System.out.println("对象为：" + entityName.trim() + " 准备插入数据库 start:" + start + " end:" + end);

		if (entityName.trim().equals("com.stylefeng.guns.modular.business.entity.Sample")) {
			int i = start;
			// try {
			for (; i <= end; i++) {
				Sample sample = (Sample) list.get(i);

				// System.out.println("将要插入：" + sample.getDetectionItem()+" "+
				// sample.getSampleNumber());
				String detection_item = sample.getDetectionItem();
				String sample_number = sample.getSampleNumber();
				List<Sample> samples = sampleDao.selectByCondition(detection_item, sample_number);

				if (samples != null) {
					// System.out.println("获取到的值为：" + sample + " delete=" +
					// sample.getDeleted());
					for (int j = 0; j < samples.size(); j++) {
						if (samples.get(j).getDeleted() == 1) {
							continue;
						} else {
							System.out.println("第" + (i + 1) + "条记录数据重复");
							throw new RepeatException(i);
						}
					}
					sampleDao.insert((Sample) list.get(i));
				} else {
					sampleDao.insert((Sample) list.get(i));
				}
				// }
			}
			// catch (Exception e) {
			// TODO: handle exception
			// System.out.println("第" + (i + 1) + "条插入出错：" + e.getMessage());
			// throw new Exception("第" + (i + 1) + "条插入出错：" + e.getMessage());
			// }
		} else {
			System.out.println("没有找到该对象的Dao");
			throw new Exception("没有找到该对象的Dao");
		}

	}
}
