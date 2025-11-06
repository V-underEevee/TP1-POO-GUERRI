import java.time.LocalDate;

import javax.swing.JOptionPane;

public class Empleado extends Usuario {
	
	private LocalDate fechaInicio;

	    public Empleado(String mail, String contr, LocalDate fechaInicio) {
	        super(mail, contr, Rol.EMPLEADO);
	        this.fechaInicio = fechaInicio;
	    }

	    public LocalDate getFechaInicio() {
	        return fechaInicio;
	    }

	    public void setFechaInicio(LocalDate fechaInicio) {
	        this.fechaInicio = fechaInicio;
	    }

	    @Override
	    public String toString() {
	        return "Empleado [fechaInicio=" + fechaInicio + ", toString()=" + super.toString() + "]";
	    }

	    public void menu() {
	        boolean salir = false;
	        while (!salir) {
	            String[] opciones = {
	                "Dar de baja cajero",
	                "Reponer dinero en cajero",
	                "Ver historial del sistema",
	                "Salir"
	            };

	            int opcion = JOptionPane.showOptionDialog(
	                null,
	                "Menú de Empleado",
	                "Empleado",
	                JOptionPane.DEFAULT_OPTION,
	                JOptionPane.INFORMATION_MESSAGE,
	                null,
	                opciones,
	                opciones[0]
	            );

	            switch (opcion) {
	                case 0:
	                    darDeBajaCajero();
	                    break;
	                case 1:
	                    reponerDineroCajero();
	                    break;
	                case 2:
	                    verHistorialSistema();
	                    break;
	                default:
	                    salir = true;
	                    break;
	            }
	        }
	    }

	    private void darDeBajaCajero() {
	        JOptionPane.showMessageDialog(
	            null,
	            "Función para dar de baja cajero aún no implementada."
	        );
	        // integrar lógica de cajeros automáticos
	    }

	    private void reponerDineroCajero() {
	        String monto = JOptionPane.showInputDialog("Ingrese monto a reponer en cajero:");
	        try {
	            int cantidad = Integer.parseInt(monto);
	            // simulación de lógica
	            JOptionPane.showMessageDialog(null, "Se ha repuesto $" + cantidad + " en el cajero.");
	        } catch (NumberFormatException e) {
	            JOptionPane.showMessageDialog(null, "Monto no válido. Intente nuevamente.");
	        }
	    }

	    private void verHistorialSistema() {
	        JOptionPane.showMessageDialog(
	            null,
	            "Función para ver historial del sistema aún no implementada."
	        );

	    }
}
