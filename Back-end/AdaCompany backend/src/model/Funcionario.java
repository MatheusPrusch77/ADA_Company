package model;

import dao.OrcamentoDAO;
import dao.ServicoDAO;
import java.util.ArrayList;
import controller.ServicoController;
import dao.UsuarioDAO;
import dao.exceptions.ExceptionDAO;

public class Funcionario extends Usuario implements Administracao {
	private static final long serialVersionUID = 1L;
	String cargo;
	
	public Funcionario() {
		
	}
	
	public Funcionario(String email, String senha){
		super(email, senha);
	}

	public Funcionario(String nomeCompleto, String email, Integer ID, String senha, String telefone, String cargo) {
		super(nomeCompleto, ID, email, senha, telefone);
		this.cargo = cargo;
	}
	
	public ArrayList<Usuario> consultarUsuarios(String nome, int tipoConsulta) throws ExceptionDAO {
		return new UsuarioDAO().consultarUsuarios(nome, tipoConsulta);
	}

	@Override
	public void alterarServico(String tipoServ, Double valor) throws ExceptionDAO {
		new ServicoDAO().alterarServico(ServicoController.getTipoSelecionado(tipoServ).toString(), valor);
	}

	@Override
	public void editarOrcamento(Integer orcID, Double novoValor, String descricao, String emailVendedor) throws ExceptionDAO {
		new OrcamentoDAO().editarOrcamento(orcID, novoValor, descricao, emailVendedor);
	}

	@Override
	public void alterarStatusOrcamento(int ID, int escolha) throws ExceptionDAO {
		if (escolha == 1)
			new OrcamentoDAO().alterarStatusOrcamento("ANALISE", ID);
		else if (escolha == 2) 
			new OrcamentoDAO().alterarStatusOrcamento("CANCELADO", ID);
		else if (escolha == 3)
			new OrcamentoDAO().alterarStatusOrcamento("CONCLUIDO", ID);
		else
			new OrcamentoDAO().alterarStatusOrcamento("DESENVOLVIMENTO", ID);
	}

	@Override
	public void editarPerfil(Usuario user) throws ExceptionDAO {
		new UsuarioDAO().editarUsuario(user);
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	@Override
	public void cadastraAdmin(Usuario user) throws ExceptionDAO {
		new UsuarioDAO().cadastrar(user);
	}


}
