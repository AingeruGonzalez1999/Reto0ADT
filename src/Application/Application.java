package Application;

import clases.Cliente;
import clases.Cuenta;
import clases.CuentaCliente;
import dao.Dao;
import utilidades.Utilidades;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Ejecuta la aplicacion
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
    /**
     * Muestra el menu
     * @return devuelve entero con la opcion seleccionada
     */
    public static int obtenerOpcMenu() {
        int opc;

        System.out.println("B A N C O");
        System.out.println("**********************************************************");
        System.out.println("1. Crear cliente."); // Hecho
        System.out.println("2. Consultar datos de un cliente."); // Hecho
        System.out.println("3. Consultar cuentas de un cliente."); // Hecho
        System.out.println("4. Crear cuenta para cliente."); // Acabar
        System.out.println("5. Agregar cliente a cuenta."); // Hecho
        System.out.println("6. Consultar datos de una cuenta."); // Hecho
        System.out.println("7. Realizar movimiento sobre una cuenta."); // Acabar
        System.out.println("8. Consultar movimientos de una cuenta."); // Hecho
        System.out.println("9. S A L I R ");
        System.out.println("**********************************************************");
        System.out.println("Introduce opción: ");
        opc = Utilidades.leerInt(1, 9);
        return opc;
    }
    /**
     * Crea un nuevo cliente
     */
    private static void crearCliente() {
        Cliente c = new Cliente();
        c.setDatosCliente();
        dao.setCustomer(c);
    }
    /**
     * Consulta datos de un cliente
     */
    private static void consultarCliente() {
        System.out.println("Introuce cliente a consultar:");
        String n = Utilidades.introducirCadena();
        dao.getCustomers(n);
    }
    /**
     * Consulta cuenta/s de un cliente
     */
    private static void consultarCuentaCliente() {
        System.out.println("Introduce nombre cliente para ver sus cuentas:");
        String nom = Utilidades.introducirCadena();
        System.out.println("Introduce apellido cliente para ver sus cuentas:");
        String ape = Utilidades.introducirCadena();
        dao.getCuentaCliente(nom, ape);
    }
    /**
     * Crea cuenta/cliente para cliente
     */
    private static void crearCuentaCliente() {
        System.out.println("Introduce ID cliente para crear la cuenta:");
        long id = Utilidades.leerLong();
        dao.comprobarCliente(id); 
    }
    /**
     * Agrega cliente a cuenta
     */
    private static void agregarClienteCuenta() {
        System.out.println("Introduce ID de cliente:");
        long idCli = Utilidades.leerLong();
        System.out.println("Introduce ID de cuenta a asociar:");
        long idCu = Utilidades.leerLong();
        dao.agregarCuentaCliente(idCli, idCu);
    }
    /**
     * Consulta datos de una cuenta
     */
    private static void consultarDatosCuenta() {

        System.out.println("Introuce ID de cuenta a consultar:");
        long a = Utilidades.leerLong();
        dao.getAccount(a);

    }
    
    /**
     * Realiza un movimiento
     */
    private static void realizarMovimiento() {
        String desc = null;
        double amount = 0;
        Cuenta acco= null;
        
        //Ask for Account ID
        System.out.println("Introduce ID de la cuenta: ");
        long id = Utilidades.leerLong();
        acco = dao.getAccount(id);//The account exists?
        
        if(acco != null){//If the account exists, 'acco' is not null
            System.out.println("Que desea hacer?:\n"
                    + "1).Depositar dinero.\n"
                    + "2).Retirar dinero.\n");
            int type = Utilidades.leerInt(1, 2);
            if(type == 1){
                desc = "Deposit";
                System.out.println("Introduce cantidad a depositar: ");
            }
            else{
                desc = "Payment";
                System.out.println("Introduce cantidad a retirar: ");
            }
            amount = Utilidades.leerDouble();
            dao.realizarMovimiento(acco, amount, desc, type);
        }else//The account doesn't exist
            System.out.println("Cuenta no existente.");
    }
    
    /**
     * Consulta movimientos de una cuenta
     */
    private static void consultarMovimiento() {
        System.out.println("Introduce Id de cuenta:");
        long n = Utilidades.leerLong();
        dao.getMovements(n);
    }
}
