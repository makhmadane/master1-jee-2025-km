package src.main.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {

    private  String url="jdbc:postgresql://localhost:5432/master1_jee" ;
    private String user = "postgres";
    private String password = "passer";


    public Connection getConnection(){
        Connection connection = null;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url,user,password);
            System.out.println("SUCCESS");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("FAILED");
            throw new RuntimeException(e);
        }
        return  connection;
    }
}

