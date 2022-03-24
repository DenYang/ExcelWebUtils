package com.example.demo.controller;

import com.example.demo.entity.ExcelSheet1;
import com.example.demo.entity.ExcelSheet2;
import com.example.demo.entity.ExcelSheet3;
import com.example.demo.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.demo.utils.ExcelUtils.*;

@RestController
public class MyController {

    private ExcelService excelService;

    @Autowired
    public MyController(ExcelService excelService) {
        this.excelService = excelService;
    }

    @RequestMapping("/get")
    public Object[][] getAllExcel(){
        List<ExcelSheet2> excelSheet2List = excelService.getAllExcelDataBySheet2();
        //ListToArray(excelList,48,9);
        return ListToArray((ArrayList) excelSheet2List,48,26);
    }

    @RequestMapping("/download")
    public String downloadFile(HttpServletRequest request,
                               HttpServletResponse response) throws IOException {
        List<ExcelSheet1> excelSheet1List = excelService.getAllExcelDataBySheet1();
        List<ExcelSheet2> excelSheet2List = excelService.getAllExcelDataBySheet2();
        List<ExcelSheet3> excelSheet3List = excelService.getAllExcelDataBySheet3();
        File file1 = ModelToExcel("D:\\IdeaProjects\\MyWeb\\src\\main\\resources\\excelFile\\测试.xls",1,46,42,2,1, (ArrayList) excelSheet1List);
        File file2 = ModelToExcel(file1.getCanonicalPath(),2,48,25,3,1, (ArrayList) excelSheet2List);
        File file3 = ModelToExcel(file2.getCanonicalPath(),3,50,24,3,1, (ArrayList) excelSheet3List);
        String fileName = "";// 文件名
        //设置文件路径

        //ModelToExcel(excelFile.getPath(), 48, 9, 3, 1, (ArrayList<Excel>) excelList);
        File newFile = new File(file3.getPath());
        if (newFile.exists()) {
            response.setContentType("application/force-download");
            response.addHeader("Content-Disposition", "attachment;fileName=download.xls");
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(newFile);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                return "download success";
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                bis.close();
                fis.close();
                if (file1.exists()){
                    file1.delete();
                }
                if (file2.exists()){
                    file2.delete();
                }
                if (file3.exists()){
                    file3.delete();
                }
            }
        }
        return "failure";
    }
}
