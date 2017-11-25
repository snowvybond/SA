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
//            System.out.println("Connection to SQLite has been established.");
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
//            System.out.println("Connection to SQLite has been established.");
            if (conn != null){
                DatabaseMetaData dm = (DatabaseMetaData)conn.getMetaData();
//                System.out.println("Driver name: "+dm.getDriverName());
//                System.out.println("Product name: "+dm.getDatabaseProductName());
//                System.out.println("----------------DATA----------------");
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    String id = resultSet.getString("id");
                    String user = resultSet.getString("user");
                    String name = DatabaseConnecter.browserString("select name from user where username='"+user+"'");
                    String causeofuse = resultSet.getString("causeofuse");
                    String staus = resultSet.getString("staus");
                    String start = resultSet.getString("startdate");
                    String end = resultSet.getString("enddate");
                    String provience = resultSet.getString("provience");
                    String distance = DatabaseConnecter.browserString("select distance from destination where provience='"+provience+"'");
                    String fuelCost = DatabaseConnecter.browserString("select fuelcost from destination where provience='"+provience+"'");
                    String driverID = DatabaseConnecter.browserString("select driverid from workassign where requestforcarid='"+id+"'");
                    String driverName = DatabaseConnecter.browserString("select name from user where username='"+driverID+"'");
                    String liscense = DatabaseConnecter.browserString("select liscenseplate from workassign where requestforcarid='"+id+"'");
                    String carBrand = DatabaseConnecter.browserString("select brand from car where liscenseplate='"+liscense+"'");
                    String carModel = DatabaseConnecter.browserString("select model from car where liscenseplate='"+liscense+"'");
                    String carType = DatabaseConnecter.browserString("select type from car where liscenseplate='"+liscense+"'");
                    String detail = resultSet.getString("detail");
                    ArrayList<String> data = new ArrayList<>();
                    data.add(id); //0
                    data.add(name); //1
                    data.add(causeofuse); //2
                    data.add(staus);//3
                    data.add(start);//4
                    data.add(end);//5
                    data.add(provience);//6
                    data.add(distance);//7
                    data.add(fuelCost);//8
                    data.add(driverID);//9
                    data.add(driverName);//10
                    data.add(liscense);//11
                    data.add(carType);//12
                    data.add(carBrand);//13
                    data.add(carModel);//14
                    data.add(detail);//15
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


    public static void updateString(String query) {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:databaseFile.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
//            System.out.println("Connection to SQLite has been established.");
            if (conn != null){
                DatabaseMetaData dm = (DatabaseMetaData)conn.getMetaData();
//                System.out.println("Driver name: "+ dm.getDriverName());
//                System.out.println("Product name: "+dm.getDatabaseProductName());
                Statement statement = conn.createStatement();
                statement.executeUpdate(query);
//                System.out.println("Insert finish");
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
                }
            }
            catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
