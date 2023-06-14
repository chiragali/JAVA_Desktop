package com.por.expert.UtilitiesRepository;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {
    static String projectPath;
    static XSSFWorkbook workbook;
    static XSSFSheet sheet;

    //Constructor
    public void getExcelData(String excelPath, String sheetName) {
        try {

            workbook = new XSSFWorkbook (excelPath);
            sheet = workbook.getSheet(sheetName);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            e.printStackTrace();
        }
    }

    public static void ExcelData (String [] args) {
        getRowCount();
        getCellDataString(1,0);
        getCellDataNumeric(1,3);
    }

    public static void getRowCount() {

        try {

            int rowCount = sheet.getPhysicalNumberOfRows();
            System.out.println("number of rows " + rowCount);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            e.printStackTrace();
        }
    }

    public static String getCellDataString(int rowNum, int colNum) {

        String excelPathData = null;

        try {

            excelPathData = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
            System.out.println("" + excelPathData);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            e.printStackTrace();
        }
        return excelPathData;
    }

    public static Object getCellDataNumeric(int rowNum, int colNum) {

        Object excelPathNumeric = null;

        try {

            excelPathNumeric = sheet.getRow(rowNum).getCell(colNum).getNumericCellValue();
            System.out.println(""+excelPathNumeric);


        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            e.printStackTrace();
        }
        return excelPathNumeric;
    }
}
