import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Main {
	
	public static ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    public static ArrayList<Movimiento> historialGlobal = new ArrayList<>();
    public static ArrayList<Cajero> cajeros = new ArrayList<>();
    static Usuario[] usuarios = new Usuario[100];
    static int totalUsuarios = 0;

    public static void main(String []args) {

    	// Usuarios de prueba
        new Admin("admin@banco.com", "admin123");
        new Empleado("empleado@banco.com", "emp123");
        new Cliente("cliente@banco.com", "cli123", "clienteAlias",
                Rol.CLIENTE, new Cuenta());
        
     // Crear 4 cajeros por defecto
        Main.cajeros.add(new Cajero(1, 50000));
        Main.cajeros.add(new Cajero(2, 50000));
        Main.cajeros.add(new Cajero(3, 50000));
        Main.cajeros.add(new Cajero(4, 50000));


        while (true) {
            Usuario u = Login.iniciarSesion();

            if (u == null) {
                JOptionPane.showMessageDialog(null, "Saliendo del sistema.");
                System.exit(0);
            }

            switch (u.getRol()) {
                case CLIENTE:
                    ((Cliente) u).menu();
                    break;
                case EMPLEADO:
                    ((Empleado) u).menu();
                    break;
                case ADMINISTRADOR:
                    ((Admin) u).menu();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Rol desconocido.");
                    break;
            }
            
            // 👈 Cuando el usuario toca "Cerrar sesión" en su menú,
            //     la función menu() hace return.
            //     Entonces volvemos al WHILE y sale otra vez el LOGIN.
        }
    }
}


