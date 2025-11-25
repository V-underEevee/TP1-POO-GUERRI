import java.time.LocalDate;

import javax.swing.JOptionPane;

public class Empleado extends Usuario {

	private LocalDate fechaInicio;

    public Empleado(String mail, String contr) {
        super(mail, contr, "empleado", Rol.EMPLEADO);
        this.fechaInicio = LocalDate.now();
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @Override
    public String toString() {
        return "Empleado [fechaInicio=" + fechaInicio + ", " + super.toString() + "]";
    }

    public void menu() {
        String[] opciones = {
            "Ver lista de clientes",
            "Buscar cliente",
            "Ver movimientos de un cliente",
            "Ver historial global",
            "Cerrar sesión"
        };

        boolean activo = true;

        while (activo) {
            int op = JOptionPane.showOptionDialog(
                null,
                "Menú Empleado",
                "Empleado",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opciones,
                opciones[0]
            );

            if (op == JOptionPane.CLOSED_OPTION || op == -1 || op == 4) {
                return; // Cierra sesión
            }

            switch (op) {
                case 0:
                    verListaClientes();
                    break;
                case 1:
                    buscarCliente();
                    break;
                case 2:
                    verMovimientosCliente();
                    break;
                case 3:
                    verHistorialGlobal();
                    break;
                case 4:
                    // ya controlado al inicio
                    break;
                default:
                    activo = false;
                    break;
            }
        }
    }

    private void verListaClientes() {
        StringBuilder sb = new StringBuilder("LISTA DE CLIENTES\n\n");
        boolean hayClientes = false;

        for (Usuario u : Main.listaUsuarios) {
            if (u instanceof Cliente) {
                Cliente c = (Cliente) u;
                hayClientes = true;

                sb.append("- Alias: ").append(nullSafe(c.getAlias()))
                  .append(" | Email: ").append(nullSafe(c.getMail()))
                  .append(" | Cuenta Nº: ").append(c.getCuenta().getNumeroCuenta())
                  .append(" | Saldo: $").append(String.format("%.2f", c.getCuenta().getSaldo()))
                  .append("\n");
            }
        }

        if (!hayClientes)
            JOptionPane.showMessageDialog(null, "No hay clientes registrados.");
        else
            JOptionPane.showMessageDialog(null, sb.toString());
    }

    private Cliente pedirCliente() {
        String dato = JOptionPane.showInputDialog("Ingrese alias o email del cliente:");

        if (dato == null) return null;
        dato = dato.trim();

        if (dato.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Entrada vacía. Operación cancelada.");
            return null;
        }

        Usuario encontrado = Usuario.buscarPorAlias(dato);
        if (encontrado == null)
            encontrado = Usuario.buscarPorMail(dato);

        if (encontrado instanceof Cliente)
            return (Cliente) encontrado;

        JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
        return null;
    }

    private void buscarCliente() {
        Cliente c = pedirCliente();
        if (c == null) return;

        String info =
            "Cliente encontrado:\n\n" +
            "Alias: " + nullSafe(c.getAlias()) + "\n" +
            "Email: " + nullSafe(c.getMail()) + "\n" +
            "Cuenta Nº: " + c.getCuenta().getNumeroCuenta() + "\n" +
            "Saldo: $" + String.format("%.2f", c.getCuenta().getSaldo());

        JOptionPane.showMessageDialog(null, info);
    }

    private void verMovimientosCliente() {
        Cliente c = pedirCliente();
        if (c == null) return;

        try {
            Object histObj = c.getCuenta().getClass()
                              .getMethod("getHistorial")
                              .invoke(c.getCuenta());

            if (histObj instanceof java.util.List) {
                @SuppressWarnings("unchecked")
                java.util.List<String> hist = (java.util.List<String>) histObj;

                if (hist.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Este cliente no tiene movimientos.");
                } else {
                    StringBuilder sb = new StringBuilder("Historial de movimientos:\n\n");
                    for (String m : hist) sb.append(m).append("\n");
                    JOptionPane.showMessageDialog(null, sb.toString());
                }
                return;
            }
        } catch (Exception e) {
            // fallback silencioso
        }

        JOptionPane.showMessageDialog(null,
            "El historial no está disponible para esta cuenta.\n\n" +
            "Saldo actual: $" + c.getCuenta().getSaldo());
    }

    private void verHistorialGlobal() {
        if (Main.historialGlobal.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay movimientos registrados.");
            return;
        }

        StringBuilder sb = new StringBuilder("HISTORIAL GLOBAL DE MOVIMIENTOS\n\n");

        for (Movimiento m : Main.historialGlobal) {
            sb.append(m.toString()).append("\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString());
    }

	    // ------------------ UTIL ------------------

	private String nullSafe(String s) {
        return (s == null) ? "(sin dato)" : s;
    }
}   
