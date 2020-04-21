package com.test.demo.nospring;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author 金🗡
 * @date 2020/4/20 13:48
 * @description:
 */
public class HandlerExcel {
    static String filePath1 = "C:\\Users\\jinjian\\Desktop\\银泰店.xls";//数据源
    static String filePath2 = "C:\\Users\\jinjian\\Desktop\\总店.xls";//模板
    static String filePath3 = "C:\\Users\\jinjian\\Desktop\\目标\\银泰店.xls";//模板

    static int rowNum = 2;
    private static int count = 0;
    private static BigDecimal bigDecimal = BigDecimal.ZERO;

    public static void main(String[] args) throws IOException {
        readExcel(filePath1, filePath2, filePath3);

    }

    /**
     * @param filePath1 数据源
     * @param filePath2 模板
     * @param filePath2 输出位置
     * @throws IOException
     */
    static void readExcel(String filePath1, String filePath2, String filePath3) throws IOException {
        //定义工作簿
        Workbook read = getWorkbook(filePath1);//数据源
        Workbook write = getWorkbook(filePath2);//数据写入模板

        Sheet sheet = read.getSheetAt(0);
        int rows = sheet.getLastRowNum();
        for (int i = 2; i <= rows; i++) {
            Row row = sheet.getRow(i);
            if (i < rows) {

                System.out.println("处理行数：" + i);
                handlerRow(write, row);
            } else {
                //等于
                Row row1 = write.getSheetAt(0).createRow(rowNum);
                Cell cell3 = row1.createCell(3);
                cell3.setCellType(CellType.STRING);
                cell3.setCellValue(count);

                Cell cell7 = row1.createCell(7);
                cell7.setCellType(CellType.STRING);
                cell7.setCellValue(bigDecimal.toPlainString());


            }


        }


        System.out.println("生成总列数" + rowNum);
        File file = new File(filePath3);
        String fileName = file.getName();
        System.out.println(fileName);
        write.write(new FileOutputStream(file));

        write.close();

    }

    /**
     * @param workbook 模板数据
     * @param row      数据源 row
     */
    static void handlerRow(Workbook workbook, Row row) {
        Sheet sheet = workbook.getSheetAt(0);
        String[] codes = row.getCell(row.getLastCellNum() - 1).getStringCellValue().split(",");
        String[] sizes = row.getCell(8).getStringCellValue().split("-");
        for (int j = 0; j < codes.length; j++) {
            //创建单条记录
            Row row1 = sheet.createRow(rowNum);
            Cell temp = row1.createCell(0);
            temp.setCellValue(rowNum - 1);


            serCell(row1, row, 1);
            serCell(row1, row, 2);
            Cell cell3 = row1.createCell(3);
            cell3.setCellValue("1");
            count = count + 1;

            serCell(row1, row, 4);
            serCell(row1, row, 5);
            serCell(row1, row, 6);
            serCell(row1, row, 7);
            //总金额
            bigDecimal = bigDecimal.add(new BigDecimal(row.getCell(7).getStringCellValue()));

            Cell cell8 = row1.createCell(8);
            cell8.setCellValue(sizes[j]);
            Cell cell9 = row1.createCell(9);
            cell9.setCellValue(codes[j]);
            rowNum = rowNum + 1;

        }

    }

    static void serCell(Row row1, Row row, int num) {
        Cell cell4 = row1.createCell(num);
        row.getCell(num).setCellType(CellType.STRING);
        cell4.setCellValue(row.getCell(num).getStringCellValue());
    }


    /**
     * 获取Workbook
     *
     * @param filePath
     * @return
     */
    static Workbook getWorkbook(String filePath) {
        //定义工作簿
        Workbook xssfWorkbook = null;
        try {
            if (filePath.matches("^.+\\.(?i)(xls)$")) {
                xssfWorkbook = new HSSFWorkbook(new FileInputStream(filePath));
            } else {
                xssfWorkbook = new XSSFWorkbook(new FileInputStream(filePath));
            }
        } catch (Exception e) {
            System.out.println("Excel data file cannot be found!");
        }

        return xssfWorkbook;
    }

}
