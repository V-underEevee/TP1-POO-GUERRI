import javax.swing.JOptionPane;

public class Admin extends Usuario {
	
	public Admin(String mail, String contr) {
	    super(mail, contr, "admin", Rol.ADMINISTRADOR);
	}


	    @Override
	    public String toString() {
	        return "Administrador [] " + super.toString();
	    }

	    public void menu() {
	        boolean salir = false;
	        while (!salir) {
	            String[] opciones = {
	                "Crear usuario",
	                "Eliminar usuario",
	                "Modificar usuario",
	                "Ver usuarios registrados",
	                "Ver historial global",
	                "Salir"
	            };

	            int opcion = JOptionPane.showOptionDialog(
	                null,
	                "Menú de Administrador",
	                "Administrador",
	                JOptionPane.DEFAULT_OPTION,
	                JOptionPane.INFORMATION_MESSAGE,
	                null,
	                opciones,
	                opciones[0]
	            );

	            switch (opcion) {
	                case 0:
	                    crearUsuario();
	                    break;
	                case 1:
	                    eliminarUsuario();
	                    break;
	                case 2:
	                    modificarUsuario();
	                    break;
	                case 3:
	                    verUsuarios();
	                    break;
	                case 4:
	                	verHistorialGlobal();
	                	break;
	                case 5:
	                	return;
	                default:
	                    salir = true;
	                    break;
	            }
	        }
	    }

	    private void crearUsuario() {
	        JOptionPane.showMessageDialog(null, "Función para crear usuario aún no implementada.");
	        // lógica para registrar un nuevo usuario manualmente
	    }

	    private void eliminarUsuario() {
	        String mailUsuario = JOptionPane.showInputDialog("Ingrese el correo del usuario a eliminar:");
	        Usuario u = Usuario.buscarPorMail(mailUsuario);
	        if (u != null) {
	            Usuario.getUsuarios().remove(u);
	            JOptionPane.showMessageDialog(null, "Usuario eliminado con éxito.");
	        } else {
	            JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
	        }
	    }

	    private void modificarUsuario() {
	        JOptionPane.showMessageDialog(null, "Función para modificar usuario aún no implementada.");
	        // añadir opciones para modificar contraseña, rol, etc.
	    }

	    private void verUsuarios() {
	        JOptionPane.showMessageDialog(null, Usuario.getUsuarios().toString());
	    }
	    private void verHistorialGlobal() {
	        if (Main.historialGlobal.isEmpty()) {
	            JOptionPane.showMessageDialog(null, "No hay movimientos registrados.");
	            return;
	        }

	        StringBuilder sb = new StringBuilder("HISTORIAL GLOBAL:\n\n");

	        for (Movimiento m : Main.historialGlobal) {
	            sb.append(m.toString()).append("\n");
	        }

	        JOptionPane.showMessageDialog(null, sb.toString());
	    }

}
