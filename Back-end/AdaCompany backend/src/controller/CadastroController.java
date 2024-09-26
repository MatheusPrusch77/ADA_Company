package controller;

import java.util.ArrayList;

import dao.exceptions.ExceptionDAO;
import model.Cliente;
import model.Funcionario;
import model.Usuario;

public class CadastroController {
	public CadastroController() {}
	
	public boolean cadastrarUsuario(String CNPJ, String nome, Integer ID, String email, String senha, String telefone) throws ExceptionDAO {
		if (nome != null && email.contains("@") && senha != null && senha.length()>5 && telefone != null) {
			Usuario user = new Cliente(CNPJ, nome, ID, email, senha, telefone);
			user.cadastrar(user);//BD ou arquivo.
			return true;
		}
		return false;
	}
        
        public boolean cadastrarUsuario(String CNPJ, String nome, Integer ID, String email, String senha, String telefone, String endereco) throws ExceptionDAO {
		if (nome != null && email.contains("@") && senha != null && senha.length()>5 && telefone != null) {
			Usuario user = new Cliente(CNPJ, nome, ID, email, senha, telefone, endereco);
			user.cadastrar(user);//BD ou arquivo.
			return true;
		}
		return false;
	}
	
	public boolean cadastrarAdmin(String cargo, String nome, Integer ID, String email, String senha, String telefone) throws ExceptionDAO {
		if (nome != null && email.contains("@") && senha != null && senha.length()>5 && telefone != null && cargo.length() > 2) {
			
			Usuario user = new Funcionario(nome, email, ID, senha, telefone, cargo);
			user.cadastrar(user); //BD ou arquivo.
			return true;
		}
		return false;
	}
	
	public ArrayList<Usuario> consultarUsuarios(String nome, int tipo) throws ExceptionDAO {
		if (nome != null) {
			return new Funcionario().consultarUsuarios(nome, tipo);
		}
		return null;
	}
	
	public boolean editarPerfil(Usuario user) throws ExceptionDAO {
		if (user instanceof Funcionario) {
			new Funcionario().editarPerfil(user);
			return true;
		}
		else {
			new Cliente().editarPerfil(user);
			return true;
		}
	}
	
  
}
