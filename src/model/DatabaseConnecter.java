package model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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
                        str = "";
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
        if(query.equals("")) {return rfc;}
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
                    name += " "+DatabaseConnecter.browserString("select surname from user where username='"+user+"'");
                    String causeofuse = resultSet.getString("causeofuse");
                    String staus = resultSet.getString("staus");
                    String start = resultSet.getString("startdate");
                    String end = resultSet.getString("enddate");
                    String provience = resultSet.getString("provience");
                    String distance = DatabaseConnecter.browserString("select distance from destination where provience='"+provience+"'");
                    String fuelCost = DatabaseConnecter.browserString("select fuelcost from destination where provience='"+provience+"'");

                    String driverID = "-";
                    String driverName = "-";
                    String liscense = "-";
                    String carBrand = "-";
                    String carModel = "-";
                    String carType = "-";

                    if (staus.equals("อนุมัติแล้ว")||staus.equals("คืนแล้ว")){
                        driverID = DatabaseConnecter.browserString("select driverid from workassign where requestforcarid='"+id+"'");
                        driverName = DatabaseConnecter.browserString("select name from user where username='"+driverID+"'");
                        driverName += " "+DatabaseConnecter.browserString("select surname from user where username='"+driverID+"'");
                        liscense = DatabaseConnecter.browserString("select liscenseplate from workassign where requestforcarid='"+id+"'");
                        carBrand = DatabaseConnecter.browserString("select brand from car where liscenseplate='"+liscense+"'");
                        carModel = DatabaseConnecter.browserString("select model from car where liscenseplate='"+liscense+"'");
                        carType = DatabaseConnecter.browserString("select type from car where liscenseplate='"+liscense+"'");
                    }

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



    public static ArrayList<String> browseStringInArray(String query) {
        Connection conn = null;
        ArrayList<String> aStr = new ArrayList<>();
        try {
            // db parameters
            String url = "jdbc:sqlite:databaseFile.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
//            System.out.println("Connection to SQLite has been established.");
            if (conn != null) {
                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
//                System.out.println("Driver name: "+dm.getDriverName());
//                System.out.println("Product name: "+dm.getDatabaseProductName());
//                System.out.println("----------------DATA----------------");
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    aStr.add(resultSet.getString(1));
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
//                    System.out.println("closeDB");
                    return aStr;
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return aStr;
    }







    public static ArrayList browseUserData(String query) {
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
                    name += " "+DatabaseConnecter.browserString("select surname from user where username='"+user+"'");
                    String causeofuse = resultSet.getString("causeofuse");
                    String staus = resultSet.getString("staus");
                    String start = resultSet.getString("startdate");
                    String end = resultSet.getString("enddate");
                    String provience = resultSet.getString("provience");
                    String distance = DatabaseConnecter.browserString("select distance from destination where provience='"+provience+"'");
                    String fuelCost = DatabaseConnecter.browserString("select fuelcost from destination where provience='"+provience+"'");
                    String driverID = DatabaseConnecter.browserString("select driverid from workassign where requestforcarid='"+id+"'");
                    String driverName = DatabaseConnecter.browserString("select name from user where username='"+driverID+"'");
                    driverName += " "+DatabaseConnecter.browserString("select surname from user where username='"+driverID+"'");
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



    public static void insertStringByArray(ArrayList<String> data, String query) {
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
//                System.out.println("Insert :"+name);
                PreparedStatement pstmt = conn.prepareStatement(query);
                for (int i=0;i<data.size();i++){
                    pstmt.setString(i+1,data.get(i));
                }
//                pstmt.setString(1,data.get(0));
//                pstmt.setString(2,data.get(1));
//                pstmt.setString(3,data.get(2));
//                pstmt.setString(4,data.get(3));
//                pstmt.setString(5,data.get(4));
//                pstmt.setString(6,data.get(5));
//                pstmt.setString(7,data.get(6));
                pstmt.executeUpdate();
                System.out.println("Insert finish");
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

    public static ArrayList browseCar(String query) {
        Connection conn = null;
        ArrayList<ArrayList> cars = new ArrayList<>();
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
                    String type = resultSet.getString("type");
                    String liscense = resultSet.getString("liscenseplate");
                    String brand = resultSet.getString("brand");
                    String gen = resultSet.getString("model");
                    String totalMission = resultSet.getString("totalmission");
                    String totalDistance = resultSet.getString("totaldistance");
                    ArrayList<String> data = new ArrayList<>();
//                    data.add(type);
//                    data.add(liscense);
//                    data.add(brand);
//                    data.add(gen);
//                    data.add(totalMission);
//                    data.add(totalDistance);
                    Collections.addAll(data,type,liscense,brand,gen,totalMission,totalDistance);
                    cars.add(data);
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
                    return cars;
                }
            }
            catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return cars;
    }

    public static ArrayList browseRfcIDByDate(LocalDate startR,LocalDate endR,String s) {
        Connection conn = null;
        ArrayList<String> unUseAbleId = new ArrayList<>();
        ArrayList<ArrayList> allId = DatabaseConnecter.browseAllIDAndDate(s);
        int startRequest = startR.getDayOfYear();
        int endRequest = endR.getDayOfYear();
        System.out.println("startK = "+startRequest);
        System.out.println("endK = "+endRequest);

        for (ArrayList<String> i : allId){
            String id = i.get(0);
            LocalDate startRfc = LocalDate.parse(i.get(1));
            LocalDate endRfc = LocalDate.parse(i.get(2));
            int startRfcDayOfY = startRfc.getDayOfYear();
            int endRfcDayOfY = endRfc.getDayOfYear();
            if (startRequest <= startRfcDayOfY && startRequest <= endRfcDayOfY){
                if (endRfcDayOfY<=endRequest){
                    unUseAbleId.add(id);
                }
            }


        }
        return unUseAbleId;
    }

    private static ArrayList browseAllIDAndDate(String s) {
        Connection conn = null;
        ArrayList<ArrayList> rfc = new ArrayList<>();
        String query = "select id, startdate,enddate FROM requestforcar " + s;
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
                    String start = resultSet.getString("startdate");
                    String end = resultSet.getString("enddate");
                    ArrayList<String> data = new ArrayList<>();
                    Collections.addAll(data,id,start,end);
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




    public static ArrayList<String[]> browseCarInArray(String query) {
        Connection conn = null;
        ArrayList<String[]> aStr = new ArrayList<>();
        try {
            // db parameters
            String url = "jdbc:sqlite:databaseFile.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
//            System.out.println("Connection to SQLite has been established.");
            if (conn != null) {
                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
//                System.out.println("Driver name: "+dm.getDriverName());
//                System.out.println("Product name: "+dm.getDatabaseProductName());
//                System.out.println("----------------DATA----------------");
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    String[] data = {resultSet.getString("liscenseplate"),resultSet.getString("brand"),resultSet.getString("model"),resultSet.getString("type")};
                    aStr.add(data);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
//                    System.out.println("closeDB");
                    return aStr;
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return aStr;
    }

    public static ArrayList<String[]> browseDriverInArray(String query) {
        Connection conn = null;
        ArrayList<String[]> aStr = new ArrayList<>();
        try {
            // db parameters
            String url = "jdbc:sqlite:databaseFile.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
//            System.out.println("Connection to SQLite has been established.");
            if (conn != null) {
                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
//                System.out.println("Driver name: "+dm.getDriverName());
//                System.out.println("Product name: "+dm.getDatabaseProductName());
//                System.out.println("----------------DATA----------------");
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    String[] data = {resultSet.getString("id"),DatabaseConnecter.browserString("select name from user where username='"+resultSet.getString("id")+"'")+"  "+DatabaseConnecter.browserString("select surname from user where username='"+resultSet.getString("id")+"'")};
                    aStr.add(data);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
//                    System.out.println("closeDB");
                    return aStr;
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return aStr;
    }


}
