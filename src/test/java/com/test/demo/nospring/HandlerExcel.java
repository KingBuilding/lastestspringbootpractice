package com.test.demo.nospring;

import lombok.Data;
import lombok.Getter;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author 金🗡
 * @date 2020/4/20 13:48
 * @description:
 */
public class HandlerExcel {
    static String filePath1 = "C:\\Users\\jinjian\\Desktop\\宁海杜晓娟.xls";//数据源
    static String filePath2 = "C:\\Users\\jinjian\\Desktop\\1.xls";//模板
    static String filePath3 = "C:\\Users\\jinjian\\Desktop\\目标\\";//输出位置


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
        int sheets = read.getNumberOfSheets();


        for (int n = 0; n < sheets; n++) {
            Sheet sheet = read.getSheetAt(n);
            int rows = sheet.getLastRowNum();//总列数
            System.out.println("处理的sheet是：" + sheet.getSheetName() + "；总行数：" + sheet.getLastRowNum());
            Sheet writeSheet;
            if (write.getNumberOfSheets() <= n) {//说明已有的sheet小于要创建的
                writeSheet = write.cloneSheet(n - 1);
            } else {
                writeSheet = write.getSheetAt(n);
            }
            write.setSheetName(n, sheet.getSheetName());
            int count = 0;
            int rowNum = 0;


            BigDecimal bigDecimal = new BigDecimal("0");//数量

            for (int i = 4; i <= rows; i++) {//从第四列开始
                Row row = sheet.getRow(i);
                System.out.println("处理的sheet是：" + sheet.getSheetName() + ";");
                rowNum = i;//处理的行数
                if (i < rows) {
                    if (Objects.nonNull(row)) {
                        System.out.println("处理行数：" + i);
                        ReturnMessage returnMessage = handlerRow(write, row, writeSheet, count, rowNum, bigDecimal);
                        count=returnMessage.getCount();
                        bigDecimal=returnMessage.getBigDecimal();
                    } else {
                        continue;
                    }

                } else {
                    //等于
                    Row row1 = write.getSheetAt(n).createRow(count + 1);
                    Cell cell3 = row1.createCell(3);
                    cell3.setCellType(CellType.STRING);
                    cell3.setCellValue(count);

                    Cell cell7 = row1.createCell(7);
                    cell7.setCellType(CellType.STRING);
                    cell7.setCellValue(bigDecimal.toPlainString());
                }
            }
            System.out.println("生成总列数" + rowNum);

        }


        String ccc[] = filePath1.split("\\\\");
        System.out.println("输出文件位置：" + filePath3 + ccc[ccc.length - 1]);
        File file = new File(filePath3 + ccc[ccc.length - 1]);
        String fileName = file.getName();
        System.out.println(fileName);
        write.write(new FileOutputStream(file));

        write.close();

    }

    /**
     * @param workbook 模板数据
     * @param row      数据源 row
     */
    static ReturnMessage handlerRow(Workbook workbook, Row row, Sheet sheet, int count, int rowNum, BigDecimal bigDecimal) {
        ReturnMessage returnMessage=new ReturnMessage();
        row.getCell(row.getLastCellNum() - 1).setCellType(CellType.STRING);
        String[] codes = row.getCell(row.getLastCellNum() - 1).getStringCellValue().split(",");
        String[] sizes = row.getCell(9).getStringCellValue().split("-");
        for (int j = 0; j < codes.length; j++) {
            //创建单条记录
            Row row1 = sheet.createRow(count + 2);//从第二列开始写
            Cell temp = row1.createCell(0);
            temp.setCellValue(1 + count++);


            serCell(row1, row, 1, 1);
            serCell(row1, row, 2, 2);
            Cell cell3 = row1.createCell(3);

            row.getCell(3).setCellType(CellType.STRING);
            String counts = row.getCell(3).getStringCellValue();
            int number = Integer.valueOf(counts) / codes.length;
            cell3.setCellValue(number);//数量


            serCell(row1, row, 4, 4);//单价
            serCell(row1, row, 6, 5);//折扣
            String singleMoney = serCell(row1, row, 7, 6);//折后单价
            Cell cell4 = row1.createCell(7);

            row.getCell(8).setCellType(CellType.STRING);
            BigDecimal bigDecimal1 = new BigDecimal(singleMoney).multiply(new BigDecimal(number));
            cell4.setCellValue(bigDecimal1.toPlainString());//折扣价等于折扣价乘以数量


            bigDecimal = bigDecimal.add(bigDecimal1);//计算折后价总金额

            Cell cell8 = row1.createCell(8);//尺寸
            cell8.setCellValue(sizes[j]);//
            Cell cell9 = row1.createCell(9);//码
            cell9.setCellValue(codes[j]);
            rowNum = rowNum + 1;

        }
        returnMessage.setBigDecimal(bigDecimal);
        returnMessage.setCount(count);
        return returnMessage;
    }

    /**
     * @param row1
     * @param row
     * @param num     原始cell 位置
     * @param cellNum 需要创建的位置
     */
    static String serCell(Row row1, Row row, int num, int cellNum) {
        Cell cell4 = row1.createCell(cellNum);

        row.getCell(num).setCellType(CellType.STRING);
        cell4.setCellValue(row.getCell(num).getStringCellValue());
        return row.getCell(num).getStringCellValue();
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


    @Data
    public static class ReturnMessage {


        private BigDecimal bigDecimal;
        private int count;

    }

}
