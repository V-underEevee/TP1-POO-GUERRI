import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Cliente extends Usuario {
	
    private Cuenta cuenta;
    private CuentaInversion inversion;
    private ArrayList<Tarjeta> tarjetas;

    public Cliente(String mail, String contr, Rol rol, Cuenta cuenta) {
        super(mail, contr, rol);
        this.cuenta = cuenta;
        this.tarjetas = new ArrayList<>();
        this.inversion = new CuentaInversion(); // la cuenta de inversión comienza vacía
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void agregarTarjeta(Tarjeta tarjeta) {
        tarjetas.add(tarjeta);
    }

    @Override
    public void menu() {
        boolean salir = false;

        String[] opciones = {
                "Billetera",
                "Inversiones",
                "Tarjetas",
                "Salir"
        };

        while (!salir) {

            int opcion = JOptionPane.showOptionDialog(
                    null,
                    "Menú de Cliente",
                    "Cliente",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            switch (opcion) {
                case 0:
                    menuBilletera();
                    break;
                case 1:
                    menuInversiones();
                    break;
                case 2:
                    menuTarjetas();
                    break;
                default:
                    salir = true;
            }
        }
    }

    // ---------------- BILLETERA ----------------

    private void menuBilletera() {

        String[] opciones = {"Ver saldo", "Transferir", "Depositar", "Extraer", "Volver"};
        boolean volver = false;

        while (!volver) {

            int opcion = JOptionPane.showOptionDialog(
                    null,
                    "Menú Billetera",
                    "Billetera",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            switch (opcion) {

                case 0:
                    JOptionPane.showMessageDialog(null, "Tu saldo es: $" + cuenta.getSaldo());
                    break;

                case 1:
                    transferirDinero();
                    break;

                case 2:
                    double dep = Validaciones.IngresarInt("Monto a depositar:");
                    cuenta.depositar(dep);
                    break;

                case 3:
                    double ext = Validaciones.IngresarInt("Monto a extraer:");
                    cuenta.retirar(ext);
                    break;

                default:
                    volver = true;
            }
        }
    }

    private void transferirDinero() {

        String aliasDestino =
                JOptionPane.showInputDialog("Ingrese alias del destinatario:");

        Usuario destino = Usuario.buscarPorAlias(aliasDestino);

        if (destino instanceof Cliente) {

            double monto = Validaciones.IngresarInt("Ingrese monto a transferir:");

            if (cuenta.transferir(((Cliente) destino).getCuenta(), monto)) {
                JOptionPane.showMessageDialog(null, "Transferencia exitosa.");
            }

        } else {
            JOptionPane.showMessageDialog(null,
                    "Alias inválido o no corresponde a un cliente.");
        }
    }

    // --------------- INVERSIONES -------------------

    private void menuInversiones() {

        boolean activo = true;

        String[] opciones = {
                "Invertir dinero",
                "Simular 1 día",
                "Ver historial",
                "Volver"
        };

        while (activo) {

            int op = JOptionPane.showOptionDialog(
                    null,
                    "Cuenta de Inversiones",
                    "Inversiones",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            switch (op) {
                case 0:
                    double monto = Validaciones.IngresarInt("Monto a invertir:");

                    if (cuenta.retirar(monto)) {
                        inversion.depositar(monto);
                        JOptionPane.showMessageDialog(null, "Inversión realizada.");
                    }
                    break;

                case 1:
                    inversion.simularDia();
                    JOptionPane.showMessageDialog(null,
                            "Simulación realizada.\nNuevo saldo: $" + inversion.getSaldo());
                    break;

                case 2:
                    JOptionPane.showMessageDialog(null, inversion.historialToString());
                    break;

                default:
                    activo = false;
            }
        }
    }

    // ---------------- TARJETAS (sin implementar aún) -----------------
    private void menuTarjetas() {
        JOptionPane.showMessageDialog(null,
                "Gestión de tarjetas aún no implementada.");
    }

	
}
