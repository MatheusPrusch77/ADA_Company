package main;

import dao.exceptions.ExceptionDAO;
import model.Cliente;
import model.Orcamento;
import model.enums.TipoServico;

public class Program {

	public static void main(String[] args) throws ExceptionDAO {
		// TESTE console
		Orcamento orc = new Orcamento(new Cliente(), TipoServico.ADAPTACAO, "Orcamento descricao");
		
		System.out.println(orc.getValor());
		System.out.println(orc.getDescricao());
		System.out.println(orc.getDataCriacao());
		System.out.println(orc.getDataValidade());
		System.out.println(orc.getTipoServico());
		
	}

}
