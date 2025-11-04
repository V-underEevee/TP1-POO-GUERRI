import java.time.LocalDate;

public class Empleado extends Usuario {
	
	private LocalDate fechaInicio;

	public Empleado(String mail, String contr, LocalDate fechaInicio) {
		super(mail, contr, Rol.Empleado);
		this.fechaInicio = fechaInicio;
	}
}
