import javax.swing.JOptionPane;

public class Validaciones {
	
	// Pide un String no vacío. Si el usuario cancela, devuelve null.
    public static String ingresarString(String mensaje) {
        String dato;
        do {
            dato = JOptionPane.showInputDialog(null, mensaje);
            if (dato == null) return null;           
            if (dato.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El campo no puede quedar vacío. Intente nuevamente.");
            } else {
                return dato.trim();
            }
        } while (true);
    }

    // Pide un número double positivo. Si cancela devuelve null.
    public static Double ingresarDoublePositivo(String mensaje) {
        String dato;
        do {
            dato = JOptionPane.showInputDialog(null, mensaje);
            if (dato == null) return null;
            dato = dato.trim();
            if (dato.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un número. Intente nuevamente.");
                continue;
            }
            try {
                double valor = Double.parseDouble(dato);
                if (valor <= 0) {
                    JOptionPane.showMessageDialog(null, "Ingrese un monto mayor a 0.");
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Formato inválido. Ingrese solo números (ej: 1234.56).");
            }
        } while (true);
    }

    // Versión usada en tu código, devuelve double > 0 (0 si canceló)
    public static double IngresarInt(String mensaje) {
        while (true) {
            String dato = JOptionPane.showInputDialog(null, mensaje);
            if (dato == null) {
                return 0.0;
            }
            dato = dato.trim().replace(',', '.');
            if (dato.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un número. Intente nuevamente.");
                continue;
            }
            try {
                double valor = Double.parseDouble(dato);
                if (valor <= 0) {
                    JOptionPane.showMessageDialog(null, "Ingrese un monto mayor a 0.");
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Formato inválido. Ingrese solo números.");
            }
        }
    }

    public static int IngresarIntPositivo(String mensaje) {
        while (true) {
            String dato = JOptionPane.showInputDialog(null, mensaje);
            if (dato == null) return 0;
            dato = dato.trim();
            if (dato.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un número entero. Intente nuevamente.");
                continue;
            }
            try {
                int valor = Integer.parseInt(dato);
                if (valor <= 0) {
                    JOptionPane.showMessageDialog(null, "Ingrese un entero mayor a 0.");
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Formato inválido. Ingrese solo números enteros.");
            }
        }
    }
}

    

