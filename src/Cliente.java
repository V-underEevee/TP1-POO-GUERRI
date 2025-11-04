import javax.swing.JOptionPane;

public class Cliente extends Usuario {
private int saldo;

public Cliente(String mail, String contr, Rol rol, int saldo) {
	super(mail, contr, rol);
	this.saldo = saldo;
}

public int getSaldo() {
	return saldo;
}

public void setSaldo(int saldo) {
	this.saldo = saldo;
}

@Override
public String toString() {
	return "Cliente [saldo=" + saldo + ", toString()=" + super.toString() + ", getMail()=" + getMail() + ", getContr()="
			+ getContr() + ", getRol()=" + getRol() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
}
public void Menu() {

	int opcion = JOptionPane.showOptionDialog(null, "Bienvenido cliente", "", 0, 0, null, this.getRol().getOpciones(),this.getRol().getOpciones()[0]);
}
}
