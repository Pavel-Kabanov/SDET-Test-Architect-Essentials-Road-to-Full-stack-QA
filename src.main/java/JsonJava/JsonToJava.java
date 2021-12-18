package JsonJava;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class JsonToJava {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        // DB section
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Business", "JavaAllPermissions", "admin");
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select * from CustomerInfo where Location = 'Asia';");

        ArrayList<CustomerDetails> arrayList = new ArrayList<>();
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
        FileUtils.cleanDirectory(new File("src.main/java/JsonJava/Jsons"));
        // TODO still didn't understand why we can't write to only one file
        for (int i = 0; i < arrayList.size(); i++) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File("src.main/java/JsonJava/Jsons/customerInfo_" + i + ".json"), arrayList.get(i));
        }

        connection.close();
    }
}
