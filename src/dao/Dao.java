/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import clases.Cliente;
import clases.Cuenta;
import clases.Movimiento;

/**
 * Consultas hacia la BD.
 */
public interface Dao {

    public Cliente setCustomer(Cliente c);

    public Cliente getCustomers(String n);

    public Cuenta getCuentaCliente(String n, String a);

    public Cuenta setAccount(Cuenta c);

    public Cuenta getAccount(long a);

    public void comprobarCliente(long i);

    public void agregarCuentaCliente(long cli, long c);

    public void realizarMovimiento(Cuenta acco, double ammount, String desc, int type);

    public Movimiento getMovements(long i);

    public Cuenta getIdAccount();

    public Cliente setCustomerNew(Cliente c, long id);
}
