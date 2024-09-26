package model;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ServicoDAO;
import dao.exceptions.ExceptionDAO;
import model.enums.TipoServico;

public class Servico implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TipoServico tipo;
	private String nome;
	private Double valor;

	public Servico(TipoServico tipo, String nome, Double valor) {
		this.tipo = tipo;
		this.nome = nome;
		this.valor = valor;
	}

	public static Servico consultaServico(String tipoServ) throws ExceptionDAO {
		return new ServicoDAO().consultaServico(tipoServ);
	}

	public static ArrayList<Servico> visualizarServicos(String input) throws ExceptionDAO, SQLException {
		return new ServicoDAO().visualizarServicos(input);
	}

	// ======== getters setters =======================

	public TipoServico getTipo() {
		return tipo;
	}

	public void setTipo(TipoServico tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public static Double getValorBD(TipoServico tipo) throws ExceptionDAO {
		if (tipo.equals(TipoServico.ADAPTACAO))
			return new ServicoDAO().consultaServico("ADAPTACAO").getValor();
		else if (tipo.equals(TipoServico.DESENVOLVIMENTO))
			return new ServicoDAO().consultaServico("DESENVOLVIMENTO").getValor();
		else
			return new ServicoDAO().consultaServico("CONSULTORIA").getValor();
	}

	public Double getValor() {
		return this.valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
