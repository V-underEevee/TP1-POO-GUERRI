import java.util.ArrayList;

public class CuentaInversion {
	private double saldo;
    private ArrayList<String> historial;

    public CuentaInversion() {
        this.saldo = 0.0;
        this.historial = new ArrayList<>();
    }

    public double getSaldo() {
        return saldo;
    }

    public ArrayList<String> getHistorial() {
        return historial;
    }

    // -------- Depositar --------
    public void depositar(double monto) {
        saldo += monto;
        historial.add("Depósito: $" + monto + " | Saldo: $" + saldo);
    }

    // -------- Simular día --------
    public void simularDia() {
        if (saldo <= 0) {
            historial.add("No se puede simular: saldo en inversión = 0.");
            return;
        }

        double tasa = generarTasaAleatoria(); // -5% a +5%
        double rendimiento = saldo * tasa;
        saldo += rendimiento;

        String mov = String.format(
                "Tasa: %.2f%% | Rendimiento: $%.2f | Saldo final: $%.2f",
                tasa * 100, rendimiento, saldo
        );

        historial.add(mov);
    }

    private double generarTasaAleatoria() {
        return (Math.random() * 0.10) - 0.05;
    }

    // -------- Convertir historial a String --------
    public String historialToString() {
        if (historial.isEmpty()) {
            return "No hay movimientos de inversión aún.";
        }

        StringBuilder sb = new StringBuilder("Historial de Inversiones:\n\n");

        for (String mov : historial) {
            sb.append("- ").append(mov).append("\n");
        }

        return sb.toString();
    }
    
 // Retirar dinero de la inversión
    public boolean retirar(double monto) {
        if (monto <= 0) return false;
        if (monto > saldo) return false;

        saldo -= monto;
        historial.add("Retiro: $" + monto + " | Saldo: $" + saldo);
        return true;
    }

    // Reinvertir todo (simboliza "nuevo ciclo de inversión")
    public void reinvertirTodo() {
        historial.add("Reinversión: se reinvierte el saldo total ($" + saldo + ")");
        // No cambia el saldo, pero marca un "nuevo ciclo"
    }
    
}

