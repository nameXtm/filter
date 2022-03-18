package filter;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManger {
    //获取connection
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();


    //开启事务
    public static void beninTrans()  {
        Connection connection = threadLocal.get();//调用线程

        if (connection==null){
            connection = connectionUtil.getConnection();
            threadLocal.set(connection);//储存线程
            }
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    //提交事务
    public static void commit(){
        Connection connection = threadLocal.get();
        if (connection==null){
            connection = connectionUtil.getConnection();
            threadLocal.set(connection);
        }
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connectionUtil.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connectionUtil.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    //回滚事务
    public static void rollback() throws SQLException {
        Connection connection = threadLocal.get();
        if (connection==null){
            connection = connectionUtil.getConnection();
            threadLocal.set(connection);
        }
        connection.rollback();

    }


}
