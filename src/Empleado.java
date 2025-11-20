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

	    // ------------------ MENÚ ------------------
	    public void menu() {
	        String[] opciones = {
	            "Ver lista de clientes",
	            "Buscar cliente",
	            "Depositar a un cliente",
	            "Extraer de un cliente",
	            "Ver movimientos de un cliente",
	            "Ver historial global",
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

	            // Si el usuario cerró la ventana (X) showOptionDialog devuelve -1
	            if (op == JOptionPane.CLOSED_OPTION || op == -1) {
	                break;
	            }

	            switch (op) {
	                case 0:
	                    verListaClientes();
	                    break;
	                case 1:
	                    buscarCliente();
	                    break;
	                case 2:
	                    depositarACliente();
	                    break;
	                case 3:
	                    extraerDeCliente();
	                    break;
	                case 4:
	                    verMovimientosCliente();
	                    break;
	                case 5:
	                	verHistorialGlobal();
	                	break;
	                default:
	                    activo = false;
	                    break;
	            }
	        }
	    }

	    // ------------------ HELPERS ------------------

	    // Muestra listado completo de clientes usando Main.listaUsuarios
	    private void verListaClientes() {
	        StringBuilder listado = new StringBuilder("Listado de clientes:\n\n");
	        boolean hayClientes = false;

	        for (Usuario u : Main.listaUsuarios) {
	            if (u instanceof Cliente) {
	                Cliente c = (Cliente) u;
	                hayClientes = true;
	                listado.append("- Alias: ").append(nullSafe(c.getAlias()))
	                       .append(" | Email: ").append(nullSafe(c.getMail()))
	                       .append(" | Cuenta Nº: ").append(c.getCuenta().getNumeroCuenta())
	                       .append(" | Saldo: $").append(String.format("%.2f", c.getCuenta().getSaldo()))
	                       .append("\n");
	            }
	        }

	        if (!hayClientes) {
	            JOptionPane.showMessageDialog(null, "No hay clientes registrados.");
	        } else {
	            JOptionPane.showMessageDialog(null, listado.toString());
	        }
	    }

	    // Pide alias o mail y devuelve el Cliente (o null si no existe / canceló)
	    private Cliente pedirCliente() {
	        String dato = JOptionPane.showInputDialog("Ingrese alias o email del cliente:");

	        if (dato == null) return null; // canceló
	        dato = dato.trim();
	        if (dato.isEmpty()) {
	            JOptionPane.showMessageDialog(null, "Entrada vacía. Operación cancelada.");
	            return null;
	        }

	        Usuario encontrado = Usuario.buscarPorAlias(dato);
	        if (encontrado == null) {
	            encontrado = Usuario.buscarPorMail(dato);
	        }

	        if (encontrado instanceof Cliente) {
	            return (Cliente) encontrado;
	        } else {
	            JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
	            return null;
	        }
	    }

	    // Busca y muestra datos básicos del cliente
	    private void buscarCliente() {
	        Cliente c = pedirCliente();
	        if (c == null) return;

	        String info = "Cliente encontrado:\n\n" +
	                      "Alias: " + nullSafe(c.getAlias()) + "\n" +
	                      "Email: " + nullSafe(c.getMail()) + "\n" +
	                      "Cuenta Nº: " + c.getCuenta().getNumeroCuenta() + "\n" +
	                      "Saldo: $" + String.format("%.2f", c.getCuenta().getSaldo());

	        JOptionPane.showMessageDialog(null, info);
	    }

	    // Deposita en la cuenta de un cliente (usando Validaciones)
	    private void depositarACliente() {
	        Cliente c = pedirCliente();
	        if (c == null) return;

	        double monto = Validaciones.IngresarInt("Ingrese monto a depositar:");
	        if (monto <= 0) {
	            JOptionPane.showMessageDialog(null, "Operación cancelada o monto inválido.");
	            return;
	        }

	        c.getCuenta().depositar(monto);

	        // Registrar movimiento correctamente
	        Main.historialGlobal.add(
	            new Movimiento("Depósito", c.getAlias(), monto)
	        );

	        JOptionPane.showMessageDialog(null,
	            "Depósito de $" + String.format("%.2f", monto) +
	            " realizado a " + c.getAlias());
	    }


	    // Extrae de la cuenta de un cliente
	    private void extraerDeCliente() {
	        Cliente c = pedirCliente();
	        if (c == null) return;

	        double monto = Validaciones.IngresarInt("Ingrese monto a extraer:");
	        if (monto <= 0) {
	            JOptionPane.showMessageDialog(null, "Operación cancelada o monto inválido.");
	            return;
	        }

	        boolean ok = c.getCuenta().retirar(monto);

	        if (ok) {

	            Main.historialGlobal.add(
	                new Movimiento("Extracción", c.getAlias(), monto)
	            );

	            JOptionPane.showMessageDialog(null,
	                "Extracción de $" + String.format("%.2f", monto) + " realizada.");

	        } else {
	            JOptionPane.showMessageDialog(null,
	                "Fondos insuficientes. Operación cancelada.");
	        }
	    
	    }

	    // Ver movimientos del cliente (si Cuenta tiene historial showHistorial)
	    private void verMovimientosCliente() {
	        Cliente c = pedirCliente();
	        if (c == null) return;

	        // Si tu Cuenta tiene método getMovimientos() o getHistorial(), lo usamos.
	        // Primero verificamos por reflexión segura (evita compilar si no existe).
	        try {
	            // Suponemos que Cuenta tiene getHistorial() -> List<String>
	            Object histObj = c.getCuenta().getClass().getMethod("getHistorial").invoke(c.getCuenta());
	            if (histObj instanceof java.util.List) {
	                @SuppressWarnings("unchecked")
	                java.util.List<String> hist = (java.util.List<String>) histObj;
	                if (hist.isEmpty()) {
	                    JOptionPane.showMessageDialog(null, "No hay movimientos registrados para esta cuenta.");
	                } else {
	                    StringBuilder sb = new StringBuilder("Historial de movimientos:\n\n");
	                    for (String m : hist) sb.append(m).append("\n");
	                    JOptionPane.showMessageDialog(null, sb.toString());
	                }
	                return;
	            }
	        } catch (NoSuchMethodException nsme) {
	            // getHistorial no existe: seguimos con fallback
	        } catch (Exception e) {
	            // si algo falla, mostramos fallback
	        }

	        // Fallback: mostrar información básica
	        String info = "Movimientos no disponibles (Cuenta no implementa historial).\n\n" +
	                      "Saldo actual: $" + String.format("%.2f", c.getCuenta().getSaldo()) + "\n" +
	                      "Tarjetas asociadas: " + c.getCuenta().getTarjetas().size();

	        JOptionPane.showMessageDialog(null, info);
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
	    // Evita NPEs con alias/mail nulos
	    private String nullSafe(String s) {
	        return (s == null) ? "(sin dato)" : s;
	    }
	
}   
