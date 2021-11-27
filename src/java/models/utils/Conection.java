/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Rodrigo
 */
public class Conection {
    
            public static Connection conectar() {
        Connection con = null;
 
        String url = "jdbc:postgresql://ec2-18-235-154-252.compute-1.amazonaws.com:5432/d16gum7i2mdqd8";
        String user = "imlkpprionecum";
        String password = "331982622e4a24c45d788044ad4fb20e8ee258d3c0583fc320d25a322dc31052";

        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        return con;
    }
    
    
}
