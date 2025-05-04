package co.ecubix.utils.excel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class AddDataIntoBusinessGeo {
	
	public static void appendOneRowToExistingExcel(String filePath, Object[] data) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0); // Assuming data is in first sheet
        fis.close();

        // Find the next empty row
        int lastRowNum = sheet.getLastRowNum();
        int newRowNum = lastRowNum + 1;
        XSSFRow row = sheet.createRow(newRowNum);
        row.createCell(0).setCellValue(2025);                  // Year
        row.createCell(1).setCellValue((String) data[1]);      // Geo Level Code
        row.createCell(2).setCellValue((String) data[2]);      // Geo Level Name
        row.createCell(3).setCellValue((String) data[3]);      // Parent Code
        row.createCell(4).setCellValue((String) data[4]);      // Parent Name
        row.createCell(5).setCellValue((String) data[5]);      // Delete (Y)

        // Save the file
        FileOutputStream fos = new FileOutputStream(filePath);
        workbook.write(fos);
        workbook.close();
        fos.close();
    }

}
