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
                case 3: // retirar
                    double ret = Validaciones.IngresarInt("Monto a retirar:");
                    if (inversion.retirar(ret)) {
                        cuenta.depositar(ret);
                        JOptionPane.showMessageDialog(null, "Retiro exitoso. El dinero volvió a tu billetera.");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo retirar.");
                    }
                    break;

                case 4: // reinvertir
                    inversion.reinvertirTodo();
                    JOptionPane.showMessageDialog(null, "Reinversión realizada.");
                    break;
                case 5:
                	return;
               

                default:
                    activo = false;
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

                case 0: // ver tarjetas
                    if (tarjetas.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No tenés tarjetas cargadas.");
                    } else {
                        StringBuilder sb = new StringBuilder("Tus tarjetas:\n\n");
                        for (Tarjeta t : tarjetas) {
                            sb.append("- ").append(t).append("\n");
                        }
                        JOptionPane.showMessageDialog(null, sb.toString());
                    }
                    break;

                case 1: // agregar
                    String num = JOptionPane.showInputDialog("Número de tarjeta:");
                    String venc = JOptionPane.showInputDialog("Vencimiento (MM/AA):");

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

                case 2: // eliminar
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

                default:
                    activo = false;
            }
        }
    }

	
}
