import javax.swing.JOptionPane;

public class Admin extends Usuario {
	
	 public Admin(String mail, String contr) {
	        super(mail, contr, Rol.Admin);
	    }

	    @Override
	    public String toString() {
	        return "Administrador [] " + super.toString();
	    }

	    public void menu() {
	        boolean salir = false;
	        do {
	            String[] opciones = this.getRol().getOpciones();
	            int opcion = JOptionPane.showOptionDialog(null, "Menú de Administrador", "Administrador", 
	                                                      JOptionPane.DEFAULT_OPTION,
	                                                      JOptionPane.INFORMATION_MESSAGE, 
	                                                      null, opciones, opciones[0]);

	            switch (opcion) {
	                case 0: // Dar de alta empleado
	                    JOptionPane.showMessageDialog(null, "Función para crear empleado aún no implementada.");
	                    break;
	                case 1: // Ver todos los usuarios
	                    JOptionPane.showMessageDialog(null, Usuario.getUsuarios().toString());
	                    break;
	                default:
	                    salir = true;
	                    break;
	            }
	        } while (!salir);
	    }
}
