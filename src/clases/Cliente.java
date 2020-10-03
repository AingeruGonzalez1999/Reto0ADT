/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.Serializable;
import utilidades.Utilidades;

/**
 *
 * @author gsalg
 */
public class Cliente implements Serializable {

    // Atributos
    private long idCliente; //Primary Key
    private String nombre;
    private String apellido;
    private String inicial;
    private String calle;
    private String ciudad;
    private String provincia;
    private String email;
    private int codigoPostal;
    private long telefono;

    // Constructor
    public Cliente() {

    }

    // Métodos
    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int id) {
        this.idCliente = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getInicial() {
        return inicial;
    }

    public void setInicial(String inicial) {
        this.inicial = inicial;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public void setDatosCliente() {
        System.out.println("Introduce nombre:");
        nombre = Utilidades.introducirCadena();
        System.out.println("Introduce apellido:");
        apellido = Utilidades.introducirCadena();
        inicial = nombre.substring(0).toUpperCase().concat(".");
        System.out.println("Introduce calle:");
        calle = Utilidades.introducirCadena();
        System.out.println("Introduce ciudad:");
        ciudad = Utilidades.introducirCadena();
        System.out.println("Introduce provincia:");
        provincia = Utilidades.introducirCadena();
        email = nombre.substring(0).concat(apellido).concat("@").concat("gmail.com");
        System.out.println("Introduce código postal:");
        codigoPostal = Utilidades.leerInt();
        System.out.println("Introduce teléfono:");
        telefono = Utilidades.leerInt();
    }

    @Override
    public String toString() {
        return "Cliente{" + "nombre=" + nombre + ", apellido=" + apellido + ", inicial=" + inicial + ", calle=" + calle + ", ciudad=" + ciudad + ", provincia=" + provincia + ", email=" + email + ", codigoPostal=" + codigoPostal + ", telefono=" + telefono + '}';
    }
  
}
