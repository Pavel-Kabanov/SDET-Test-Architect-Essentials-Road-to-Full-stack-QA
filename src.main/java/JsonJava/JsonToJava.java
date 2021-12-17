package JsonJava;

import java.sql.*;

public class JsonToJava {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Business", "JavaAllPermissions", "admin");
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select * from CustomerInfo where Location = 'Asia' and PurchaseDate = curdate();");

        while (result.next()) {
            System.out.print(result.getString(1) + " ");
            System.out.print(result.getString(2) + " ");
            System.out.print(result.getInt(3) + " ");
            System.out.print(result.getString(4) + " ");
            System.out.println(" ");
        }

        connection.close();

    }
}
