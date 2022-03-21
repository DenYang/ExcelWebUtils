package com.example.demo.dao;

import com.example.demo.entity.ExcelSheet1;
import com.example.demo.entity.ExcelSheet2;
import com.example.demo.entity.ExcelSheet3;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ExcelDao {
    List<ExcelSheet1> getAllExcelDataBySheet1();
    List<ExcelSheet2> getAllExcelDataBySheet2();
    List<ExcelSheet3> getAllExcelDataBySheet3();
}
