package service;

import dao.exceptions.ExceptionDAO;
import model.Usuario;

public class LoginService {
	private static LoginService instance;
	private Usuario usuarioLogado;
	
	private LoginService() {} //construtor privado para previnir instanciar novamente.
	
	public static LoginService getInstance() {
	    if (instance == null) {
	        instance = new LoginService();
	    }
	    return instance;
	}
	
    public Usuario realizarLogin(String email, String senha) throws ExceptionDAO {
        Usuario usuario = Usuario.realizarLogin(email, senha);
        if (usuario != null) {
            this.usuarioLogado = usuario;
        }
        return usuario;
    }
    
    public boolean validaLogin(String email, String senha) throws ExceptionDAO {
    	return Usuario.validaLogin(email, senha);
    }

	public void logout() {
		usuarioLogado = null;
	}

	public Usuario getUsuarioLogado() {
		return this.usuarioLogado;
	}
}
