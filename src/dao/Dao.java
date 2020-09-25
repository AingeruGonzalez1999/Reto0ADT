package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author gsalg
 */
public class Dao {
    private Connection con;
    private Statement stmt;
    private ResourceBundle configFile;
    private String driverBD;
    private String urlBD;
    private String userBD;
    private String passwordBD;
    
    public Dao(){
        this.configFile = ResourceBundle.getBundle("dao.config");
        this.driverBD = this.configFile.getString("Driver");
        this.urlBD = this.configFile.getString("Conn");
        this.userBD = this.configFile.getString("DBUser");
        this.passwordBD = this.configFile.getString("DBPass");
    }
    
    private void openConnection() throws Exception {
	try{
            Class.forName(this.driverBD);
            con=DriverManager.getConnection(urlBD, userBD, passwordBD);
        }catch(SQLException e){
            System.out.println("ERROR SQL");
        }
    }

    private void closeConnection() throws SQLException {
	stmt.close();
		con.close();
    }


}
