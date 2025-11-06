import javax.swing.JOptionPane;

public class Login  {
	 
    public static void iniciarSesion() {
        boolean sesionActiva = false;

        while (!sesionActiva) {
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
                    sesionActiva = true; // Cierra el ciclo si elige "Salir" o cierra ventana
                    break;
            }
        }
    }

    private static boolean loginUsuario() {
        String mail = JOptionPane.showInputDialog("Ingrese su email (o código secreto):");

        // Bypass de desarrollador/debugger
        if (mail != null) {
            if (mail.equals("desbug123")) {
                JOptionPane.showMessageDialog(null, "¡Bienvenido al modo desarrollador!");
                Usuario admin = Usuario.buscarPorMail("admin@banco.com");
                if (admin != null) {
                    ((Admin) admin).menu();
                }
                return true;
            }
        } else return false;

        String contr = JOptionPane.showInputDialog("Ingrese su contraseña:");
        Usuario usuario = Usuario.buscarPorMail(mail);

        if (usuario != null && usuario.getContr().equals(contr)) {
            JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso.");
            // Redirige según rol
            switch (usuario.getRol()) {
                case ADMINISTRADOR:
                    ((Admin) usuario).menu();
                    break;
                case EMPLEADO:
                    ((Empleado) usuario).menu();
                    break;
                case CLIENTE:
                    ((Cliente) usuario).menu();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Rol desconocido.");
            }
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Email o contraseña incorrectos.");
            return false;
        }
    }

    private static void registrarUsuario() {
        String mail = JOptionPane.showInputDialog("Ingrese su email:");
        String contr = JOptionPane.showInputDialog("Ingrese su contraseña:");
        String alias = JOptionPane.showInputDialog("Ingrese un alias de usuario:");
        
        String[] roles = {"Cliente", "Empleado", "Admin"};
        int rolSeleccion = JOptionPane.showOptionDialog(
                null,
                "Seleccione su rol:",
                "Registro",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                roles,
                roles[0]
        );

        if (rolSeleccion == -1) return; // Si cierra ventana

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
        }

        // Crear usuario según rol
        switch (rolUsuario) {
            case CLIENTE:
                Cliente cliente = new Cliente(mail, contr, Rol.CLIENTE, new Cuenta());
                cliente.setAlias(alias);
                JOptionPane.showMessageDialog(null, "Cliente registrado correctamente.");
                break;
            case EMPLEADO:
                Empleado empleado = new Empleado(mail, contr, java.time.LocalDate.now());
                empleado.setAlias(alias);
                JOptionPane.showMessageDialog(null, "Empleado registrado correctamente.");
                break;
            case ADMINISTRADOR:
                Admin admin = new Admin(mail, contr);
                admin.setAlias(alias);
                JOptionPane.showMessageDialog(null, "Administrador registrado correctamente.");
                break;
        }
    }
	
	


}