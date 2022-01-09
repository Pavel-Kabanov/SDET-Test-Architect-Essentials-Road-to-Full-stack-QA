package DataProvider;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProvide {
    @DataProvider(name = "simpleDataProvider")
    public Object[][] getData() {
        Object[][] data = {
                {"first", "first", 1},
                {"second", "second", 2},
                {"third", "third", 3}
        };
        return data;
    }

    @Test(dataProvider = "simpleDataProvider")
    public void testSample(String firstString, String secondString, int myInt) {
        System.out.println(firstString + secondString + myInt);
    }
}
