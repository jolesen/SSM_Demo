package com.stylefeng.guns.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;

import static com.stylefeng.guns.core.util.ToolUtil.getTempPath;
import static com.stylefeng.guns.core.util.ToolUtil.isEmpty;

/**
 * 文件上传下载的配置
 *
 * @author djb
 * @Date 2917-8-30
 */
@Component
@ConfigurationProperties(prefix = FileIOProperties.PREFIX)
public class FileIOProperties {

    public static final String PREFIX = "FileIO";
    
    private String ExportURL ;
    private String excelExportName ;
    private String zipAttachmentName ;
    
	public String getExportURL() {
		return ExportURL;
	}
	public void setExportURL(String exportURL) {
		ExportURL = exportURL;
	}
	public String getExcelExportName() {
		return excelExportName;
	}
	public void setExcelExportName(String excelExportName) {
		this.excelExportName = excelExportName;
	}
	public String getZipAttachmentName() {
		return zipAttachmentName;
	}
	public void setZipAttachmentName(String zipAttachmentName) {
		this.zipAttachmentName = zipAttachmentName;
	}
	public static String getPrefix() {
		return PREFIX;
	}
 
}
