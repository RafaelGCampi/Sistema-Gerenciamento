package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBaseMySQL implements Database {

    @Override
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost/sistema-cadastro?useSSL=false", "root", "");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DataBaseMySQL.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
