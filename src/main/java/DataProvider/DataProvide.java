package DataProvider;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class DataProvide {
    @DataProvider(name = "simpleDataProvider")
    public Object[][] getData() throws IOException {

        // Open workbook
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream("src/main/java/DataProvider/Utils/DataProvider.xlsx"));
        // Selecting workbook sheet
        XSSFSheet workbookSheet = workbook.getSheetAt(0);
        // Counting rows on sheet
        int rowCount = workbookSheet.getPhysicalNumberOfRows();
        // Counting columns - row needed only for that
        XSSFRow row = workbookSheet.getRow(0);
        int columnCount = row.getLastCellNum();
        // Using formatter cause our test waiting for String three times
        DataFormatter dataFormatter = new DataFormatter();
        // Creating multi dimension array
        Object[][] data = new Object[rowCount - 1][columnCount];
        // Collecting values from cells using outer and inner loop
        for (int i = 1; i < rowCount; i++) {
            row = workbookSheet.getRow(i);
            for (int k = 0; k < columnCount; k++) {
                XSSFCell cell = row.getCell(k);
                data[i - 1][k] = dataFormatter.formatCellValue(cell);
            }
        }
        return data;
    }

    @Test(dataProvider = "simpleDataProvider")
    public void testSample(String firstString, String secondString, String Id) {
        System.out.println(firstString + secondString + Id);
    }
}
