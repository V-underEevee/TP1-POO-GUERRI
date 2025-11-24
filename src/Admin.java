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
                "Ver usuarios registrados",
                "Ver historial global",
                "Cerrar sesión"
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

            if (opcion == JOptionPane.CLOSED_OPTION || opcion == -1 || opcion == 2) {
                // Cerrar sesión
                return;
            }

            switch (opcion) {
                case 0:
                	verUsuarios();
                	break;
                case 1:
                	verHistorialGlobal();
                	break;
            }
        }
    }

    private void verUsuarios() {
        if (Usuario.getUsuarios().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay usuarios registrados.");
            return;
        }

        StringBuilder sb = new StringBuilder("USUARIOS REGISTRADOS:\n\n");
        for (Usuario u : Usuario.getUsuarios()) {
            sb.append(u.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
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
