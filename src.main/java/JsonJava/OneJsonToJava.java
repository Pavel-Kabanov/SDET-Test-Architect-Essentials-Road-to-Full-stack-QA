package JsonJava;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class OneJsonToJava {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        // DB section
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Business", "JavaAllPermissions", "admin");
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select * from CustomerInfo where Location = 'Asia';");

        // Variables
        JSONArray jsonArray = new JSONArray();
        ArrayList<CustomerDetails> arrayList = new ArrayList<>();

        // Looking through results
        while (result.next()) {
            arrayList.add(new CustomerDetails(
                    result.getString(1), // courseName
                    result.getString(2), // purchaseDate
                    result.getInt(3), // amount
                    result.getString(4) // location
            ));
        }

        // Writing to files section.
        // Clear folder with previous results
        FileUtils.cleanDirectory(new File("src.main/java/JsonJava/JsonResult"));

        for (CustomerDetails customerDetails : arrayList) {
            jsonArray.add(new Gson().toJson(customerDetails));
        }

        new JSONObject().put("data", jsonArray);


        connection.close();
    }
}
