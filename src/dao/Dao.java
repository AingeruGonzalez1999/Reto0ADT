package dao;

import clases.Cliente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private ResourceBundle rb = ResourceBundle.getBundle("config.config");
    private String setCustomer = "INSERT INTO customer (firstName, lastName, middleInitial, street, city, state, email, zip, phone) VALUES (?,?,?,?,?,?,?,?,?)";

    private void openConnection() {
        try {
            Class.forName(rb.getString("Driver"));
            Properties connectionProps = new Properties();
            connectionProps.put("user", rb.getString("DBUser"));
            connectionProps.put("password", rb.getString("DBPass"));
            this.con = DriverManager.getConnection(rb.getString("Conn"), connectionProps);
        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void closeConnection() throws SQLException {
        con.close();
    }

    public Cliente setCustomer(Cliente c) {
        try {

            this.openConnection();
            PreparedStatement ps = con.prepareStatement(setCustomer);
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getApellido());
            ps.setString(3, c.getInicial());
            ps.setString(4, c.getCalle());
            ps.setString(5, c.getCiudad());
            ps.setString(6, c.getProvincia());
            ps.setString(7, c.getEmail());
            ps.setInt(8, c.getCodigoPostal());
            ps.setLong(9, c.getTelefono());
            ps.execute();
            ps.close();
            this.closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    public Cliente getCustomers(String n) {
        Cliente c = null;
        try {
            this.openConnection();

            String getCustomers = "SELECT * FROM customer WHERE firstName LIKE '" + n + "'";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(getCustomers);
            while (rs.next()) {
                c = new Cliente();
                c.setNombre(rs.getString("firstName"));
                c.setApellido(rs.getString("lastName"));
                c.setInicial(rs.getString("middleInitial"));
                c.setCalle(rs.getString("street"));
                c.setCiudad(rs.getString("city"));
                c.setProvincia(rs.getString("state"));
                c.setEmail(rs.getString("email"));
                c.setCodigoPostal(rs.getInt("zip"));
                c.setTelefono(rs.getLong("phone"));
                System.out.println(c.toString());
            }
            rs.close();
            stmt.close();
            this.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return c;
    }
}
