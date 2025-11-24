import javax.swing.JOptionPane;

public class Login  {



	    public static Usuario iniciarSesion() {

	        while (true) {

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

	            // X o Salir
	            if (seleccion == -1 || seleccion == 2) {
	                return null;
	            }

	            switch (seleccion) {
	                case 0:
	                    Usuario u = loginUsuario();
	                    if (u != null) return u;   // Login exitoso
	                    break;

	                case 1:
	                    registrarUsuario();
	                    break;
	            }
	        }
	    }

	    private static Usuario loginUsuario() {

	        String mail = JOptionPane.showInputDialog("Ingrese su email (o código secreto):");

	        // X o cancelar
	        if (mail == null) return null;

	        // BYPASS DEBUG
	        if (mail.equals("desbug123")) {
	            JOptionPane.showMessageDialog(null, "Modo desarrollador activado.");
	            return Usuario.buscarPorMail("admin@banco.com");
	        }

	        String contr = JOptionPane.showInputDialog("Ingrese su contraseña:");

	        // X o cancelar
	        if (contr == null) return null;

	        Usuario usuario = Usuario.buscarPorMail(mail);

	        if (usuario != null && usuario.getContr().equals(contr)) {
	            JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso.");
	            return usuario;
	        }

	        JOptionPane.showMessageDialog(null, "Email o contraseña incorrectos.");
	        return null;
	    }

	    private static void registrarUsuario() {

	        String mail = JOptionPane.showInputDialog("Ingrese email:");
	        if (mail == null) return;

	        String contr = JOptionPane.showInputDialog("Ingrese contraseña:");
	        if (contr == null) return;

	        String alias = JOptionPane.showInputDialog("Ingrese alias:");
	        if (alias == null) return;

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

	        // X → salir del registro
	        if (rolSeleccion == -1) return;

	        Rol rolUsuario = null;

	        switch (rolSeleccion) {
	            case 0:
	                rolUsuario = Rol.CLIENTE;
	                break;
	            case 1:
	                rolUsuario = Rol.EMPLEADO;
	                break;
	            case 2:
	                rolUsuario = Rol.ADMINISTRADOR;
	                break;
	            default:
	                rolUsuario = null;
	                break;
	        }

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