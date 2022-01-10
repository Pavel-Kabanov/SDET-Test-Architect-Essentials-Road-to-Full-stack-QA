package DataProvider;

import javafx.scene.input.DataFormat;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DataProvide {
    @DataProvider(name = "simpleDataProvider")
    public Object[][] getData() throws IOException {
//        Object[][] data = {
//                {"first", "first", "1"},
//                {"second", "second", "2"},
//                {"third", "third", "3"}
//        };
//        return data;
        FileInputStream inputFile = new FileInputStream("src/main/java/DataProvider/Utils/DataProvider.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(inputFile);
        XSSFSheet workbookSheet = workbook.getSheetAt(0);
        int rowCount = workbookSheet.getPhysicalNumberOfRows();
        XSSFRow row = workbookSheet.getRow(0);
        int columnCount = row.getLastCellNum();

        DataFormatter dataFormatter = new DataFormatter();

        Object[][] data = new Object[rowCount - 1][columnCount];

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
