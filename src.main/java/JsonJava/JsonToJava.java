package JsonJava;

import java.sql.*;

public class JsonToJava {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Business", "JavaAllPermissions", "admin");
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select * from CustomerInfo where Location = 'Asia' limit 1;");

        while (result.next()) {
            CustomerDetails customerDetails = new CustomerDetails(
                    result.getString(1), // courseName
                    result.getString(2), // purchaseDate
                    result.getInt(3), // amount
                    result.getString(4) // location
            );
        }

        connection.close();
    }
}
