
public enum Rol {


	Admin(new String[] {"Crear empleado","Listar usuarios"}),
	Empleado(new String[] {"Dar de baja cajero","Reponer dinero"}),
	Cliente(new String[] {"Transferir","Depositar","Retirar"});
	
	private String[] opciones;

	private Rol(String[] opciones) {
		this.opciones = opciones;
	}

	public String[] getOpciones() {
		return opciones;
	}

	public void setOpciones(String[] opciones) {
		this.opciones = opciones;
	}
	

}