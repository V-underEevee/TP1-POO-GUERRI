import java.util.LinkedList;

public class Cuenta {
	private int numeroCuenta;
    private double saldo;
    private LinkedList<Tarjeta> tarjetas;

    public Cuenta() {
        this.numeroCuenta = generarNumeroCuenta();
        this.saldo = 0;
        this.tarjetas = new LinkedList<>();
    }

    private int generarNumeroCuenta() {
        return (int)(Math.random() * 9000) + 1000;
    }

    public void depositar(double monto) {
        if (monto > 0) {
            saldo += monto;
            System.out.println("Depósito realizado. Nuevo saldo: " + saldo);
        } else {
            System.out.println("El monto a depositar debe ser positivo.");
        }
    }

    public boolean retirar(double monto) {
        if (monto > 0) {
            if (monto <= saldo) {
                saldo -= monto;
                System.out.println("Retiro realizado. Nuevo saldo: " + saldo);
                return true;
            } else {
                System.out.println("Fondos insuficientes. Operación cancelada.");
                return false;
            }
        } else {
            System.out.println("El monto a retirar debe ser positivo.");
            return false;
        }
    }

    public boolean transferir(Cuenta cuentaDestino, double monto) {
        if (cuentaDestino == null) {
            System.out.println("Cuenta destino inexistente.");
            return false;
        }
        if (this.retirar(monto)) {
            cuentaDestino.depositar(monto);
            System.out.println("Transferencia realizada con éxito.");
            return true;
        } else {
            System.out.println("No se pudo realizar la transferencia.");
            return false;
        }
    }

    public void agregarTarjeta(Tarjeta tarjeta) {
        if (tarjeta != null) {
            tarjetas.add(tarjeta);
            System.out.println("Tarjeta agregada con éxito.");
        } else {
            System.out.println("No se pudo agregar la tarjeta (objeto null).");
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

    @Override
    public String toString() {
        return "Cuenta [numeroCuenta=" + numeroCuenta + ", saldo=" + saldo + ", tarjetas=" + tarjetas + "]";
    }
}
