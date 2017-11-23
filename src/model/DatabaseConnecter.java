package model;

import java.sql.*;
import java.util.ArrayList;

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
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                    if (!resultSet.isBeforeFirst() ) {
                        System.out.println("No data");
//                        str = "0";
                    }
                    else{
                        str = resultSet.getString(1);
                    }

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

    public static ArrayList browseRequestForCar(String query) {
        Connection conn = null;
        ArrayList<ArrayList> rfc = new ArrayList<>();
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
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    String id = resultSet.getString("id");
                    System.out.println("id");
                    String causeofuse = resultSet.getString("causeofuse");
                    String start = resultSet.getString("startdate");
                    String end = resultSet.getString("enddate");
                    String provience = resultSet.getString("provience");
                    String distance = DatabaseConnecter.browserString("select distance from destination where provience='"+provience+"'");
                    String fuelCost = DatabaseConnecter.browserString("select fuelcost from destination where provience='"+provience+"'");
                    String user = resultSet.getString("user");
                    String driverID = DatabaseConnecter.browserString("select driverid from workassign where requestforcarid='"+id+"'");
                    String driverName = DatabaseConnecter.browserString("select name from user where username='"+driverID+"'");
                    String liscense = DatabaseConnecter.browserString("select liscenseplate from workassign where requestforcarid='"+id+"'");
                    String carBrand = DatabaseConnecter.browserString("select brand from car where liscenseplate='"+liscense+"'");
                    String carModel = DatabaseConnecter.browserString("select model from car where liscenseplate='"+liscense+"'");
                    String detail = resultSet.getString("detail");
                    ArrayList<String> data = new ArrayList<>();
                    data.add(id);
                    data.add(causeofuse);
                    data.add(start);
                    data.add(end);
                    data.add(provience);
                    data.add(distance);
                    data.add(fuelCost);
                    data.add(user);
                    data.add(driverName);
                    data.add(liscense);
                    data.add(carBrand);
                    data.add(carModel);
                    data.add(detail);
                    rfc.add(data);
                }

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
                    return rfc;
                }
            }
            catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return rfc;
    }
}
