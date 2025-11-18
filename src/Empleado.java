import java.time.LocalDate;

import javax.swing.JOptionPane;

public class Empleado extends Usuario {
	
    private LocalDate fechaInicio;

    public Empleado(String mail, String contr) {
        super(mail, contr, "empleado", Rol.EMPLEADO);
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

    // ======================================================
    //                MENÚ DEL EMPLEADO
    // ======================================================
    public void menu() {

        String[] opciones = {
            "Ver lista de clientes",
            "Buscar cliente",
            "Depositar a un cliente",
            "Extraer de un cliente",
            "Ver movimientos de un cliente",
            "Volver"
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

            switch (op) {

                case 0 -> verListaClientes();
                case 1 -> buscarCliente();
                case 2 -> depositarACliente();
                case 3 -> extraerDeCliente();
                case 4 -> verMovimientosCliente();
                default -> activo = false;
            }
        }
    }

    // ======================================================
    //           FUNCIONES PARA GESTIONAR CLIENTES
    // ======================================================

    private void verListaClientes() {
        StringBuilder sb = new StringBuilder("Listado de clientes:\n\n");

        for (Usuario u : Main.listaUsuarios) {
            if (u instanceof Cliente) {
                sb.append("- ")
                  .append(u.getAlias())
                  .append(" | ")
                  .append(u.getMail())
                  .append("\n");
            }
        }

        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private Cliente pedirCliente() {
        String dato = JOptionPane.showInputDialog("Ingrese mail o alias del cliente:");

        if (dato == null || dato.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Entrada inválida.");
            return null;
        }

        Usuario u = Usuario.buscarPorAlias(dato);

        if (u == null)
            u = Usuario.buscarPorMail(dato);

        if (u instanceof Cliente)
            return (Cliente) u;

        JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
        return null;
    }

    private void buscarCliente() {
        Cliente c = pedirCliente();
        if (c == null) return;

        JOptionPane.showMessageDialog(null,
                "Cliente encontrado:\nAlias: " + c.getAlias() +
                "\nMail: " + c.getMail() +
                "\nSaldo: $" + c.getCuenta().getSaldo());
    }

    private void depositarACliente() {
        Cliente c = pedirCliente();
        if (c == null) return;

        double monto = Validaciones.IngresarInt("Monto a depositar:");
        c.getCuenta().depositar(monto);

        JOptionPane.showMessageDialog(null,
                "Depósito realizado a " + c.getAlias());
    }

    private void extraerDeCliente() {
        Cliente c = pedirCliente();
        if (c == null) return;

        double monto = Validaciones.IngresarInt("Monto a extraer:");

        if (c.getCuenta().retirar(monto)) {
            JOptionPane.showMessageDialog(null, "Extracción exitosa.");
        } else {
            JOptionPane.showMessageDialog(null, "Fondos insuficientes.");
        }
    }

    private void verMovimientosCliente() {
        Cliente c = pedirCliente();
        if (c == null) return;

        if (c.getCuenta().getHistorial().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay movimientos.");
            return;
        }

        StringBuilder sb = new StringBuilder("Historial de movimientos:\n\n");

        for (String mov : c.getCuenta().getHistorial()) {
            sb.append(mov).append("\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString());
    }
}