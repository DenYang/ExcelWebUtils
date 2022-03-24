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
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
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

    }

    @Test
    public void test02() throws IOException {

    }

    @Test
    public void test03() throws MessagingException, IOException {
//
    }
    @Test
    public void test04() throws IOException {


    }
}
