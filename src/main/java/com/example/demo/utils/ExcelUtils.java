package com.example.demo.utils;

import java.util.*;
import java.io.*;

import com.example.demo.entity.ExcelSheet1;
import com.example.demo.entity.ExcelSheet3;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.poi.hssf.usermodel.*;
import com.example.demo.entity.ExcelSheet2;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFColor;


import java.math.BigDecimal;
import java.nio.file.Files;



public class ExcelUtils {
    /**
     * @param fileName 本地Excel文件名字
     * @param index    选择工作簿,数值不能小于1
     * @param rowCount 需要插入的数据行数
     * @param rowHead  文档行头
     */
    public static void InsertExcel(String fileName, int index, int rowCount, int rowHead) throws IOException {
        HSSFWorkbook workbook;
        FileInputStream fileInputStream = null;
        fileInputStream = new FileInputStream(fileName);
        workbook = new HSSFWorkbook(fileInputStream);
        HSSFSheet sheet = workbook.getSheetAt(index);
        for (int i = rowHead; i < rowHead + rowCount - 1; i++) {//从行头开始循环rowCount行表格
            HSSFRow sourceRow = sheet.getRow(i);//获取上一行对象
            HSSFRow newRow = sheet.createRow(i + 1);//开始新建一行
            for (int j = 0; j < sourceRow.getLastCellNum(); j++) {
                HSSFCell oldCell = sourceRow.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);//获取原行的每一个单元格对象，单元格数据为空白时，返回Null
                HSSFCellStyle oldStyle = oldCell.getCellStyle();//获取旧单元格的样式
                HSSFCell newCell = newRow.createCell(j);//创建新的单元格
                newCell.setCellStyle(oldStyle);//将旧单元格的样式应用到新单元格上
            }
        }
        FileOutputStream outputStream = new FileOutputStream(fileName);
        workbook.write(outputStream);
        outputStream.close();
        fileInputStream.close();
    }

    /**
     * @param fileName    本地Excel文件名字
     * @param index       选择工作簿,数值不能小于1
     * @param rowCount    总共要写入的行数
     * @param columnCount 总共要写入的列数
     * @param rowHead     定义文档行头
     * @param columnHead  定义文档列头
     * @param data        数据源，是一个ArrayList集合
     */
    public static File ModelToExcel(String fileName, int index, int rowCount, int columnCount, int rowHead, int columnHead, ArrayList data) throws IOException {

        index = index - 1;
        File copyFile = ExcelCopy(fileName);
        InsertExcel(copyFile.getPath(), index, rowCount, rowHead);

        FileInputStream inputStream = null;
        inputStream = new FileInputStream(copyFile.getPath());
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet = workbook.getSheetAt(index);
        HSSFRow row;
        HSSFCell cell;
        Object[][] newData = ListToArray(data, rowCount, columnCount);
        for (int i = rowHead; i < rowHead + rowCount; i++) { //遍历表格行数
            row = sheet.getRow(i);//获取第i行
            for (int j = columnHead; j < columnHead + columnCount; j++) {//获取每一行的每一列
                cell = row.getCell(j);//获取第j个元素的单元格对象
                Object object = newData[i - rowHead][j - columnHead];//获取对象中的数据
                if (null == object) {//如果对象中的数据为空，则设置为空白字符串，否则会出现空指针异常
                    object = "";
                    cell.setCellValue(object.toString());
                }
                //对data内的数据进行判断，根据不同类型转换数值
                if (object.getClass() == Integer.class || object.getClass() == Double.class || object.getClass() == Float.class) {
                    Double parseData = (double) object;//数字类型一律强转为Double
                    //Double.doubleToLongBits(0.01) == Double.doubleToLongBits(0.01)
                    if (BigDecimal.ZERO.compareTo(BigDecimal.valueOf(parseData)) == 0) { //如果单元格数据为0，则将单元格格式设置为空白字符串
                        cell.setCellValue("");
                    } else cell.setCellValue(Double.valueOf(parseData));//设置单元格类型为Double，并存入数据
                } else {
                    cell.setCellValue(object.toString());//设置单元格类型为String，并存入数据
                }
            }
        }
        setFontBold(newData,workbook,sheet,rowHead,columnHead);
        MergeCell(newData,1,sheet,rowHead,columnHead);
        MergeCell(newData,2,sheet,rowHead,columnHead);
        MergeCell(newData,3,sheet,rowHead,columnHead);


        FileOutputStream outputStream = new FileOutputStream(copyFile.getPath());
        workbook.write(outputStream);
        outputStream.close();
        inputStream.close();
        File sourceFile = new File(copyFile.getPath());
        return sourceFile;
    }

    public static File ExcelCopy(String source) throws IOException {
        Double i = (Math.random() * Math.random() * System.currentTimeMillis());
        String str = i.toString().substring(5, 11);
        File sourceFile = new File(source);
        File destFile = new File(sourceFile.getParent() + "\\副本" + str + sourceFile.getName().substring(sourceFile.getName().length() - 4));
        Files.copy(sourceFile.toPath(), destFile.toPath());
        return destFile;
    }

    /**
     * @param excelList   一个excel list数据源
     * @param rowCount    该数据源的行数
     * @param columnCount 该数据源的列数
     * @return Object[][] 将数据转换后的二维数组
     */
    public static Object[][] ListToArray(ArrayList excelList, int rowCount, int columnCount) {
        ArrayList<Object> arrayList = new ArrayList<>();
        Iterator iterator = excelList.listIterator();//使用迭代器获取数据源中的每个Excel实体类对象
        if (iterator.next() instanceof ExcelSheet1) {
            iterator = excelList.listIterator();
            while ((iterator.hasNext())) {
                ExcelSheet1 excelSheet1 = (ExcelSheet1) iterator.next();
                arrayList.add(excelSheet1.getReportLineName());
                arrayList.add(excelSheet1.getBlank1());
                arrayList.add(excelSheet1.getMtdActual());
                arrayList.add(excelSheet1.getMtdPlan());
                arrayList.add(excelSheet1.getChangeVsPlan1());
                arrayList.add(excelSheet1.getLastYearMtdActual());
                arrayList.add(excelSheet1.getChangeVsLastYear1());
                arrayList.add(excelSheet1.getBlank2());
                arrayList.add(excelSheet1.getYtdActual());
                arrayList.add(excelSheet1.getYtdPlan());
                arrayList.add(excelSheet1.getChangeVsPlan2());
                arrayList.add(excelSheet1.getLastYearYtdActual());
                arrayList.add(excelSheet1.getChangeVsLastYear2());
                arrayList.add(excelSheet1.getBlank3());
                arrayList.add(excelSheet1.getLeftInPlan());
                arrayList.add(excelSheet1.getPlanRemaining());
                arrayList.add(excelSheet1.getJan());
                arrayList.add(excelSheet1.getFeb());
                arrayList.add(excelSheet1.getMar());
                arrayList.add(excelSheet1.getApr());
                arrayList.add(excelSheet1.getMay());
                arrayList.add(excelSheet1.getJun());
                arrayList.add(excelSheet1.getJul());
                arrayList.add(excelSheet1.getAug());
                arrayList.add(excelSheet1.getSep());
                arrayList.add(excelSheet1.getOct());
                arrayList.add(excelSheet1.getNov());
                arrayList.add(excelSheet1.getDec());
                arrayList.add(excelSheet1.getActual());
                arrayList.add(excelSheet1.getJan1());
                arrayList.add(excelSheet1.getFeb1());
                arrayList.add(excelSheet1.getMar1());
                arrayList.add(excelSheet1.getApr1());
                arrayList.add(excelSheet1.getMay1());
                arrayList.add(excelSheet1.getJun1());
                arrayList.add(excelSheet1.getJul1());
                arrayList.add(excelSheet1.getAug1());
                arrayList.add(excelSheet1.getSep1());
                arrayList.add(excelSheet1.getOct1());
                arrayList.add(excelSheet1.getNov1());
                arrayList.add(excelSheet1.getDec1());
                arrayList.add(excelSheet1.getPlan());
            }
        } else if (iterator.next() instanceof ExcelSheet2) {
            iterator = excelList.listIterator();
            while (iterator.hasNext()) {
                ExcelSheet2 excelSheet2 = (ExcelSheet2) iterator.next();
            /*
            获取Excel实体类对象的各个数据，并将数据添加到新的ArrayList当中
             */
                arrayList.add(excelSheet2.getReport());
                arrayList.add(excelSheet2.getAccount());
                arrayList.add(excelSheet2.getVendor());
                arrayList.add(excelSheet2.getBlk1());
                arrayList.add(excelSheet2.getMtdActual());
                arrayList.add(excelSheet2.getlYMtdActual());
                arrayList.add(excelSheet2.getChangeVsLY());
                arrayList.add(excelSheet2.getBlk2());
                arrayList.add(excelSheet2.getYtdActual());
                arrayList.add(excelSheet2.getlYYtdActual());
                arrayList.add(excelSheet2.getChangeVsLY1());
                arrayList.add(excelSheet2.getBlk3());
                arrayList.add(excelSheet2.getJan());
                arrayList.add(excelSheet2.getFeb());
                arrayList.add(excelSheet2.getMar());
                arrayList.add(excelSheet2.getApr());
                arrayList.add(excelSheet2.getMay());
                arrayList.add(excelSheet2.getJun());
                arrayList.add(excelSheet2.getJul());
                arrayList.add(excelSheet2.getAug());
                arrayList.add(excelSheet2.getSep());
                arrayList.add(excelSheet2.getOct());
                arrayList.add(excelSheet2.getNov());
                arrayList.add(excelSheet2.getDec());
                arrayList.add(excelSheet2.getYtdActual1());
            }
        } else if (iterator.next() instanceof ExcelSheet3) {
            iterator = excelList.listIterator();
            while (iterator.hasNext()) {
                ExcelSheet3 excelSheet3 = (ExcelSheet3) iterator.next();
            /*
            获取Excel实体类对象的各个数据，并将数据添加到新的ArrayList当中
             */
                arrayList.add(excelSheet3.getVendor());
                arrayList.add(excelSheet3.getAccount());
                arrayList.add(excelSheet3.getBlk1());
                arrayList.add(excelSheet3.getMtdActual());
                arrayList.add(excelSheet3.getlYMtdActual());
                arrayList.add(excelSheet3.getChangeVsLY());
                arrayList.add(excelSheet3.getBlk2());
                arrayList.add(excelSheet3.getYtdActual());
                arrayList.add(excelSheet3.getlYYtdActual());
                arrayList.add(excelSheet3.getChangeVsLY1());
                arrayList.add(excelSheet3.getBlk3());
                arrayList.add(excelSheet3.getJan());
                arrayList.add(excelSheet3.getFeb());
                arrayList.add(excelSheet3.getMar());
                arrayList.add(excelSheet3.getApr());
                arrayList.add(excelSheet3.getMay());
                arrayList.add(excelSheet3.getJun());
                arrayList.add(excelSheet3.getJul());
                arrayList.add(excelSheet3.getAug());
                arrayList.add(excelSheet3.getSep());
                arrayList.add(excelSheet3.getOct());
                arrayList.add(excelSheet3.getNov());
                arrayList.add(excelSheet3.getDec());
                arrayList.add(excelSheet3.getYtdActual1());
            }
        } else System.out.println("错误数据！");

        ListIterator listIterator = arrayList.listIterator();
        Object[][] objects = new Object[rowCount][columnCount];//新建一个和数据源一样行列的二维数组
        /*
        对二维数组进行遍历，并将新的ArrayList当中保存的数据写入到数组中，这样子数组的结构和数据源一致
         */
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                if (listIterator.hasNext()) {
                    objects[i][j] = listIterator.next();
                    if (null == objects[i][j]) {//判断数据是否为空，如果是，将数据改成空白字符串
                        objects[i][j] = "";
                    }
                }
            }
        }
        return objects;
    }

    public static void MergeCell(Object[][] newData, int col, HSSFSheet sheet, int rowHead, int columnHead) {
        ArrayList<Integer> arrayList = new ArrayList();
        for (int k = 0; k < newData.length; k++) {//遍历二维数组中的第一维度（行数）
            int num = col - columnHead;
            if (newData[k][num].toString() != "" || null == newData[k][col]) {//二维数组第k行的第一个值不等于空的字符串
                //计数器加1
                arrayList.add(k + 1);//将数值传入List中
            }
        }
        for (int i = 0; i < arrayList.size(); i++) {//遍历List
            int region1;//设置合并区域
            int region2;
            //list集合的Count属性是多少个元素，由于i是从0开始的，所以i永远小于Count。
            //必须先将Count减一操作之后再和i进行比较。
            if (i < arrayList.size() - 1) {
                region1 = arrayList.get(i) + rowHead - 1;//代表数据中的第一个为Account Name
                region2 = arrayList.get(i + 1) + rowHead - 2;//代表数据中的下一个Account Name
                //所以要合并的区域为 region1+rowHead - 1 到 region2 -1 + rowHead -1
            } else {
                region1 = arrayList.get(i) + rowHead - 1;//代表数据中的第一个为Account Name
                region2 = newData.length + rowHead - 1;//首先得到所有数据的行值，然后再加上行头，-1之后就得到表格的最后一行
            }
            if (region1 == region2) {
                continue;
            } else sheet.addMergedRegion(new CellRangeAddress(region1, region2, col, col));//将单元格进行合并
        }
    }

    /***
     * @param newData 一个二维数组数据源
     * @param workbook 公共的工作簿对象
     * @param sheet 工作表对象
     * @param rowHead 行头
     * @param columnHead 列头
     */
    public static void setFontBold(Object[][] newData,HSSFWorkbook workbook,HSSFSheet sheet,int rowHead,int columnHead)  {
        HSSFFont font = workbook.createFont(); //设置一个新的字体对象
        HSSFCellStyle headStyle = workbook.createCellStyle();//新建样式对象
        HSSFCellStyle defaultStyle = workbook.createCellStyle();
        //XSSFColor color = new XSSFColor(Hex.decodeHex("#C0C0C0"),null);
        //由于POI对于工作簿本身的样式对象是全局的，所以需要新建两个样式对象，对不同格式的单元格分别进行设置
        ArrayList<Integer> list = new ArrayList();//使用集合来存放字体加粗的行数
        for (int k = 0; k < newData.length; k++) {//遍历二维数组中的第一维度（行数）
            for (int a = 0; a < 3; a++) {//我们的表格中的SHEET最多前面三列是文本数据，所以需要单独设置
                if (newData[k][a].toString().contains("总计") || newData[k][a].toString().contains("Total")) {//二维数组第k行的第a个数据中包含有总计和Total的才需要加粗
                    list.add(a);//将列数添加到集合中
                    int boldRow = k + rowHead;//将二维数组中的位置转换为表格中要加粗的行
                    int boldColumn = a + columnHead;//同上，改列
                    HSSFRow row = sheet.getRow(boldRow);//获取这一行的row对象
                    HSSFCell cell = row.getCell(boldColumn);//获取单个cell单元格对象
                    headStyle.cloneStyleFrom(cell.getCellStyle());//将单元格格式复制到新的样式对象中，这样子也不会改变原有的样式
                    font.setFontName("微软雅黑");//设置字体为微软雅黑
                    font.setBold(true);//设置字体加粗
                    headStyle.setFont(font);//将字体对象传递给样式对象中
                    cell.setCellStyle(headStyle);//设置新的样式对象
                }
            }
        }
        for (int k = 0; k < newData.length; k++) {//遍历二维数组中的第一维度（行数）
            for (int a=0;a<list.size();a++){//遍历集合，将集合中的行数拿出来
                if (newData[k][list.get(a)].toString().contains("总计") || newData[k][list.get(a)].toString().contains("Total")){
                    for (int j = 3; j < newData[0].length; j++) {//遍历剩下来的数值数据
                        int boldRow = k + rowHead;
                        int boldColumn = j + columnHead;
                        HSSFRow row = sheet.getRow(boldRow);
                        HSSFCell cell = row.getCell(boldColumn);
                        defaultStyle.cloneStyleFrom(cell.getCellStyle());
                        font.setFontName("微软雅黑");
                        font.setBold(true);
                        defaultStyle.setFont(font);
                        cell.setCellStyle(defaultStyle);
                    }
                }
            }
        }
    }
}