
public class CuentaInversion {
	private double saldo;
    private List<String> historial;
    
    public CuentaInversion() {
        this.saldo = 0.0;
        this.historial = new ArrayList<>();
    }

    public double getSaldo() {
        return saldo;
    }

    public List<String> getHistorial() {
        return historial;
    }

    public void depositar(double monto) {
        saldo += monto;
        historial.add("Depósito inicial: $" + monto + " | Saldo: $" + saldo);
    }

    public void simularDia() {
        double tasa = generarTasaAleatoria();   // entre -5% y +5%
        double rendimiento = saldo * tasa;
        saldo += rendimiento;

        String mov = String.format(
                "Tasa: %.2f%% | Rendimiento: $%.2f | Saldo final: $%.2f",
                tasa * 100, rendimiento, saldo
        );

        historial.add(mov);
    }

    private double generarTasaAleatoria() {
        // genera entre -0.05 y +0.05
        return (Math.random() * 0.10) - 0.05;
    }
}
