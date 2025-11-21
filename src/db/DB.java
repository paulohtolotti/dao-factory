package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {

    public static Connection conn = null;

    public static Connection openConnection() throws DbException {

        if (conn == null) {
            try {
                Properties p = getProperties();
                String url = p.getProperty("dburl");
                conn = DriverManager.getConnection(url, p);


            } catch(SQLException err) {
                throw new DbException("Error while opening the connection: " + err.getMessage());
            }
        }
        return conn;
    }

    public static void closeConnection() throws DbException {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException err) {
                System.out.println("Error while closing the connection: " + err.getMessage());
            }
        }
    }

    public static void closeStatement(Statement s) throws DbException{
        if (s != null) {
            try {
                s.close();
            } catch(SQLException err) {
                throw new DbException(err.getMessage());
            }
        }
    }

    public void closeResultSet(ResultSet rs) throws DbException {
        if (rs != null) {
            try {
                rs.close();
            } catch(SQLException err) {
                throw new DbException(err.getMessage());
            }
        }
    }
    private static Properties getProperties() throws DbException{

        try(FileInputStream fs = new FileInputStream("db.properties")) {

            Properties p = new Properties();
            p.load(fs);
            return p;

        } catch(IOException err) {
           throw new DbException(err.getMessage());
        }
    }
}
