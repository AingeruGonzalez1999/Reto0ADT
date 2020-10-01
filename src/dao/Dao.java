package dao;

import clases.Cliente;
import clases.Cuenta;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    private Connection con = null;
    private PreparedStatement stat = null;
    private ResourceBundle configFile = null;
    private String driverBD = "";
    private String urlBD = "";
    private String userBD = "";
    private String passwordBD = "";

    private final String setCustomer = "INSERT INTO customer (firstName, lastName, middleInitial, street, city, state, email, zip, phone) VALUES (?,?,?,?,?,?,?,?,?)";
    private final String getCustomers = "SELECT * FROM customer WHERE firstName LIKE ?";
    private final String getCustomerAccount = "SELECT * FROM account a, customer c, customer_account ca where a.id=ca.accounts_id and c.id=ca.customers_id and firstName = ? and lastName = ?";
    private final String setAccount = "INSERT INTO account (balance,beginBalance,beginBalanceTimestamp,creditLine,description,type) VALUES (?,?,?,?,?,?)";
    public Dao() {
        this.configFile = ResourceBundle.getBundle("config.config");
        this.driverBD = this.configFile.getString("Driver");
        this.urlBD = this.configFile.getString("Conn");
        this.userBD = this.configFile.getString("DBUser");
        this.passwordBD = this.configFile.getString("DBPass");
    }

    private void openConnection() {
        try {
            Class.forName(this.driverBD);
            con = DriverManager.getConnection(this.urlBD, this.userBD, this.passwordBD);
        } catch (SQLException e) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, e);
        } catch (ClassNotFoundException e1) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, e1);
        }
    }

    private void closeConnection() {
        try {
            if (stat != null) {
                stat.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("ERROR CERRANDO CONEXION");
        }
    }

    public Cliente setCustomer(Cliente c) {

        this.openConnection();

        try {

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

        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.closeConnection();

        return c;
    }

    public Cliente getCustomers(String n) {

        Cliente c = null;

        this.openConnection();

        try {

            PreparedStatement ps = con.prepareStatement(getCustomers);
            ps.setString(1, n);
            ResultSet rs = ps.executeQuery();

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

            ps.execute();
            rs.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.closeConnection();

        return c;
    }

    public Cuenta getCuentaCliente(String n, String a) {

        Cuenta c = null;

        this.openConnection();

        try {

            PreparedStatement ps = con.prepareStatement(getCustomerAccount);
            ps.setString(1, n);
            ps.setString(2, a);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                c = new Cuenta();
                c.setIdCuenta(rs.getLong("id"));
                c.setBalance(rs.getDouble("balance"));
                c.setBalanceInicial(rs.getDouble("beginBalance"));
                c.setBalanceInicialFecha(rs.getTimestamp("beginBalanceTimestamp"));
                c.setLineaCredito(rs.getDouble("creditLine"));
                c.setDescripcion(rs.getString("description"));
                c.setTipo(rs.getInt("type"));
                System.out.println(c.toString());
            }

            ps.execute();
            rs.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.closeConnection();

        return c;
    }
    
    public Cuenta setAccount(Cuenta c) {
       this.openConnection();

        try {

            PreparedStatement ps = con.prepareStatement(setAccount);
            ps.setDouble(1, c.getBalance());
            ps.setDouble(2, c.getBalanceInicial());
            ps.setTimestamp(3, c.getBalanceInicialFecha());
            ps.setDouble(4, c.getLineaCredito());
            ps.setString(5, c.getDescripcion());
            ps.setInt(6, c.getTipo());

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.closeConnection();

        return c;
    }
}
