package dao;

import clases.Cliente;
import clases.Cuenta;
import clases.Movimiento;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Esta clase controla las entradas y salidas de datos de la BD
 */
public class DaoImplementation implements Dao {

    private Connection con = null;
    private PreparedStatement stat = null;
    private ResourceBundle configFile = null;
    private String driverBD = "";
    private String urlBD = "";
    private String userBD = "";
    private String passwordBD = "";

    /**
     * Querys de BBDD
     */
    private final String setCustomer = "INSERT INTO customer (firstName, lastName, middleInitial, street, city, state, email, zip, phone) VALUES (?,?,?,?,?,?,?,?,?)";
    private final String getCustomers = "SELECT * FROM customer WHERE firstName LIKE ?";
    private final String getCustomersId = "SELECT * FROM customer WHERE id LIKE ?";
    private final String getCustomerAccount = "SELECT * FROM account a, customer c, customer_account ca where a.id=ca.accounts_id and c.id=ca.customers_id and firstName = ? and lastName = ?";
    private final String setAccount = "INSERT INTO account (balance, beginBalance, beginBalanceTimestamp, creditLine, description, type) VALUES (?,?,?,?,?,?)";
    private final String getAccount = "SELECT * FROM account WHERE id = ? ";
    private final String setCustomerAccount = "INSERT INTO customer_account VALUES (?,?)";
    private final String getMovement = "SELECT * FROM movement m, account a WHERE m.account_id LIKE a.id AND a.id LIKE ?";
    private final String getFinalMovementID = "SELECT MAX(id) from movement";
    private final String updateBalance = "update account set balance = ? where id = ?";
    private final String setMovement = "insert into movement values (?, ?, ?, ?, ?, ?)";
    private final String getFinalAccountID = "SELECT MAX(id) from account";
    private final String setCustomerNew = "INSERT INTO customer (id, firstName, lastName, middleInitial, street, city, state, email, zip, phone) VALUES (?,?,?,?,?,?,?,?,?,?)";

    public DaoImplementation() {
        this.configFile = ResourceBundle.getBundle("config.config");
        this.driverBD = this.configFile.getString("Driver");
        this.urlBD = this.configFile.getString("Conn");
        this.userBD = this.configFile.getString("DBUser");
        this.passwordBD = this.configFile.getString("DBPass");
    }

    /**
     * Abrir conexion BBDD
     */
    private void openConnection() {
        try {
            Class.forName(this.driverBD);
            con = DriverManager.getConnection(this.urlBD, this.userBD, this.passwordBD);
        } catch (SQLException e) {
            Logger.getLogger(DaoImplementation.class.getName()).log(Level.SEVERE, null, e);
        } catch (ClassNotFoundException e1) {
            Logger.getLogger(DaoImplementation.class.getName()).log(Level.SEVERE, null, e1);
        }
    }

    /**
     * Cerrar conexion BBDD
     */
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

    /**
     * Agrega cliente a BBDD
     *
     * @param c
     * @return
     */
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
        }

        this.closeConnection();

        return c;
    }

    /**
     * Saca datos de cliente de BBDD
     *
     * @param n
     * @return
     */
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
        }

        this.closeConnection();

        return c;
    }

    /**
     * Muestra cuenta de cliente especifico de BBDD
     *
     * @param n
     * @param a
     * @return
     */
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
        }

        this.closeConnection();

        return c;
    }

    /**
     * Introduce cuenta nueva en BBDD
     *
     * @param c
     * @return
     */
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
        }

        this.closeConnection();

        return c;
    }

    /**
     * Muestra cuenta especifica de BBDD
     *
     * @param a
     * @return
     */
    public Cuenta getAccount(long a) {

        Cuenta c = null;
        this.openConnection();
        try {

            PreparedStatement ps = con.prepareStatement(getAccount);
            ps.setLong(1, a);
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
            ps.close();

        } catch (SQLException e) {
        }
        this.closeConnection();
        return c;
    }

    /**
     * Metodo para comprobar un cliente existente o no
     *
     * @param i
     */
    public void comprobarCliente(long i) {

        Cliente cli = null;
        Cuenta c = new Cuenta();
        Cuenta cue = new Cuenta();
        long idCuenta;

        this.openConnection();

        try {

            PreparedStatement ps = con.prepareStatement(getCustomersId);
            ps.setLong(1, i);
            ps.execute();

            ResultSet rs = ps.executeQuery();

            if (rs.next() == false) {
                System.out.println("Cliente NO existente");
                cli = new Cliente();
                cli.setDatosClienteSinId(i);
                setCustomerNew(cli, i);

                c.setDatosCuenta();
                setAccount(c);

                cue = getIdAccount();
                idCuenta = cue.getIdCuenta();

                agregarCuentaCliente(i, idCuenta);
            } else {
                System.out.println("Cliente existente");

                c.setDatosCuenta();
                setAccount(c);

                cue = getIdAccount();
                idCuenta = cue.getIdCuenta();

                agregarCuentaCliente(i, idCuenta);
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        this.closeConnection();

    }

    /**
     * Metodo para crear cuenta a cliente
     *
     * @param c
     * @param cli
     */
    public void agregarCuentaCliente(long cli, long c) {

        this.openConnection();
        try {

            PreparedStatement ps = con.prepareStatement(setCustomerAccount);
            ps.setLong(1, cli);
            ps.setLong(2, c);

            ps.execute();
            ps.close();

        } catch (SQLException e) {
        }

        this.closeConnection();

    }

    /**
     * Metod to make a movement and update the balance of the account
     *
     * @param acco Account to update the balance
     * @param ammount of money of the movement
     * @param desc Description of the movement, Deposit or Payment
     * @param type Type of movement, just to make the life easier
     */
    public void realizarMovimiento(Cuenta acco, double ammount, String desc, int type) {
        long lastId = 0;
        //Connection with the DB
        this.openConnection();
        try {
            if (type == 1) {//Deposit
                double finalRes = acco.getBalance() + ammount;

                //Updates the account's balance
                stat = con.prepareStatement(updateBalance);
                stat.setDouble(1, finalRes);
                stat.setLong(2, acco.getIdCuenta());
                stat.executeUpdate();

                //Gets the last id of movement
                stat = con.prepareStatement(getFinalMovementID);
                ResultSet rs = stat.executeQuery();
                while (rs.next()) {
                    lastId = rs.getLong(1);
                }
                rs.close();

                //Creates a row in movement
                stat = con.prepareStatement(setMovement);
                stat.setLong(1, (lastId + 1));
                stat.setDouble(2, ammount);
                stat.setDouble(3, finalRes);
                stat.setString(4, desc);
                Timestamp time = new Timestamp(System.currentTimeMillis());
                stat.setTimestamp(5, time);
                stat.setLong(6, acco.getIdCuenta());
                stat.executeUpdate();

            } else {//Payment
                if (acco.getBalance() >= ammount) { //If the balance is enought
                    double finalRes = acco.getBalance() - ammount;

                    //Updates the account's balance
                    stat = con.prepareStatement(updateBalance);
                    stat.setDouble(1, finalRes);
                    stat.setLong(2, acco.getIdCuenta());
                    stat.executeUpdate();

                    //Gets the last id of movement
                    stat = con.prepareStatement(getFinalMovementID);
                    ResultSet rs = stat.executeQuery();
                    while (rs.next()) {
                        lastId = rs.getLong(1);
                    }
                    rs.close();

                    //Creates a row in movement
                    stat = con.prepareStatement(setMovement);
                    stat.setLong(1, (lastId + 1));
                    stat.setDouble(2, ammount);
                    stat.setDouble(3, finalRes);
                    stat.setString(4, desc);
                    Timestamp time = new Timestamp(System.currentTimeMillis());
                    stat.setTimestamp(5, time);
                    stat.setLong(6, acco.getIdCuenta());
                    stat.executeUpdate();
                } else //Error, not enought money in the acount
                {
                    System.out.println("Not enought balance.");
                }
            }
        } catch (SQLException e) {
            //Temporal error print
            System.out.println("Error with the SQL or the DB - "
                    + e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //
        this.closeConnection();
    }

    /**
     * Metodo para consultar movimiento en BBDD
     *
     * @param i
     * @return
     */
    public Movimiento getMovements(long i) {
        Movimiento m = null;

        this.openConnection();

        try {

            PreparedStatement ps = con.prepareStatement(getMovement);
            ps.setLong(1, i);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                m = new Movimiento();
                m.setIdMovimiento(rs.getLong("id"));
                m.setCantidad(rs.getDouble("amount"));
                m.setBalanceMov(rs.getDouble("balance"));
                m.setDescripcionMov(rs.getString("description"));
                m.setFecha(rs.getTimestamp("timestamp"));
                System.out.println(m.toString());

            }

            ps.execute();
            rs.close();
            ps.close();

        } catch (SQLException e) {
        }

        this.closeConnection();

        return m;
    }

    /**
     * Devuelve el id de la ultima cuenta
     *
     * @return
     */
    public Cuenta getIdAccount() {
        Cuenta c = null;
        this.openConnection();
        try {

            PreparedStatement ps = con.prepareStatement(getFinalAccountID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                c = new Cuenta();
                c.setIdCuenta(rs.getLong("MAX(id)"));
                c.setBalance(rs.getDouble("balance"));
                c.setBalanceInicial(rs.getDouble("beginBalance"));
                c.setBalanceInicialFecha(rs.getTimestamp("beginBalanceTimestamp"));
                c.setLineaCredito(rs.getDouble("creditLine"));
                c.setDescripcion(rs.getString("description"));
                c.setTipo(rs.getInt("type"));
            }
            ps.execute();
            ps.close();

        } catch (SQLException e) {
        }
        this.closeConnection();
        return c;
    }

    /**
     * Crea un nuevo cliente con un ID asignado manualmente.
     *
     * @param c
     * @param id
     * @return
     */
    public Cliente setCustomerNew(Cliente c, long id) {

        this.openConnection();

        try {

            PreparedStatement ps = con.prepareStatement(setCustomerNew);
            ps.setLong(1, id);
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getApellido());
            ps.setString(4, c.getInicial());
            ps.setString(5, c.getCalle());
            ps.setString(6, c.getCiudad());
            ps.setString(7, c.getProvincia());
            ps.setString(8, c.getEmail());
            ps.setInt(9, c.getCodigoPostal());
            ps.setLong(10, c.getTelefono());

            ps.execute();
            ps.close();

        } catch (SQLException e) {
        }

        this.closeConnection();

        return c;
    }
}
