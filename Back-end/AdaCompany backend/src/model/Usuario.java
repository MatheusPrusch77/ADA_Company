package model;

import java.io.Serializable;

import dao.UsuarioDAO;
import dao.exceptions.ExceptionDAO;

public abstract class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	protected String nomeCompleto;
	protected Integer ID;
	protected String email;
	protected String senha;
	protected String telefone;
	
	
	public Usuario() {}

	public Usuario(String nomeCompleto, Integer ID, String email, String senha, String telefone) {
		this.nomeCompleto = nomeCompleto;
		this.ID = ID;
		this.email = email;
		this.senha = senha;
		this.telefone = telefone;
	}
	
	public Usuario(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}
	
	//metodos de login devem acessar BD. 
	public static Usuario realizarLogin(String email, String senha) throws ExceptionDAO {
		return new UsuarioDAO().realizarLogin(email, senha);
	}
	
	public static boolean validaLogin(String email, String senha) throws ExceptionDAO {
		return new UsuarioDAO().validaLogin(email, senha);
	}

	public void cadastrar(Usuario user) throws ExceptionDAO {
		new UsuarioDAO().cadastrar(user); //salva no BD.
	}

	public void recuperarSenha() {
	}

	public abstract void editarPerfil(Usuario user) throws ExceptionDAO;

	
	// ===== getters setters =====
	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Integer getID() {
		return this.ID;
	}
	
	public void setID(int ID) {
		this.ID = ID;
	}

}
