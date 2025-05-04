package co.ecubix.utils.excel;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelSheet {

	public void dataReading(String excelFilePath) throws IOException {
		FileInputStream fis = new FileInputStream(excelFilePath);
		// for .xlsx file extension
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Sheet1");

		// FOR LOOP
		int columns = sheet.getRow(1).getLastCellNum();

		XSSFRow row = sheet.getRow(1);

		for (int c = 0; c < columns; c++) {
			XSSFCell cell = row.getCell(c);
			switch (cell.getCellType()) {
			case STRING:
				System.out.println(cell.getStringCellValue());
				break;
			case NUMERIC:
				System.out.println(cell.getNumericCellValue());
				break;
			default:
				break;
			}
		}
		workbook.close();
	}

}
