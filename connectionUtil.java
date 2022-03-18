package filter;

import util.JDBCutils;

import java.sql.Connection;
import java.sql.SQLException;

public class connectionUtil {
    public static Connection getConnection(){
        JDBCutils jdbCutils = new JDBCutils();
        try {
            Connection connection3 = JDBCutils.getConnection3();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }
    public static  Connection closeConnection() throws SQLException {
        Connection connection3 = JDBCutils.getConnection3();
        if (connection3 == null) {
            new RuntimeException("connection3为null");
        }
        connection3.close();

        return connection3;
    }
}
