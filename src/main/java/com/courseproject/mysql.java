//package com.courseproject;
//
//import java.sql.*;
//
//public class mysql {
//    private static String username = "root";
//    private static String password = "root";
//    private static String url = "jdbc:mysql//localhost:3306/courseproject";
//    private static Connection con;
//    private static Statement stmt;
//    private static ResultSet rs;
//
//    public Connection dbconnection()
//        throws  ClassNotFoundException, SQLException {
//
//        String query = "select count(*) from accounts";
//        Class.forName("com.mysql.jdbc.Driver");
//        con = DriverManager.getConnection(url, username, password);
//        return con;
//    }
//
//
//
//    public static void main(String[] args) {
////        connect();
//    }
//    public static void createUser(String login, String password, String name, String role, int salary) {
//
//    }
//    public static void checkLogin(String role, String login, String password) {
//
//    }
//
//}
