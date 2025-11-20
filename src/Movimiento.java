
public class Movimiento {


	    private String tipo;      // Depósito, Extracción, Transferencia
	    private String cliente;   // Alias o mail d l cliente
	    private double monto;
	    private String fecha;

	    public Movimiento(String tipo, String cliente, double monto) {
	        this.tipo = tipo;
	        this.cliente = cliente;
	        this.monto = monto;
	        this.fecha = java.time.LocalDateTime.now().toString();
	    }

	    @Override
	    public String toString() {
	        return "[" + fecha + "] " + tipo + " - Cliente: " + cliente + " - Monto: $" + monto;
	    }
	

}
