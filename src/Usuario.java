import java.util.ArrayList;

public class Usuario {
    protected String mail;
    protected String contr;
    protected String alias;
    protected Rol rol;

    public Usuario(String mail, String contr, String alias, Rol rol) {
        this.mail = mail;
        this.contr = contr;
        this.alias = alias;
        this.rol = rol;
    }
    
 // Getters y setters

    public String getMail() { return mail; }
    public String getContr() { return contr; }
    public String getAlias() { return alias; }
    public Rol getRol() { return rol; }

    public void setAlias(String alias) {
        this.alias = alias;
    }
	    
	   
		@Override
		public String toString() {
			return "Usuario [mail=" + mail + ", contr=" + contr + ", rol=" + rol + ", getClass()=" + getClass()
					+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
		}
		 private static ArrayList<Usuario> usuarios = new ArrayList<>();
		// Buscar Usuarios
	    
	    public static Usuario buscarPorMail(String mail) {
	        for (Usuario userEvaluado : usuarios) {
	            if (userEvaluado.getMail().equalsIgnoreCase(mail)) {
	                return userEvaluado;
	            }
	        }
	        return null;
	    }

	    public static Usuario buscarPorAlias(String alias) {
	        for (Usuario user : usuarios) {
	            if (user.getAlias().equalsIgnoreCase(alias)) {
	                return user;
	            }
	        }
	        return null;
	    }
	    
	    //lista solo de clientes
	    public static ArrayList<Cliente> getClientes() {
	        ArrayList<Cliente> lista = new ArrayList<>();
	        for (Usuario u : usuarios) {
	            if (u instanceof Cliente) {
	                lista.add((Cliente) u);
	            }
	        }
	        return lista;
	    }
		
}


