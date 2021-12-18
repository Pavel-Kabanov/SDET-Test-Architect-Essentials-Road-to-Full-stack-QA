package JsonJava;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
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

        // Adding objects as JSON to an array
        for (CustomerDetails customerDetails : arrayList) {
            jsonArray.add(new Gson().toJson(customerDetails));
        }

        // Preparing JSON to a String
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", jsonArray);
        String unescapedString = StringEscapeUtils.unescapeJava(jsonObject.toString());
        // Fixing problem with extra quotes
        String unescapedStringQuotesFix = unescapedString
                .replace("\"{", "{")
                .replace("}\"", "}");

        // Writing to file section.
        // Clear folder with previous result
        FileUtils.cleanDirectory(new File("src.main/java/JsonJava/JsonResult"));
        // Writing
        try (FileWriter fileWriter = new FileWriter("src.main/java/JsonJava/JsonResult/transactions.json")) {
            fileWriter.write(unescapedStringQuotesFix);
        }

        connection.close();
    }
}
