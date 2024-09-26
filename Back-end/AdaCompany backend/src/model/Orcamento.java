package model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

import dao.OrcamentoDAO;
import dao.exceptions.ExceptionDAO;
import model.enums.StatusOrcamento;
import model.enums.TipoServico;

public class Orcamento implements Serializable {

	private static final long serialVersionUID = 1L;
	private int ID;
	private Date dataCriacao;
	private Date dataValidade;
	private Usuario usuario;
	private double valor;
	private TipoServico tipoServico;
	private String descricao;
	private StatusOrcamento status;
	private String emailVendedor;
	
	
	//construtor para select no BD.
	public Orcamento(Usuario usuario, TipoServico tipo, String descricao, Date dataCriacao,
			Date dataValidade, double valor, StatusOrcamento status, String emailVendedor) {
		this.dataCriacao = dataCriacao;
		this.dataValidade = dataValidade;
		this.usuario = usuario;
		this.valor = valor;
		this.tipoServico = tipo;
		this.descricao = descricao;
		this.status = status;
		this.emailVendedor = emailVendedor;
	}

	public Orcamento(Usuario usuario, TipoServico tipo, String descricao) throws ExceptionDAO {
		this.usuario = usuario;
		this.valor = Servico.getValorBD(tipo);
		this.tipoServico = tipo;
		this.descricao = descricao;
		this.status = StatusOrcamento.ANALISE;
		this.dataCriacao =  new Date(System.currentTimeMillis());
                this.dataValidade = Date.valueOf(dataCriacao.toLocalDate().plusMonths(4));
                this.emailVendedor = null;
	}
	
	public void gerarOrcamento(Orcamento orc, Usuario user) throws ExceptionDAO {
		new OrcamentoDAO().cadastrarOrcamento(orc, user); //salva no BD.
	}
	
	public static ArrayList<Orcamento> listarOrcamentos() throws ExceptionDAO {
		return new OrcamentoDAO().listarOrcamentos(); //select no BD.
		
	}

	// ==== getters setters ====
	public Usuario getUsuario() {
		return usuario;
	}

	public double getValor() {
		return valor;
	}

	protected void setValor(double valor) {
		this.valor = valor;
	}
	
	protected void setTipoServico(TipoServico tipo) {
		this.tipoServico = tipo;
	}

	public TipoServico getTipoServico() {
		return tipoServico;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public StatusOrcamento getStatus() {
		return status;
	}

	public void setStatus(StatusOrcamento status) {
		this.status = status;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public Date getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}

	public String getEmailVendedor() {
		return this.emailVendedor;
	}
	
	public void setEmailVendedor(String email) {
		this.emailVendedor = email;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
		

}
