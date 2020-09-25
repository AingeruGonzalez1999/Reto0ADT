package Application;

import java.util.Scanner;

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
        Scanner entrada = new Scanner(System.in);
        
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
        opc = entrada.nextInt();
        return opc;
    }

    private static void crearCliente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void consultarCliente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void consultarCuentaCliente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void crearCuentaCliente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void agregarClienteCuenta() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void consultarDatosCuenta() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void realizarMovimiento() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void consultarMovimiento() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
