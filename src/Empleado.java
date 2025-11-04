import java.time.LocalDate;

import javax.swing.JOptionPane;

public class Empleado extends Usuario {
	
	private LocalDate fechaInicio;

	    public Empleado(String mail, String contr, LocalDate fechaInicio) {
	        super(mail, contr, Rol.Empleado);
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
	        do {
	            String[] opciones = this.getRol().getOpciones();
	            int opcion = JOptionPane.showOptionDialog(null, "Menú de Empleado", "Empleado", 
	                                                      JOptionPane.DEFAULT_OPTION, 
	                                                      JOptionPane.INFORMATION_MESSAGE, 
	                                                      null, opciones, opciones[0]);

	            switch (opcion) {
	                case 0: // Dar de baja cajero
	                    JOptionPane.showMessageDialog(null, "Función de dar de baja cajero no implementada aún.");
	                    break;
	                case 1: // Reponer dinero
	                    JOptionPane.showMessageDialog(null, "Función de reponer dinero no implementada aún.");
	                    break;
	                default:
	                    salir = true;
	                    break;
	            }
	        } while (!salir);
	    }
}
