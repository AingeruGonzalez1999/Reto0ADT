/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author gsalg
 */
public class Movimiento implements Serializable {

    // Atributos
    private long idMovimiento; //Primary Key
    private double cantidad;
    private double balanceMov;
    private String descripcionMov;
    private java.sql.Timestamp fecha;
    private long idCuenta; //Foreign Key

    // Constructor
    public Movimiento() {

    }

    // Métodos
    public long getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(long idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getBalanceMov() {
        return balanceMov;
    }

    public void setBalanceMov(double balanceMov) {
        this.balanceMov = balanceMov;
    }

    public String getDescripcionMov() {
        return descripcionMov;
    }

    public void setDescripcionMov(String descripcionMov) {
        this.descripcionMov = descripcionMov;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public long getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(long idCuenta) {
        this.idCuenta = idCuenta;
    }

    @Override
    public String toString() {
        return "Movimiento{" + "idMovimiento=" + idMovimiento + ", cantidad=" + cantidad + ", balanceMov=" + balanceMov + ", descripcionMov=" + descripcionMov + ", fecha=" + fecha + '}';
    }

  
    

}
