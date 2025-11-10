import javax.swing.JOptionPane;

public class Main {
public static void main(String []args) {
    // Inicializar-usuarios-de-prueba
    new Usuario("cliente1@mail.com", "1234", Rol.CLIENTE);
    new Usuario("admin@banco.com", "admin123", Rol.ADMINISTRADOR);

    Usuario usuarioLogueado = null;

    while (usuarioLogueado == null) {
        usuarioLogueado = Login.iniciarSesion();
    }

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
