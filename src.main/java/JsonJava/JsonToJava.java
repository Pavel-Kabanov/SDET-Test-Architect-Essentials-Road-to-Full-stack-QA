package JsonJava;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class JsonToJava {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Business", "JavaAllPermissions", "admin");
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select * from CustomerInfo where Location = 'Asia' limit 1;");

        CustomerDetails customerDetails = new CustomerDetails();

        while (result.next()) {
            customerDetails.setCourseName(result.getString(1));
            customerDetails.setPurchaseDate(result.getString(2));
            customerDetails.setAmount(result.getInt(3));
            customerDetails.setLocation(result.getString(4));
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("src.main/java/Jsons/customerInfo.json"), customerDetails);

        connection.close();
    }
}
