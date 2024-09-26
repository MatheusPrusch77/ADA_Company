package model;

import java.util.ArrayList;
import java.util.List;

import dao.UsuarioDAO;
import dao.exceptions.ExceptionDAO;

public final class Cliente extends Usuario {

	private static final long serialVersionUID = 1L;
	private String CNPJ;
	private String endereco;
	private List<Orcamento> listaOrcamentos = new ArrayList<>();
	
	public Cliente() {
		
	}
        
    public Cliente(String CNPJ, String nomeCompleto, Integer ID, String email, String senha, String telefone, String endereco) {
		super(nomeCompleto, ID, email, senha, telefone);
		this.CNPJ = CNPJ;
        this.endereco = endereco;
	}
	
	public Cliente(String CNPJ, String nomeCompleto, Integer ID, String email, String senha, String telefone) {
		super(nomeCompleto, ID, email, senha, telefone);
		this.CNPJ = CNPJ;
	}
	
	public Cliente(String email, String senha) {
		super(email, senha);
	}

	@Override
	public void editarPerfil(Usuario usuario) throws ExceptionDAO {
		new UsuarioDAO().editarUsuario(usuario);
	}

	public String getCNPJ() {
		return CNPJ;
	}

	public void setCNPJ(String CNPJ) {
		this.CNPJ = CNPJ;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	

}
