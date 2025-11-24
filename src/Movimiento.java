
public class Movimiento {


	private String tipo;      // Depósito, Extracción, Transferencia...
    private String usuario;   // alias del cliente
    private double monto;
    private String fecha;

    public Movimiento(String tipo, String usuario, double monto) {
        this.tipo = tipo;
        this.usuario = usuario;
        this.monto = monto;
        this.fecha = java.time.LocalDateTime.now().toString();
    }

    public String getTipo() { return tipo; }
    public String getUsuario() { return usuario; }
    public double getMonto() { return monto; }
    public String getFecha() { return fecha; }

    @Override
    public String toString() {
        return "[" + fecha + "] " + tipo + " — " + usuario + " — $" + monto;
    }
	

}
