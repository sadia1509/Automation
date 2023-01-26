package org.data;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.report.Log;


public class ExcelDataGetter {

    private Sheet xlSheet;
    private Map<String, Integer> columns = new HashMap<>();

    // Constructor(s)
    public ExcelDataGetter(String fileName, String sheetName ) {
        try {
            dataFromExcel( fileName, sheetName);
            Log.logger("Getting the data from excel");

        } catch (Exception e) {
            Log.logger("please check the file directory path or file name or sheet");
        }
    }



    // Here the data will be taken
    private void  dataFromExcel( String fileName, String sheetName) throws IOException {

        //Create an object of InputStream class to read excel file
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);

        Workbook sadiaWorkbook = null;

        //Find the file extension by splitting file name in substring  and getting only extension name
        String fileExtensionName = fileName.substring(fileName.indexOf("."));

        //Check condition if the file is xlsx file
        if (fileExtensionName.equals(".xlsx"))
            sadiaWorkbook = new XSSFWorkbook(inputStream);

            //Check condition if the file is xls file
        else if (fileExtensionName.equals(".xls"))
            sadiaWorkbook = new HSSFWorkbook(inputStream);


        //Read sheet inside the workbook by its name
        Sheet xlSheetLocal = sadiaWorkbook.getSheet(sheetName);

        xlSheet=  xlSheetLocal;

        xlSheet.getRow(0).forEach(cell ->{
            columns.put(cell.getStringCellValue(), cell.getColumnIndex());
        });

    }

    // Return the Excel row count
    public int totalRowCount(){
      return   xlSheet.getLastRowNum() - xlSheet.getFirstRowNum();
    }

    // Here we get the nth row and nth column based data
    public String dataFromRowColumn(int rowNum, int columnNum ){
        //Find number of rows in excel file
        int rowCount = xlSheet.getLastRowNum() - xlSheet.getFirstRowNum();

        if (rowCount == 0) return "No data found";
        else {
            DataFormatter formatter = new DataFormatter();
            // for getting a specific value
            return formatter.formatCellValue(xlSheet.getRow(rowNum).getCell(columnNum));
        }
    }

    public String getCellData(int rownum, String columnName )  {
        return dataFromRowColumn(rownum, columns.get(columnName));
    }



}
