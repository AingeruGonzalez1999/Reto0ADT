/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import utilidades.Utilidades;

/**
 *
 * @author gsalg
 */
public class Cuenta implements Serializable {

    // Atributos
    private long idCuenta; //Primary Key
    private double balance;
    private double balanceInicial;
    private java.sql.Timestamp balanceInicialFecha; // M  I  R  A  R
    private double lineaCredito;
    private String descripcion;
    private int tipo;

    // Constructor
    public Cuenta() {

    }

    // Métodos
    public long getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(long idCuenta) {
        this.idCuenta = idCuenta;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalanceInicial() {
        return balanceInicial;
    }

    public void setBalanceInicial(double balanceInicial) {
        this.balanceInicial = balanceInicial;
    }

    public Timestamp getBalanceInicialFecha() {
        return balanceInicialFecha;
    }

    public void setBalanceInicialFecha(Timestamp balanceInicialFecha) {
    this.balanceInicialFecha = balanceInicialFecha;
    }

    public double getLineaCredito() {
        return lineaCredito;
    }

    public void setLineaCredito(double lineaCredito) {
        this.lineaCredito = lineaCredito;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public void setDatosCuenta(){
        System.out.println("Introduce balance:");
        balance = Utilidades.leerDouble();
        System.out.println("Introduce balance inicial:");
        balanceInicial = Utilidades.leerDouble();
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp=Timestamp.valueOf(now);
        balanceInicialFecha=timestamp;
        System.out.println("Introduce  linea de credito:");
        lineaCredito = Utilidades.leerDouble();
        System.out.println("Introduce descripcion:");
        descripcion = Utilidades.introducirCadena();
        System.out.println("Introduce tipo:");
        tipo = Utilidades.leerInt();
       
    }
    @Override
    public String toString() {
        return "Cuenta{" + "idCuenta=" + idCuenta + ", balance=" + balance + ", balanceInicial=" + balanceInicial + ", balanceInicialFecha=" + balanceInicialFecha + ", lineaCredito=" + lineaCredito + ", descripcion=" + descripcion + ", tipo=" + tipo + '}';
    }

}
