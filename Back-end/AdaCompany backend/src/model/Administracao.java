package model;

import dao.exceptions.ExceptionDAO;

public interface Administracao {
	
	void cadastraAdmin(Usuario user) throws ExceptionDAO;
	void alterarServico(String tipoServ, Double valor) throws ExceptionDAO;
	void alterarStatusOrcamento(int ID, int escolha) throws ExceptionDAO;
	void editarOrcamento(Integer orcID, Double novoValor, String descricao, String emailVendedor) throws ExceptionDAO;
	
	//void addMsgChatbot();
	//void removerMsgChatbot();
}
