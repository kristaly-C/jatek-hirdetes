package iit.unimiskolc.repository;


import iit.unimiskolc.domain.interfaces.Database;

import java.sql.*;


public class DatabaseSteam implements Database {
    private String url = "jdbc:mysql://localhost:3306/humblegames";
    private String username = "root";
    private String password = "";
    private Connection connection;

    public DatabaseSteam() { }

    public DatabaseSteam(String url, String username, String passwd){
        this.url = url;
        this.username = username;
        this.password = passwd;
    }

    public void connectDatabase(){
       try {
           connection = DriverManager.getConnection(url,username,password);
           System.out.println("Connected to the database");
       }catch (SQLException e){
           System.out.println("Oops, ERROR!");
           e.printStackTrace();
       }
    }

    public void readGames(String sql){
     try {
         Statement stmt = connection.createStatement();
         ResultSet rs= stmt.executeQuery(sql);
         while(rs.next()){
             System.out.println(rs.getInt(1) + " "
                     + rs.getString(2)+ " "
                     + rs.getString(3) + " "
                     + rs.getString(4) + " "
                     + rs.getString(5)+ " "
                     + rs.getInt(6) + " "
                     + rs.getInt(7)+ " "
                     + rs.getInt(8));
         }
     } catch (SQLException e) {
         e.printStackTrace();
     }
    }

    public void disconnectDatabase(){
        try {
            connection.close();
            System.out.println("Disconnected database");
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
