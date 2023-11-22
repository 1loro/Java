/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author lorem
 */
public class conexion {
    Connection con;
    public Connection getConnection(){
        String url="jdbc:mysql://localhost:3306/gymdb";
        String user="root";
        String pass="";
        
        try{
            con=DriverManager.getConnection(url,user,pass);
        }catch (Exception e){
            
        }
        return con;
    }  
}
