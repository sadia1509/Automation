package org.bjit.sqa;

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


public class ExcelDataGetter {

    private Sheet xlSheet;
    private String fileName;
    private Workbook sadiaWorkbook;
    private Map<String, Integer> columns = new HashMap<>();

    // Constructor(s)
    public ExcelDataGetter( ) {
        exelDataGetter();
    }

    // Defining the path, file and sheet
    public  void exelDataGetter(  ) {
        System.out.println("Getting the data from excel");
        try {
           dataFromExcel("F:\\Learning\\maven\\files", "a04.xlsx", "check");

        } catch (Exception e) {
            System.out.println("please check the file directory path or file name or sheet");
        }
    }

    // Here the data will be taken
    private void  dataFromExcel(String filePath, String fileName, String sheetName) throws IOException {
        this.fileName = fileName;
        //Create an object of File class to open xlsx file
      //  File file = new File(filePath + "\\" + fileName);


        //Create an object of FileInputStream class to read excel file
       // FileInputStream inputStream = new FileInputStream(file);

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


        this.sadiaWorkbook=sadiaWorkbook;


        //Read sheet inside the workbook by its name
        Sheet xlSheetLocal = sadiaWorkbook.getSheet(sheetName);


        xlSheet=  xlSheetLocal;

        xlSheet.getRow(0).forEach(cell ->{
            columns.put(cell.getStringCellValue(), cell.getColumnIndex());
        });


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


    // Write the email addresses for 3 rows
    public  void  updateTheMails( ){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMddHHmmss");
        for (int i =1; i<=3; i++) {
            LocalDateTime now = LocalDateTime.now();
            Cell cell =xlSheet.getRow(i).getCell(2);
            cell.setCellType(CellType.STRING);
            cell.setCellValue("sadia"+dtf.format(now)+i+"@gmail.com");  //.setCellValue();
            System.out.println(i+":"+cell);
        }
        try {
            System.out.println(fileName + " "+ sadiaWorkbook);
            //Crating output stream and writing the updated workbook
            FileOutputStream os = new FileOutputStream(fileName);
            sadiaWorkbook.write(os);

            //Close the workbook and output stream
            sadiaWorkbook.close();
            os.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
