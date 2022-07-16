package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=Assignment_PRJ";
        conn = DriverManager.getConnection(url, "sa", "tuan2502");
        return conn;
    }

    public static boolean checkString(String inputString, String pattern) {
        if (!inputString.matches(pattern)) {
            return false;
        }
        return true;
    }
}
