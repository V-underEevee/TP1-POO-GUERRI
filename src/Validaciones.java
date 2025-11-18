import javax.swing.JOptionPane;

public class Validaciones {
	// Pide un String no vacío. Si el usuario cancela, devuelve null.
    public static String ingresarString(String mensaje) {
        String dato;
        do {
            dato = JOptionPane.showInputDialog(null, mensaje);
            if (dato == null) return null;           // usuario canceló
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
            if (dato == null) return null; // usuario canceló
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
                JOptionPane.showMessageDialog(null, "Formato inválido. Ingrese sólo números (ej: 1234.56).");
            }
        } while (true);
    }
    
    
    public static double IngresarInt(String mensaje) {
        while (true) {
            String dato = JOptionPane.showInputDialog(null, mensaje);
            if (dato == null) {
                // El usuario canceló: devolvemos 0.0 (comportamiento seguro y no rompe llamadas existentes).
                return 0.0;
            }
            dato = dato.trim().replace(',', '.'); // aceptar coma como decimal
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
                JOptionPane.showMessageDialog(null, "Formato inválido. Ingrese sólo números (ej: 1234.56).");
            }
        }
    }

    /**
     * Pide un entero positivo. Devuelve 0 si cancela.
     */
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
                JOptionPane.showMessageDialog(null, "Formato inválido. Ingrese sólo números enteros.");
            }
        }
    }

    
}
