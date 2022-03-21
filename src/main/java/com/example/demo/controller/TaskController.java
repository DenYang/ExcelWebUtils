package com.example.demo.controller;

import com.example.demo.entity.ExcelSheet1;
import com.example.demo.entity.ExcelSheet2;
import com.example.demo.entity.ExcelSheet3;
import com.example.demo.service.EmailService;
import com.example.demo.service.ExcelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.demo.utils.ExcelUtils.ModelToExcel;

@Controller
public class TaskController {
    int i = 0;
    private EmailService emailService;
    private ExcelService excelService;
    private static final SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
    private static final Logger log = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    public TaskController(EmailService emailService,ExcelService excelService) {
        this.emailService = emailService;
        this.excelService = excelService;
    }

    public TaskController() {
    }

    public void sendMailAtTime(){
        log.debug("num",i);
        i ++;
        String subject = "这是第"+i+"次发送邮件，请您注意安全！";
        String text = "现在是第"+i+"次发送，目前时间是"+format.format(new Date());
        emailService.sendSimpleMail("1308467355@qq.com",subject,text);
    }

    @Scheduled(cron = "0 0,5,10,13,15,20,25,30,35,40,45,50,55 9,10,11,12,16,17 * * ? ")
    public void sendFileMailAtTime(){
        i++;
        log.debug("sum:",i);
        String subject = "您有一个新文件需要处理";
        String text = "附件"+i+":目前时间是:"+format.format(new Date());
        List<ExcelSheet1> excelSheet1List = excelService.getAllExcelDataBySheet1();
        List<ExcelSheet2> excelSheet2List = excelService.getAllExcelDataBySheet2();
        List<ExcelSheet3> excelSheet3List = excelService.getAllExcelDataBySheet3();
//        ModelToExcel("D:\\IdeaProjects\\MyWeb\\src\\main\\resources\\excelFile\\测试.xls",2,50,24,3,1, (ArrayList) excelList);
        try {
            File file = ModelToExcel("D:\\IdeaProjects\\MyWeb\\src\\main\\resources\\excelFile\\测试.xls",1,46,42,2,1, (ArrayList) excelSheet1List);
            File file1 = ModelToExcel(file.getCanonicalPath(),2,48,25,3,1, (ArrayList) excelSheet2List);
            File file2 = ModelToExcel(file1.getCanonicalPath(),3,50,24,3,1, (ArrayList) excelSheet3List);
            emailService.sendFileMail("",subject,text,file2);
            if (file2.exists()){
                file2.delete();
            }
            if (file1.exists()){
                file1.delete();
            }
            if (file.exists()){
                file.delete();
            }
        } catch (IOException e) {
            log.debug(e.getMessage());
        }
    }
}
