/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.Serializable;

/**
 *
 * @author gsalg
 */
public class CuentaCliente implements Serializable {

    // Atributos
    private long idCliente; //Primary Key & Foreign Key
    private long idCuenta; //Primary KeyF  & Foreign Key

    // Constructor
    public CuentaCliente() {

    }

    // Métodos
    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public long getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(long idCuenta) {
        this.idCuenta = idCuenta;
    }

}
