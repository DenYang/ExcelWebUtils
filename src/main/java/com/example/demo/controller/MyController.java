package com.example.demo.controller;

import com.example.demo.entity.ExcelSheet2;
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
        List<ExcelSheet2> excelSheet2List = excelService.getAllExcelDataBySheet2();
        File file = ModelToExcel("D:\\IdeaProjects\\MyWeb\\src\\main\\resources\\excelFile\\测试.xls",1,48,26,3,1, (ArrayList) excelSheet2List);
        String fileName = "";// 文件名
        if (fileName != null) {
            //设置文件路径

            //ModelToExcel(excelFile.getPath(), 48, 9, 3, 1, (ArrayList<Excel>) excelList);
            File newFile = new File(file.getPath());
            if (newFile.exists()) {
                response.setContentType("application/force-download");
                response.addHeader("Content-Disposition", "attachment;fileName="+newFile.getName());
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
                }
            }
        }
        return "failure";
    }
}
