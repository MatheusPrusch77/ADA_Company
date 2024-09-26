package controller;

import dao.exceptions.ExceptionDAO;
import model.Usuario;
import service.LoginService;

public class LoginController {
	private LoginService loginService;
	
	public LoginController() {
		this.loginService = LoginService.getInstance();
	}

	public Usuario realizarLogin(String email, String senha) throws ExceptionDAO {
		return loginService.realizarLogin(email, senha);
    }
	
	public boolean validaLogin(String email, String senha) throws ExceptionDAO {
		return loginService.validaLogin(email, senha);
	}

    public Usuario getUsuarioLogado() {
        return loginService.getUsuarioLogado();
    }

    public void logout() {
        loginService.logout();
    }
    
}