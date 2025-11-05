import java.util.ArrayList;

public class Usuario {
	    private String mail;
	    private String contr;
	    private String alias;
	    private Rol rol;
	    private static ArrayList<Usuario> usuarios = new ArrayList<>();

	    public Usuario(String mail, String contr, Rol rol) {
	        this.mail = mail;
	        this.contr = contr;
	        this.alias = alias;
	        this.rol = rol;
	        usuarios.add(this); // opcional: para llevar registro
	    }
	    
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
	    
	    // Getters
	    public String getMail() {
	        return mail;
	    }

	    public String getContr() {
	        return contr;
	    }
	    
	    

	    public String getAlias() {
			return alias;
		}

		public void setAlias(String alias) {
			this.alias = alias;
		}

		public Rol getRol() {
	        return rol;
	    }

	    public static ArrayList<Usuario> getUsuarios() {
	        return usuarios;
	    }

	    @Override
	    public String toString() {
	        return "Usuario [mail=" + mail + ", rol=" + rol + "]";
	    }
}


