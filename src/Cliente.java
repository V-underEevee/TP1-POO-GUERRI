import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Cliente extends Usuario {
	
	private Cuenta cuenta;  // Cada cliente tiene una cuenta bancaria
	 private ArrayList<Tarjeta> tarjetas;
    


    public Cliente(String mail, String contr, Rol rol, Cuenta cuenta) {
		super(mail, contr, rol);
		this.cuenta = cuenta;
		this.tarjetas = new ArrayList<>();
	}

    public Cuenta getCuenta() {
    	return cuenta;
    }
    
    public void agregarTarjeta(Tarjeta tarjeta) {
    	tarjetas.add(tarjeta);
    }
    

    public void menu() {
        boolean salir = false;
        String[] opciones = {"Billetera", "Tarjetas", "Créditos y Préstamos", "Salir"};

        while (!salir) {
            int opcion = JOptionPane.showOptionDialog(null, "Menú de Cliente", "Cliente",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

            switch (opcion) {
                case 0:
                    menuBilletera();
                    break;
                case 1:
                    menuTarjetas();
                    break;
                case 2:
                    menuCreditosYPrestamos();
                    break;
                default:
                    salir = true;
                    break;
            }
        }
    }

    private void menuBilletera() {
        String[] opciones = {"Ver saldo", "Transferir", "Depositar", "Extraer", "Volver"};
        boolean volver = false;

        while (!volver) {
            int opcion = JOptionPane.showOptionDialog(null, "Menú Billetera", "Billetera",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

            switch (opcion) {
                case 0:
                    JOptionPane.showMessageDialog(null, "Tu saldo es: $" + cuenta.getSaldo());
                    break;
                case 1:
                    transferirDinero();
                    break;
                case 2:
                    String montoDep = JOptionPane.showInputDialog("Ingrese monto a depositar:");
                    cuenta.depositar(Double.parseDouble(montoDep));
                    break;
                case 3:
                    String montoExt = JOptionPane.showInputDialog("Ingrese monto a extraer:");
                    cuenta.retirar(Double.parseDouble(montoExt));
                    break;
                default:
                    volver = true;
            }
        }
    }

    private void menuTarjetas() {
        // INCOMPLETO: submenú para gestionar tarjetas
        JOptionPane.showMessageDialog(null, "Gestión de tarjetas aún no implementada");
    }

    private void menuCreditosYPrestamos() {
        // INCOMPLETO: funcionalidades para préstamos, créditos, inversiones, etc.
        JOptionPane.showMessageDialog(null, "Funcionalidades de crédito aún no implementadas");
    }

    private void transferirDinero() {
        String aliasDestino = JOptionPane.showInputDialog("Ingrese alias del destinatario:");
        Usuario destino = Usuario.buscarPorAlias(aliasDestino); // este método lo implementamos enseguida
        if (destino instanceof Cliente) {
            String monto = JOptionPane.showInputDialog("Ingrese monto a transferir:");
            cuenta.transferir(((Cliente) destino).getCuenta(), Double.parseDouble(monto));
            JOptionPane.showMessageDialog(null, "Transferencia exitosa.");
        } else {
            JOptionPane.showMessageDialog(null, "Alias inválido o perteneciente a un usuario no cliente.");
        }
    }

	
}
