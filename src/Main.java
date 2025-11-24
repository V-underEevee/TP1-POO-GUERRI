import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Main {
	
	public static ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    public static ArrayList<Movimiento> historialGlobal = new ArrayList<>();
    static Usuario[] usuarios = new Usuario[100];
    static int totalUsuarios = 0;

    public static void main(String[] args) {

        // Usuarios iniciales
        new Usuario("admin@banco.com", "admin123", "adminAlias", Rol.ADMINISTRADOR);
        new Usuario("empleado@banco.com", "emp123", "empleadoAlias", Rol.EMPLEADO);
        new Cliente("cliente@banco.com", "cli123", "clienteAlias",
                Rol.CLIENTE, new Cuenta());

        while (true) {   // 👈 BUCLE PRINCIPAL

            Usuario u = Login.iniciarSesion();

            if (u == null) {
                JOptionPane.showMessageDialog(null, "Sesión no iniciada. Saliendo del sistema.");
                System.exit(0);   // 👈 AHORA cierra de verdad
            }


            // 👈 Cuando el usuario toca "Cerrar sesión" en su menú,
            //     la función menu() hace return.
            //     Entonces volvemos al WHILE y sale otra vez el LOGIN.
        
    
    

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
    }
}
