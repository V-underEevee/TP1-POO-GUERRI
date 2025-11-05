import javax.swing.JOptionPane;

public class Login  {
	 
    public static Usuario iniciarSesion() {
        String[] opciones = { "Iniciar sesión", "Registrarse", "Salir" };
        int eleccion = JOptionPane.showOptionDialog(null, "Bienvenido al sistema bancario",
                "Login", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

        switch (eleccion) {
            case 0:
                return loginUsuario();
            case 1:
                return registrarUsuario();
            default:
                JOptionPane.showMessageDialog(null, "Gracias por usar el sistema. ¡Hasta luego!");
                System.exit(0);
        }
        return null; 
    }

    private static Usuario loginUsuario() {
        String mail = JOptionPane.showInputDialog("Ingrese su email (o código secreto):");

        // Clave secreta para modo desarrollador/debugger
        if (mail != null && mail.equals("desbug123")) {
            JOptionPane.showMessageDialog(null, "¡Bienvenido al modo desarrollador!");
            return Usuario.buscarPorMail("admin@banco.com"); // usuario admin predeterminado
        }

        String contr = JOptionPane.showInputDialog("Ingrese su contraseña:");

        Usuario usuario = Usuario.buscarPorMail(mail);

        if (usuario != null && usuario.getContr().equals(contr)) {
            JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso.");
            return usuario;
        } else {
            JOptionPane.showMessageDialog(null, "Credenciales incorrectas. Intente nuevamente.");
            return null;
        }
    }

    private static Usuario registrarUsuario() {
        String mail = JOptionPane.showInputDialog("Ingrese su email:");
        if (Usuario.buscarPorMail(mail) != null) {
            JOptionPane.showMessageDialog(null, "Ese correo ya está registrado.");
            return null;
        }

        String contr = JOptionPane.showInputDialog("Ingrese su contraseña:");
        if (contr == null || contr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar una contraseña válida.");
            return null;
        }

        String[] roles = { "Cliente", "Administrador" };
        int eleccionRol = JOptionPane.showOptionDialog(null, "Seleccione su tipo de usuario", "Registro",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, roles, roles[0]);

        Rol rol = (eleccionRol == 1) ? Rol.ADMINISTRADOR : Rol.CLIENTE;

        Usuario nuevoUsuario = new Usuario(mail, contr, rol);
        JOptionPane.showMessageDialog(null, "Registro exitoso. Ahora puede iniciar sesión.");

        return nuevoUsuario;
    }
	
	


}