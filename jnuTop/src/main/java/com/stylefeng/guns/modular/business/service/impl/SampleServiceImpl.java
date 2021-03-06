package com.stylefeng.guns.modular.business.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.stylefeng.guns.common.exception.BussinessException;
import com.stylefeng.guns.common.exception.ErrorWrapException;
import com.stylefeng.guns.common.exception.NonullException;
import com.stylefeng.guns.common.exception.RepeatException;
import com.stylefeng.guns.modular.business.dao.SampleDao;
import com.stylefeng.guns.modular.business.entity.Sample;
import com.stylefeng.guns.modular.business.service.IFileIOAuxiliaryService;
import com.stylefeng.guns.modular.business.service.IProjectService;
import com.stylefeng.guns.modular.business.service.ISampleService;
import com.stylefeng.guns.modular.business.util.ExportExcel;
import com.stylefeng.guns.modular.business.util.ImportExcel;
import com.stylefeng.guns.modular.business.util.*;

/**
 * 样本Service
 *
 * @author djb
 * @Date 2017-08-09 14:40:25
 */
@Service
public class SampleServiceImpl implements ISampleService {

	@Resource
	private SampleDao sampleDao;

	@Override
	public void add(Sample sample) {

		try {
			sampleDao.insert(sample);
		} catch (Exception e) {
			throw new BussinessException(BizExceptionEnum.SERVER_ERROR);
		}

	}

	@Override
	public String checkInput(Sample sample) {
		if (sample.getSampleStorage() == null) {
			return "sampleStorage";
		} else if (sample.getDetectionItem() == null) {
			return "detectionItem";
		} else if (sample.getLabCode() == null) {
			return "labCode";
		} else if (sample.getSampleNumber() == null) {
			return "sampleNumber";
		} else if (sample.getSubjectName() == null) {
			return "subjectName";
		}
		return "success";
	}

	public Sample findSampleById(Integer sample_id) {
		return sampleDao.selectById(sample_id);
	}


	public void deleteById(String[] sampleIds) {
		if (sampleIds != null && sampleIds.length > 0) {
			try {
				for (String id : sampleIds) {
					sampleDao.deleteById_self(Integer.parseInt(id));

				}
			} catch (Exception e) {
				throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
			}

		}
	}

	public int updateSample(Sample sample) {
		int i = sampleDao.updateById(sample);

		return i;

	}

	public void setSampleDao(SampleDao sampleDao) {
		this.sampleDao = sampleDao;
	}

}
