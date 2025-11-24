import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Cliente extends Usuario {
	
    private Cuenta cuenta;
    private CuentaInversion inversion;
    private ArrayList<Tarjeta> tarjetas;

    public Cliente(String mail, String contr, String alias, Rol rol, Cuenta cuenta) {
        super(mail, contr, alias, rol);
        this.cuenta = cuenta;
        this.tarjetas = new ArrayList<>();
        this.inversion = new CuentaInversion();
    }


    public Cuenta getCuenta() {
        return cuenta;
    }

    public void agregarTarjeta(Tarjeta tarjeta) {
        tarjetas.add(tarjeta);
    }

    
    public void menu() {

        String[] opciones = {
            "Billetera",
            "Inversiones",
            "Tarjetas",
            "Cerrar sesión"
        };

        boolean activo = true;

        while (activo) {

            int opcion = JOptionPane.showOptionDialog(
                    null,
                    "Menú de Cliente (" + alias + ")",
                    "Cliente",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            if (opcion == JOptionPane.CLOSED_OPTION || opcion == -1 || opcion == 3) {
                return; // cerrar sesión
            }

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
                    break;
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
                if (dep > 0) {
                    cuenta.depositar(dep);
                    Main.historialGlobal.add(new Movimiento("Depósito", alias, dep));
                    JOptionPane.showMessageDialog(null, "Depósito realizado.");
                }
                break;

            case 3:
                double ext = Validaciones.IngresarInt("Monto a extraer:");
                if (ext > 0) {
                    if (cuenta.retirar(ext)) {
                        Main.historialGlobal.add(new Movimiento("Extracción", alias, ext));
                        JOptionPane.showMessageDialog(null, "Extracción realizada.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Fondos insuficientes.");
                    }
                }
                break;

            case 4:
            default:
                volver = true;
                break;
        }

        }
    }

    private void transferirDinero() {

        String aliasDestino = JOptionPane.showInputDialog("Ingrese alias del destinatario:");
        if (aliasDestino == null) return;

        Usuario destino = Usuario.buscarPorAlias(aliasDestino);

        // instanceof clásico
        if (destino instanceof Cliente) {

            Cliente cliDestino = (Cliente) destino; // CAST clásico

            double monto = Validaciones.IngresarInt("Ingrese monto a transferir:");
            if (monto <= 0) return;

            boolean ok = cuenta.transferir(
                    cliDestino.getCuenta(),
                    monto,
                    this.alias,
                    cliDestino.alias
            );

            if (ok) {
                Main.historialGlobal.add(
                    new Movimiento("Transferencia", this.alias + " -> " + cliDestino.alias, monto)
                );
                JOptionPane.showMessageDialog(null, "Transferencia exitosa.");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo realizar la transferencia.");
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
                "Retirar a la billetera",
                "Reinvertir ganancias",
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
                if (monto > 0 && cuenta.retirar(monto)) {
                    inversion.depositar(monto);
                    JOptionPane.showMessageDialog(null, "Inversión realizada.");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo invertir.");
                }
                break;

            case 1:
                inversion.simularDia();
                JOptionPane.showMessageDialog(null,
                        "Simulación realizada.\nNuevo saldo inversión: $" + inversion.getSaldo());
                break;

            case 2:
                JOptionPane.showMessageDialog(null, inversion.historialToString());
                break;

            case 3:
                double ret = Validaciones.IngresarInt("Monto a retirar de inversiones:");
                if (ret > 0 && inversion.retirar(ret)) {
                    cuenta.depositar(ret);
                    JOptionPane.showMessageDialog(null, "Retiro exitoso.");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo retirar.");
                }
                break;

            case 4:
                inversion.reinvertirTodo();
                JOptionPane.showMessageDialog(null, "Reinversión registrada.");
                break;

            case 5:
            default:
                activo = false;
                break;
        }

        }
    }

    // ---------------- TARJETAS (sin implementar aún) -----------------
    private void menuTarjetas() {
        String[] opciones = {
                "Ver tarjetas",
                "Agregar tarjeta",
                "Eliminar tarjeta",
                "Volver"
        };

        boolean activo = true;

        while (activo) {

            int op = JOptionPane.showOptionDialog(
                    null,
                    "Gestión de Tarjetas",
                    "Tarjetas",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            switch (op) {

            case 0:
                if (tarjetas.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No tenés tarjetas cargadas.");
                } else {
                    StringBuilder sb = new StringBuilder("Tus tarjetas:\n\n");
                    for (Tarjeta t : tarjetas) sb.append("- ").append(t).append("\n");
                    JOptionPane.showMessageDialog(null, sb.toString());
                }
                break;

            case 1:
                String num = JOptionPane.showInputDialog("Número de tarjeta:");
                if (num == null) break;

                String venc = JOptionPane.showInputDialog("Vencimiento (MM/AA):");
                if (venc == null) break;

                String[] tipos = {"Débito", "Crédito"};
                int tipoIndex = JOptionPane.showOptionDialog(
                        null,
                        "Tipo de tarjeta:",
                        "Tipo",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        tipos,
                        tipos[0]
                );

                if (tipoIndex >= 0) {
                    tarjetas.add(new Tarjeta(num, venc, tipos[tipoIndex]));
                    JOptionPane.showMessageDialog(null, "Tarjeta agregada.");
                }
                break;

            case 2:
                if (tarjetas.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No hay tarjetas para eliminar.");
                    break;
                }

                String[] lista = new String[tarjetas.size()];
                for (int i = 0; i < tarjetas.size(); i++) {
                    lista[i] = tarjetas.get(i).toString();
                }

                int elim = JOptionPane.showOptionDialog(
                        null,
                        "Elija tarjeta a eliminar:",
                        "Eliminar",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.WARNING_MESSAGE,
                        null,
                        lista,
                        lista[0]
                );

                if (elim >= 0) {
                    tarjetas.remove(elim);
                    JOptionPane.showMessageDialog(null, "Tarjeta eliminada.");
                }
                break;

            case 3:
            default:
                activo = false;
                break;
        }

        }
    }

	
}
