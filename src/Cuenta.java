import java.util.ArrayList;
import java.util.LinkedList;

public class Cuenta {
	private int numeroCuenta;
    private double saldo;
    private LinkedList<Tarjeta> tarjetas;
    private ArrayList<String> historial;  // <-- historial interno

    public Cuenta() {
        this.numeroCuenta = generarNumeroCuenta();
        this.saldo = 0;
        this.tarjetas = new LinkedList<>();
        this.historial = new ArrayList<>();
    }

    private int generarNumeroCuenta() {
        return (int)(Math.random() * 9000) + 1000;
    }

    public void depositar(double monto) {
        if (monto > 0) {
            saldo += monto;
            historial.add("Depósito: $" + String.format("%.2f", monto) +
                          " | Saldo: $" + String.format("%.2f", saldo));
        } else {
            historial.add("Intento de depósito inválido: $" + monto);
        }
    }

    public boolean retirar(double monto) {
        if (monto <= 0) {
            historial.add("Intento de extracción inválida: $" + monto);
            return false;
        }
        if (monto > saldo) {
            historial.add("Extracción fallida por fondos insuficientes: $" + monto +
                          " | Saldo: $" + String.format("%.2f", saldo));
            return false;
        }
        saldo -= monto;
        historial.add("Extracción: $" + String.format("%.2f", monto) +
                      " | Saldo: $" + String.format("%.2f", saldo));
        return true;
    }

    public boolean transferir(Cuenta cuentaDestino, double monto, String aliasOrigen, String aliasDestino) {
        if (cuentaDestino == null) {
            historial.add("Transferencia fallida: cuenta destino inexistente.");
            return false;
        }
        if (this.retirar(monto)) {
            cuentaDestino.depositar(monto);
            historial.add("Transferencia enviada a " + aliasDestino + ": $" +
                          String.format("%.2f", monto));
            cuentaDestino.historial.add("Transferencia recibida de " + aliasOrigen + ": $" +
                                        String.format("%.2f", monto));
            return true;
        }
        return false;
    }

    public void agregarTarjeta(Tarjeta tarjeta) {
        if (tarjeta != null) {
            tarjetas.add(tarjeta);
            historial.add("Tarjeta agregada: " + tarjeta.toString());
        }
    }

    public LinkedList<Tarjeta> getTarjetas() {
        return tarjetas;
    }

    public double getSaldo() {
        return saldo;
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public ArrayList<String> getHistorial() {
        return historial;
    }

    @Override
    public String toString() {
        return "Cuenta [numeroCuenta=" + numeroCuenta + ", saldo=" + saldo + "]";
    }
}
