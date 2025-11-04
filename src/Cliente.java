import javax.swing.JOptionPane;

public class Cliente extends Usuario {
	
	private Cuenta cuenta;  // Cada cliente tiene una cuenta bancaria

    public Cliente(String mail, String contr, Rol rol, Cuenta cuenta) {
        super(mail, contr, rol);
        this.cuenta = cuenta;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public String toString() {
        return "Cliente [cuenta=" + cuenta + ", toString()=" + super.toString() + "]";
    }

    public void menu() {
        boolean salir = false;
        do {
            String[] opciones = this.getRol().getOpciones();
            int opcion = JOptionPane.showOptionDialog(null, "Bienvenido cliente", "Menú de Cliente", 
                                                      JOptionPane.DEFAULT_OPTION, 
                                                      JOptionPane.INFORMATION_MESSAGE, 
                                                      null, opciones, opciones[0]);
            
            switch (opcion) {
                case 0: // Ver saldo
                    JOptionPane.showMessageDialog(null, "Tu saldo actual es: $" + cuenta.getSaldo());
                    break;
                case 1: // Depositar
                    String montoDep = JOptionPane.showInputDialog("Ingrese monto a depositar:");
                    try {
                        double dep = Double.parseDouble(montoDep);
                        cuenta.depositar(dep);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Monto no válido.");
                    }
                    break;
                case 2: // Retirar
                    String montoRet = JOptionPane.showInputDialog("Ingrese monto a retirar:");
                    try {
                        double ret = Double.parseDouble(montoRet);
                        cuenta.retirar(ret);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Monto no válido.");
                    }
                    break;
                case 3: // Transferir
                    JOptionPane.showMessageDialog(null, "Función de transferir aún no implementada.");
                    break;
                default:
                    salir = true;
                    break;
            }
        } while (!salir);
    }

}
