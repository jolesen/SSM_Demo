package com.stylefeng.guns.modular.business.service.impl;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.mockito.Matchers.intThat;

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
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.stylefeng.guns.common.exception.ErrorWrapException;
import com.stylefeng.guns.common.exception.FileNotExistException;
import com.stylefeng.guns.common.exception.NonullException;
import com.stylefeng.guns.common.exception.RepeatException;
import com.stylefeng.guns.modular.business.dao.SampleDao;
import com.stylefeng.guns.modular.business.entity.Sample;
import com.stylefeng.guns.modular.business.service.IFileIOAuxiliaryService;
import com.stylefeng.guns.modular.business.service.IProjectService;
import com.stylefeng.guns.modular.business.util.ExcelSpecialHanding;
import com.stylefeng.guns.modular.business.util.ExportExcel;
import com.stylefeng.guns.modular.business.util.ImportExcel;

@Service
public class FileIOAuxiliaryServiceImpl implements IFileIOAuxiliaryService {
	@Resource
	private SampleDao sampleDao;
	@Resource
	private IProjectService projectService;
	private String excelExportURL = "E:\\projectTest\\"; // 服务器导出excel的默认路径
	private String excelExportName = "output.xls"; // 服务器导出excel的默认名字
	private String attachmentSaveURL = "E:\\projectTest\\";// 服务器保存附件的默认路径
	private String zipAttachmentURL = "E:\\projectTest\\"; // 服务器附件压缩的默认路径
	private String zipAttachmentName = "attachment.zip"; // 服务器附件压缩的默认名称

	public <T> void InsertCheck(List<T> list, String entityName) throws NonullException, RepeatException, Exception {

		int start = 0, end = list.size() - 1;
		System.out.println("对象为：" + entityName.trim() + " 准备插入数据库 start:" + start + " end:" + end);

		if ("com.stylefeng.guns.modular.business.entity.Sample".equals(entityName.trim())) {
			int i = start;
			for (; i <= end; i++) {
				Sample sample = (Sample) list.get(i);
				String detection_item = sample.getDetectionItem();
				String sample_number = sample.getSampleNumber();
				List<Sample> samples = sampleDao.selectByCondition(detection_item, sample_number);

				if (samples != null) {

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

			}

		} else {
			System.out.println("没有找到该对象的Dao");
			throw new Exception("没有找到该对象的Dao");
		}

	}

	@Override
	public void saveAttachment(MultipartFile uploadFile, String entityName, String id) throws IOException {

		InputStream inputStream = uploadFile.getInputStream();
		String fileName = uploadFile.getOriginalFilename();
		String attachmentPath = attachmentSaveURL + fileName;
		System.out.println("进入saveAttachment");
		recordAttachmentPath(entityName, id, attachmentSaveURL, fileName);

		OutputStream os = null;
		try {

			// 1K的数据缓冲
			byte[] bs = new byte[1024];
			// 读取到的数据长度
			int len;
			// 输出的文件流保存到本地文件

			File tempFile = new File(attachmentSaveURL);

			if (!tempFile.exists()) {
				tempFile.mkdirs();
			}
			os = new FileOutputStream(tempFile.getPath() + File.separator + fileName);
			// 开始读取
			while ((len = inputStream.read(bs)) != -1) {
				os.write(bs, 0, len);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 完毕，关闭所有链接
			try {
				os.close();
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 将附件路径存入
	 * 
	 * @param entityName
	 *            要调用的类的名字
	 * @param id
	 *            具体要保存路径的类id
	 * @param attachmentSaveURL
	 *            附件保存的路径
	 * @param fileName
	 *            附件名
	 */
	private void recordAttachmentPath(String entityName, String id, String attachmentSaveURL, String fileName) {

		String AttachmentPath = attachmentSaveURL + fileName;
		if ("com.stylefeng.guns.modular.business.entity.Sample".equals(entityName.trim())) {
			Sample sample = sampleDao.selectById(id);
			String oldAttachmentPath = sample.getAttachmentPath();
			System.out.println("旧的路径为："+oldAttachmentPath);
			if (oldAttachmentPath == null || "".equals(oldAttachmentPath)) {
				sample.setAttachmentPath(AttachmentPath);
			} else {
				sample.setAttachmentPath(oldAttachmentPath + "," + AttachmentPath);
			}
			System.out.println("现在的附件路径为："+sample.getAttachmentPath());
			sampleDao.updateById(sample);
		}
	}

	public <T> void insertExcel(@RequestParam("uploadFile") MultipartFile uploadFile, String entityName, String map,
			String requiredField, String title, String specialfieldName)
			throws NonullException, RepeatException, Exception {
		List<T> list;
		Object aObject;

		if ("com.stylefeng.guns.modular.business.entity.Sample".equals(entityName)) {
			ExcelSpecialHanding handing = new ExcelSpecialHanding(title, projectService, sampleDao.selectCount(null));
			HashMap<String, String> mapping = stringToMap(map);
			System.out.println("转化后的map为： " + mapping.toString());

			Class clz = Class.forName(entityName);
			list = ImportExcel.inportExcel(uploadFile.getInputStream(), clz, mapping, requiredField.split(","),
					specialfieldName.split(","), handing);
			InsertCheck(list, entityName);
		}

	}

	@Override
	public HashMap<String, String> stringToMap(String needChange) {

		HashMap<String, String> map = new HashMap<String, String>();
		String[] breakUp = needChange.split(",");

		for (int i = 0; i < breakUp.length; i = i + 2) {
			System.out.println(breakUp[i] + " | " + breakUp[i + 1]);
			map.put(breakUp[i], breakUp[i + 1]);
		}
		return map;
	}

	public JSONObject outputExcel(String ids, String title, String needExports) throws Exception {
		List<Sample> list;
		list = selectListByIds(ids);

		try {
			OutputStream out = new FileOutputStream(excelExportURL + excelExportName);
			ExportExcel.exportExcel(title.split(","), list, out, needExports.split(","));
			out.close();
			System.out.println("excel导出成功！");
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}
		JSONObject result = new JSONObject();
		result.put("filedir", excelExportURL);
		result.put("filename", excelExportName);
		return result;
	}

	public List<Sample> selectListByIds(String Ids) throws Exception {
		List<Sample> list = new ArrayList<Sample>();
		if (Ids != null && !"".equals(Ids)) {
			String[] sampleIds = Ids.split(",");
			for (String id : sampleIds) {
				Sample sample = sampleDao.selectById(Integer.parseInt(id));
				if (sample == null)
					throw new Exception("id为：" + id + "为空！");
				list.add(sample);
			}
		}
		return list;
	}

	public void ajaxDownload(HttpServletResponse response, HttpServletRequest request) throws Exception {
		InputStream ins = null;
		BufferedInputStream bins = null;
		OutputStream outs = null;
		BufferedOutputStream bouts = null;
		String file_name = request.getParameter("filename").trim(); // 文件名
		String file_dir = request.getParameter("filedir").trim(); // 文件路径
		System.out.println("获取到文件路径：" + file_dir + File.separator + file_name);
		try {
			if (!"".equals(file_name)) {
				File file = new File(file_dir + File.separator + file_name);
				if (file.exists()) {
					ins = new FileInputStream(file_dir + File.separator + file_name);
					bins = new BufferedInputStream(ins);
					outs = response.getOutputStream();
					bouts = new BufferedOutputStream(outs);
					response.setContentType("application/x-download");
					response.setHeader("Content-disposition",
							"attachment;filename=" + URLEncoder.encode(file_name, "UTF-8"));
					int bytesRead = 0;
					byte[] buffer = new byte[8192];
					while ((bytesRead = bins.read(buffer, 0, 8192)) != -1) {
						bouts.write(buffer, 0, bytesRead);
					}
					bouts.flush();
				} else {
					throw new ErrorWrapException("下载的文件不存在！", "下载的文件不存在！");
				}
			} else {
				throw new ErrorWrapException("下载文件时发生错误！", "下载文件时发生错误！");
			}
		} catch (Exception e) {

		} finally {
			if (null != ins) {
				ins.close();
			}
			if (null != bins) {
				bins.close();
			}
			if (null != outs) {
				outs.close();
			}
			if (null != bouts) {
				bouts.close();
			}
		}
	}

	@Override
	public JSONObject downloadAttachment(String entityName, String id) throws Exception {
		List<File> files = new ArrayList<File>();
		File file = null;

		if ("com.stylefeng.guns.modular.business.entity.Sample".equals(entityName)) {
			Sample sample = sampleDao.selectById(id);
			String attachmentPath = sample.getAttachmentPath();
			if(attachmentPath==null||"".equals(attachmentPath))throw new Exception("没有附件可下载");
			
			String[] path = attachmentPath.split(",");

			for (int i = 0; i < path.length; i++) {
				file = new File(path[i]);
				if (!file.exists())
					throw new FileNotExistException(path[i]);
				files.add(file);
			}
		}
        System.out.println("附件已全部获取，准备压缩");
		zipFiles(files, zipAttachmentURL+zipAttachmentName);
		
		JSONObject result = new JSONObject();
		result.put("filedir", zipAttachmentURL);
		result.put("filename", zipAttachmentName);
		return result;
	}

	/**
	 * 功能:压缩多个文件成一个zip文件
	 * 
	 * @param files：源文件列表
	 * @param zipURL：
	 *            压缩后的文件路径
	 */
	private void zipFiles(List<File> files, String zipURL) {

		File zipFile = new File(zipURL);
		if (zipFile.exists()) {
			// 压缩文件名=源文件名.zip
			String zipName = zipFile.getName();
			File target = new File(zipFile.getParent(), zipName);
			if (target.exists()) {
				target.delete(); // 删除旧的文件
			}
		}
		byte[] buf = new byte[1024];
		try {
			// ZipOutputStream类：完成文件或文件夹的压缩
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));
			for (int i = 0; i < files.size(); i++) {
				FileInputStream in = new FileInputStream(files.get(i));
				out.putNextEntry(new ZipEntry(files.get(i).getName()));
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				out.closeEntry();
				in.close();
			}
			out.close();
			System.out.println("压缩完成.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
