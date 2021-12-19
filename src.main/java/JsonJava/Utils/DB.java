package JsonJava.Utils;

import java.sql.*;

public class DB {
    private final Connection connection;

    public DB(String url, String user, String password) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(url, user, password);
    }

    public ResultSet query(String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

}
