package com.example;

import com.example.demo.dao.ExcelDao;
import com.example.demo.entity.ExcelSheet1;
import com.example.demo.entity.ExcelSheet2;
import com.example.demo.service.EmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static com.example.demo.utils.ExcelUtils.*;


@SpringBootTest
class DemoApplicationTests {
    @Autowired
    private ExcelDao dao;
    @Autowired
    private EmailService emailService;
    @Autowired
    private JavaMailSender javaMailSender;

    @Test
    public void test01() throws IOException {
        List<ExcelSheet1> excelListSheet1 = dao.getAllExcelDataBySheet1();
        ModelToExcel("D:\\IdeaProjects\\MyWeb\\src\\main\\resources\\excelFile\\测试.xls",1,46,42,2,1, (ArrayList) excelListSheet1);

       // ModelToExcel("D:\\IdeaProjects\\MyWeb\\src\\main\\resources\\excelFile\\测试.xls",1,48,25,3,1, (ArrayList) excelList);
    }

    @Test
    public void test02() throws IOException {
//        emailService.senderEmail("18664445916@163.com","1308467355@qq.com","DenYang，您有一条新信息。","欢迎使用SpringBoot接受邮件！");
//        List excelList = dao.getAllExcelDataBySheet3();
//        ModelToExcel("D:\\IdeaProjects\\MyWeb\\src\\main\\resources\\excelFile\\测试.xls",2,50,24,3,1, (ArrayList) excelList);
//        Iterator iterator = excelList.listIterator();
//        while (iterator.hasNext()){
//            System.out.print(iterator.next());
//            System.out.println();
//        }
    }

    @Test
    public void test03() throws MessagingException, IOException {
//        String from = "18664445916@163.com";
//        String[] to = {"1308467355@qq.com","yang.zhengpt@cn.mcd.com"};
//        String subject = "您有一个新的文件需要下载";
//        String text = "请点击下方下载附件";
//        List<Excel> excelList = dao.getAllExcelData();
//        File excelFile = ExcelCopy("C:/Users/cn-yangzhengpt/Desktop/测试.xls");
//        InsertExcel(excelFile.getPath(), 1, 48, 3);
//        ModelToExcel(excelFile.getPath(), 48, 12, 3, 1, (ArrayList<Excel>) excelList);
//        emailService.senderEmail(from,to,subject,text,excelFile);
    }
}
