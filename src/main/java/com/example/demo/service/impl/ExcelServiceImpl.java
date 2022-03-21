package com.example.demo.service.impl;

import com.example.demo.dao.ExcelDao;
import com.example.demo.entity.ExcelSheet1;
import com.example.demo.entity.ExcelSheet2;
import com.example.demo.entity.ExcelSheet3;
import com.example.demo.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ExcelServiceImpl implements ExcelService {

    private ExcelDao dao;

    @Autowired
    public ExcelServiceImpl(ExcelDao dao) {
        this.dao = dao;
    }

    @Override
    public List<ExcelSheet1> getAllExcelDataBySheet1() {
        return dao.getAllExcelDataBySheet1();
    }

    @Override
    public List<ExcelSheet2> getAllExcelDataBySheet2() {
        return dao.getAllExcelDataBySheet2();
    }

    @Override
    public List<ExcelSheet3> getAllExcelDataBySheet3() {
        return dao.getAllExcelDataBySheet3();
    }
}

