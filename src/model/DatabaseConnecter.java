package model;

import java.sql.*;

public class DatabaseConnecter {

    public static String browserString(String query) {
        Connection conn = null;
        String str = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:databaseFile.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
            if (conn != null){
                DatabaseMetaData dm = (DatabaseMetaData)conn.getMetaData();
//                System.out.println("Driver name: "+dm.getDriverName());
//                System.out.println("Product name: "+dm.getDatabaseProductName());
//                System.out.println("----------------DATA----------------");
                query = "select username from user";
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                str = resultSet.getString(1);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (conn != null) {
                    conn.close();
//                    System.out.println("closeDB");
                    return str;
                }
            }
            catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return str;
    }
}
