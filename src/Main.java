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

    Usuario usuarioLogueado = null;

    do {
    	
    	usuarioLogueado = Login.iniciarSesion();
    }while(usuarioLogueado == null) ;
    

    JOptionPane.showMessageDialog(null, 
        "Bienvenido, " + usuarioLogueado.getMail() + "\nRol: " + usuarioLogueado.getRol());

    // Menú-según el-rol
    switch (usuarioLogueado.getRol()) {
        case CLIENTE:
        	
            // Implementar-menú-del-cliente
            JOptionPane.showMessageDialog(null, "Redirigiendo al menú de Cliente...");
            Cliente actual = (Cliente)usuarioLogueado;
            actual.menu();
            break;
        case ADMINISTRADOR:
            // Implementar-menú-del-administrador
            JOptionPane.showMessageDialog(null, "Redirigiendo al menú de Administrador...");
            break;
	case EMPLEADO:
		break;
	default:
		break;
    }
}
}
