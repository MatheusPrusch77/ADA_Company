package controller;

import java.util.ArrayList;

import dao.OrcamentoDAO;
import dao.exceptions.ExceptionDAO;
import model.Funcionario;
import model.Orcamento;
import model.Usuario;
import model.enums.TipoServico;
import service.LoginService;

public class OrcamentoController {
	LoginService loginService = LoginService.getInstance();
	
	public OrcamentoController() {}
	
	public boolean gerarOrcamento(Usuario user, String tipo, String descricao) throws ExceptionDAO {
		if (user != null && tipo != null && !descricao.isEmpty()) {
			TipoServico tipoServ = ServicoController.getTipoSelecionado(tipo);
			Orcamento orc = new Orcamento(user, tipoServ, descricao);
			orc.gerarOrcamento(orc, user);
            return true;
		}
		else
            return false;
	}
	
	public static ArrayList<Orcamento>listarOrcamentos() throws ExceptionDAO {
		return Orcamento.listarOrcamentos();
	}

	public void alterarStatusOrcamento(int ID, int escolha) throws ExceptionDAO {
		Funcionario func = (Funcionario) loginService.getUsuarioLogado();
		func.alterarStatusOrcamento(ID, escolha);
	}
    
	public void editarOrcamento(Integer orcID, Double novoValor, String descricao, String emailVendedor) throws ExceptionDAO {
		//Funcionario func = (Funcionario) loginService.getUsuarioLogado();
		if (orcID != null && novoValor >= 1000.0) {
			Funcionario func = (Funcionario) loginService.getUsuarioLogado();
			func.editarOrcamento(orcID, novoValor, descricao, emailVendedor);
		}
		
	}
}
