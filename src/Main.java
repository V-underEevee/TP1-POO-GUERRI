import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Main {
	
	 public static ArrayList<Usuario> listaUsuarios = new ArrayList<>();
	 public static ArrayList<Movimiento> historialGlobal = new ArrayList<>();


	
public static void main(String []args) {
    // Inicializar-usuarios-de-prueba
	new Usuario("admin@banco.com", "admin123", "adminAlias", Rol.ADMINISTRADOR);
    new Usuario("empleado@banco.com", "emp123", "empleadoAlias", Rol.EMPLEADO);
    new Cliente("cliente@banco.com", "cli123", "clienteAlias",
            Rol.CLIENTE, new Cuenta());

    Usuario usuarioLogueado = Login.iniciarSesion();

    if (usuarioLogueado == null) {
        // Tocó salir o cerró la ventana → terminamos el programa
        JOptionPane.showMessageDialog(null, "Sesión no iniciada. Saliendo del sistema.");
        System.exit(0);
    }

    // si llegó acá, hay usuario logueado:
    switch (usuarioLogueado.getRol()) {
        case CLIENTE:
            ((Cliente) usuarioLogueado).menu();
            break;
        case EMPLEADO:
            ((Empleado) usuarioLogueado).menu();
            break;
        case ADMINISTRADOR:
            ((Admin) usuarioLogueado).menu();
            break;
    }
}
}
