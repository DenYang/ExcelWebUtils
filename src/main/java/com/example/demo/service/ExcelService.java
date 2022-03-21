package com.example.demo.service;

import com.example.demo.entity.ExcelSheet1;
import com.example.demo.entity.ExcelSheet2;
import com.example.demo.entity.ExcelSheet3;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExcelService {
    List<ExcelSheet1> getAllExcelDataBySheet1();
    List<ExcelSheet2> getAllExcelDataBySheet2();
    List<ExcelSheet3> getAllExcelDataBySheet3();
}
