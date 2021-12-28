package JsonJava;

import JsonJava.Utils.DB;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OneJsonToJavaApp {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        // DB section: part 1/2
        DB db = new DB("jdbc:mysql://localhost:3306/Business", "JavaAllPermissions", "admin");

        // Queries
        ResultSet resultSetCustomerInfo = db.query("select * from CustomerInfo where Location = 'Asia';");
        ResultSet resultSetCustomerInfoTest = db.query("select * from CustomerInfoTest where Location = 'Asia';");

        // JSON
        OneJsonToJava oneJsonToJava = new OneJsonToJava();
        oneJsonToJava.clearPrevResults();
        oneJsonToJava.customerDetailsJson(resultSetCustomerInfo);
        oneJsonToJava.customerDetailsTestJson(resultSetCustomerInfoTest);


        // DB section: part 2/2
        db.closeConnection();
    }
}
