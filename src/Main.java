import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Main {
	
	public static ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    public static ArrayList<Movimiento> historialGlobal = new ArrayList<>();
    static Usuario[] usuarios = new Usuario[100];
    static int totalUsuarios = 0;

    public static void main(String []args) {

        // Usuarios de prueba
        new Admin("admin@banco.com", "admin123");
        new Empleado("empleado@banco.com", "emp123");
        new Cliente("cliente@banco.com", "cli123", "clienteAlias",
                    Rol.CLIENTE, new Cuenta());

        while (true) {

            Usuario u = Login.iniciarSesion();

            if (u == null) {
                JOptionPane.showMessageDialog(null, "Sesión no iniciada. Saliendo del sistema.");
                System.exit(0);
            }
            
            // si llegó acá, hay usuario logueado:
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
            
            
    }
            }
    

            // 👈 Cuando el usuario toca "Cerrar sesión" en su menú,
            //     la función menu() hace return.
            //     Entonces volvemos al WHILE y sale otra vez el LOGIN.
        
    
    

    
    }
}


