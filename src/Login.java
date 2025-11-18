import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Login  {


	    public static Usuario iniciarSesion() {
	        Usuario sesionActiva = null;

	        do {
	            String[] opciones = {"Iniciar sesión", "Registrarse", "Salir"};
	            int seleccion = JOptionPane.showOptionDialog(
	                    null,
	                    "Bienvenido al Sistema Bancario",
	                    "Login",
	                    JOptionPane.DEFAULT_OPTION,
	                    JOptionPane.INFORMATION_MESSAGE,
	                    null,
	                    opciones,
	                    opciones[0]
	            );

	            switch (seleccion) {
	                case 0:
	                    sesionActiva = loginUsuario();
	                    break;

	                case 1:
	                    registrarUsuario();
	                    break;

	                default:
	                    return null; // salir
	            }

	        } while (sesionActiva == null);

	        return sesionActiva;
	    }

	    private static Usuario loginUsuario() {

	        String mail = JOptionPane.showInputDialog("Ingrese su email (o código secreto):");

	        if (mail == null) return null;

	        // BYPASS DEBUGGER
	        if (mail.equals("desbug123")) {
	            JOptionPane.showMessageDialog(null, "Modo desarrollador activado.");
	            Usuario admin = Usuario.buscarPorMail("admin@banco.com");
	            return admin;     // NO ejecutamos menú aquí
	        }

	        String contr = JOptionPane.showInputDialog("Ingrese su contraseña:");
	        if (contr == null) return null;

	        Usuario usuario = Usuario.buscarPorMail(mail);

	        if (usuario != null && usuario.getContr().equals(contr)) {
	            JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso.");
	            return usuario; // devolvemos, NO mostramos menú
	        }

	        JOptionPane.showMessageDialog(null, "Email o contraseña incorrectos.");
	        return null;
	    }

	    private static void registrarUsuario() {

	        String mail = JOptionPane.showInputDialog("Ingrese email:");
	        String contr = JOptionPane.showInputDialog("Ingrese contraseña:");
	        String alias = JOptionPane.showInputDialog("Ingrese alias:");

	        if (mail == null || contr == null || alias == null) return;

	        String[] roles = {"Cliente", "Empleado", "Admin"};
	        int rolSeleccion = JOptionPane.showOptionDialog(
	                null,
	                "Seleccione rol:",
	                "Registro",
	                JOptionPane.DEFAULT_OPTION,
	                JOptionPane.INFORMATION_MESSAGE,
	                null,
	                roles,
	                roles[0]
	        );

	        if (rolSeleccion == -1) return;

	        Rol rolUsuario = switch (rolSeleccion) {
	            case 0 -> Rol.CLIENTE;
	            case 1 -> Rol.EMPLEADO;
	            case 2 -> Rol.ADMINISTRADOR;
	            default -> null;
	        };

	        if (rolUsuario == null) return;

	        Usuario nuevo = null;

	        switch (rolUsuario) {

	            case CLIENTE:
	                nuevo = new Cliente(mail, contr, alias, Rol.CLIENTE, new Cuenta());
	                break;

	            case EMPLEADO:
	                nuevo = new Empleado(mail, contr);
	                nuevo.setAlias(alias);
	                break;

	            case ADMINISTRADOR:
	                nuevo = new Admin(mail, contr);
	                nuevo.setAlias(alias);
	                break;
	        }

	        Main.listaUsuarios.add(nuevo);

	        JOptionPane.showMessageDialog(null, "Usuario registrado correctamente.");
	    }
	

	


}