import java.time.LocalDate;

public class Tarjeta {
	
	    private String numero;
	    private String tipo; // Crédito o débito
	    private String banco;
	    private LocalDate fechaVencimiento;

	    public Tarjeta(String numero, String tipo, String banco, LocalDate fechaVencimiento) {
	        this.numero = numero;
	        this.tipo = tipo;
	        this.banco = banco;
	        this.fechaVencimiento = fechaVencimiento;
	    }

	    @Override
	    public String toString() {
	        return "Tarjeta [numero=" + numero + ", tipo=" + tipo + ", banco=" + banco + ", vence=" + fechaVencimiento + "]";
	    }
	}


