package Application;

import clases.Cliente;
import clases.Cuenta;
import dao.Dao;
import utilidades.Utilidades;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @version 1.0
 * @author Garikoitz
 * @author Aingeru
 */
public class Application {

    private static Dao dao = new Dao();
    private static Cliente cliente = new Cliente();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int m;
        do {
            m = obtenerOpcMenu();
            switch (m) {

                case 1:
                    crearCliente();
                    break;
                case 2:
                    consultarCliente();
                    break;
                case 3:
                    consultarCuentaCliente();
                    break;
                case 4:
                    crearCuentaCliente();
                    break;
                case 5:
                    agregarClienteCuenta();
                    break;
                case 6:
                    consultarDatosCuenta();
                    break;
                case 7:
                    realizarMovimiento();
                    break;
                case 8:
                    consultarMovimiento();
                    break;
                case 9:
                    System.out.println("S A L I E N D O");

                    break;
                default:
                    System.out.println("que has hecho para llegar aquí????????");
                    break;
            }
        } while (m != 9);
    }

    public static int obtenerOpcMenu() {
        int opc;

        System.out.println("B A N C O");
        System.out.println("**********************************************************");
        System.out.println("1. Crear cliente.");
        System.out.println("2. Consultar datos de un cliente.");
        System.out.println("3. Consultar cuentas de un cliente.");
        System.out.println("4. Crear cuenta para cliente.");
        System.out.println("5. Agregar cliente a cuenta.");
        System.out.println("6. Consultar datos de una cuenta.");
        System.out.println("7. Realizar movimiento sobre una cuenta.");
        System.out.println("8. Consultar movimientos de una cuenta.");
        System.out.println("9. S A L I R ");
        System.out.println("**********************************************************");
        System.out.println("Introduce opción: ");
        opc = Utilidades.leerInt(1, 9);
        return opc;
    }

    private static void crearCliente() {
        Cliente c = new Cliente();
        c.setDatosCliente();
        dao.setCustomer(c);
    }

    private static void consultarCliente() {
        System.out.println("Introuce cliente a consultar:");
        String n = Utilidades.introducirCadena();
        dao.getCustomers(n);
    }

    private static void consultarCuentaCliente() {
        System.out.println("Introduce nombre cliente para ver sus cuentas:");
        String nom = Utilidades.introducirCadena();
        System.out.println("Introduce apellido cliente para ver sus cuentas:");
        String ape = Utilidades.introducirCadena();
        dao.getCuentaCliente(nom, ape);
    }

    private static void crearCuentaCliente() {
        Cuenta c = new Cuenta();
        c.setDatosCuenta();
        dao.setAccount(c);

    }

    private static void agregarClienteCuenta() {

    }

    private static void consultarDatosCuenta() {

        System.out.println("Introuce ID de cuenta a consultar:");
        long a = Utilidades.leerInt();
        dao.getAccount(a);

    }

    private static void realizarMovimiento() {

    }

    private static void consultarMovimiento() {

    }

}
