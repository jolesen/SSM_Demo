package com.stylefeng.guns.business.controller;

import com.stylefeng.guns.base.BaseJunit;
import com.stylefeng.guns.modular.business.entity.Sample;
import com.stylefeng.guns.modular.business.service.ISampleService;
import com.stylefeng.guns.modular.business.service.IThreadSampleService;
import com.stylefeng.guns.modular.business.util.ExcelReader;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class SampleControllerTest extends BaseJunit{

    @MockBean
    private ISampleService sampleService;

    @MockBean
    private IThreadSampleService threadSampleService;

    /**
     * 测试 add 方法
     * @author 梁俊鹏
     * @since 2017/8/15
     */
    @Test
    public void addTest() throws Exception {
        Sample sample = new Sample();
        sample.setSampleNumber("1111");

        when(sampleService.checkInput(sample))
                .thenReturn("success");


        Mockito.doNothing().when(sampleService).add(sample); // Mock the add method of sampleService

        mockMvc.perform(get("/sample/add").param("sampleNumber", "1111"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(200)));
    }

    /**
     * 测试删除样本，成功删除
     * @author 梁俊鹏
     * @since 2017/8/15
     */
    @Test
    public void deleteTest() throws Exception {
        mockMvc.perform(get("/sample/delete")).andExpect(status().isOk());
    }

    /**
     * 测试更新样本，成功更新
     * @author 梁俊鹏
     * @since 2017/8/15
     */
    @Test
    public void updateTest() throws Exception {
        mockMvc.perform(get("/sample/update")).andExpect(status().isOk());
    }

    /**
     * 测试列出样本细节，成功列出细节
     * @author 梁俊鹏
     * @since 2017/8/15
     */
    @Test
    public void detailTest() throws Exception {
        mockMvc.perform(get("/sample/detail")).andExpect(status().isOk());
    }

    /**
     * 测试 importExcel 方法，成功导入
     * Test data: jnuTop\target\test-classes\com\stylefeng\guns\business\controller\commit.xlsx (in Windows)
     * Test data: jnuTop/target/test-classes/com/stylefeng/guns/business/controller/commit.xlsx (in Windows)
     * @author 梁俊鹏
     * @since 2017/8/15
     */
    @Test
    public void insertExcelTest() throws Exception {

        // 模拟一个返回的Sample列表
        List<Sample> sampleList;
        MultipartFile excelMultipartFile = getExcelMultipartFile("commit.xlsx");

        sampleList = ExcelReader.read(excelMultipartFile.getInputStream());

        // SUCCESS insert data
        given(threadSampleService.inputSamples(sampleList,
                0, sampleList.size()-1))
                .willReturn("SUCCESS");

        // Successfully added to database
        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/sample/importExcel")
                .file((MockMultipartFile) excelMultipartFile))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(200)));


    }

    /**
     * 测试 importExcel 方法，抛出异常
     * Test data: jnuTop\target\test-classes\com\stylefeng\guns\business\controller\commit.xlsx (in Windows)
     * Test data: jnuTop/target/test-classes/com/stylefeng/guns/business/controller/commit.xlsx (in Windows)
     * @author 梁俊鹏
     * @since 2017/8/15
     */
    @Test
    public void importExcelTest_throwException() throws Exception {

        /*
        抛出异常
        */
        List<Sample> sampleList = new ArrayList<>(1);
        MultipartFile excelMultipartFile = getExcelMultipartFile("commit.xlsx");

        sampleList = ExcelReader.read(excelMultipartFile.getInputStream());

        given(threadSampleService.inputSamples(sampleList,
                0, sampleList.size()-1))
                .willThrow(new Exception());

        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/sample/importExcel")
                .file((MockMultipartFile) excelMultipartFile))
                .andExpect(status().is5xxServerError());
    }

    /**
     * 将用于测试的Excel文件封装成MultipartFile类型，并且可以自动识别不同系统的路径格式
     * @param testExcelName
     * @return a MultipartFile refers to the testing excel file ${testExcelName}
     * @throws URISyntaxException
     */
    private MultipartFile getExcelMultipartFile(String testExcelName) throws URISyntaxException {
        /* 从当前目录读取excel表格
         * 1, 2 行用于兼容不同的系统路径格式
         */
        String currentPath = Paths.get(SampleControllerTest.class.getResource("").toURI()).toString(); // 1
        Path excelPath = Paths.get(currentPath, testExcelName); // 2
        String name = new String("uploadFile");
        String originalFileName = new String(testExcelName);
        String contentType = new String("multipart/form-data");
        // 将excel文件存储在字符数组
        byte[] content = null;
        try {
            content = Files.readAllBytes(excelPath);
        } catch (final IOException ioe) {
            System.err.println("Failed to read excel file, please check the file path.");
            ioe.printStackTrace();
        }
        // 模拟一个MultipartFile
        MultipartFile result = new MockMultipartFile(name,
                originalFileName, contentType, content);

        return result;
    }
}
