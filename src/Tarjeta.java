public class Tarjeta {
	
	private String numero;
    private String vencimiento;
    private String tipo; // "Débito" o "Crédito"

    public Tarjeta(String numero, String vencimiento, String tipo) {
        this.numero = numero;
        this.vencimiento = vencimiento;
        this.tipo = tipo;
    }

    public String getNumero() { return numero; }
    public String getVencimiento() { return vencimiento; }
    public String getTipo() { return tipo; }

    
    public String toString() {
        return tipo + " - " + numero + " - Vence: " + vencimiento;
    }
}
