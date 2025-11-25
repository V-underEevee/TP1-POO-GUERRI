
public class Cajero {
	private int id;
    private boolean habilitado;
    private double dinero;

    public Cajero(int id, double dineroInicial) {
        this.id = id;
        this.dinero = dineroInicial;
        this.habilitado = true;  // por defecto habilitado
    }

    public int getId() {
        return id;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void habilitar() {
        this.habilitado = true;
    }

    public void deshabilitar() {
        this.habilitado = false;
    }

    public double getDinero() {
        return dinero;
    }

    public void vaciar() {
        this.dinero = 0;
    }

    public void rellenar(double monto) {
        this.dinero += monto;
    }

    @Override
    public String toString() {
        return "Cajero " + id + " | Estado: " +
                (habilitado ? "HABILITADO" : "DESHABILITADO") +
                " | Dinero: $" + dinero;
    }
}
