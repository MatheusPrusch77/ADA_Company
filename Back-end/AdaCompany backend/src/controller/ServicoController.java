/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.exceptions.ExceptionDAO;
import model.Funcionario;
import model.Servico;
import model.enums.TipoServico;
import service.LoginService;

public class ServicoController {
	LoginService loginService = LoginService.getInstance();

	public static TipoServico getTipoSelecionado(String tipo) {
		TipoServico tipoServico;
		if (tipo.equals("Adaptação de Site")) {
			tipoServico = TipoServico.ADAPTACAO;
		} else if (tipo.equals("Desenvolvimento de Site")) {
			tipoServico = TipoServico.DESENVOLVIMENTO;
		} else {
			tipoServico = TipoServico.CONSULTORIA;
		}
		return tipoServico;
	}

	public ArrayList<Servico> visualizarServicos(String input) throws ExceptionDAO, SQLException {
		return Servico.visualizarServicos(input);
	}

	public boolean alterarServico(String tipoServ, Double valor) throws ExceptionDAO {
		if (tipoServ.equals("Adaptação de Site") || tipoServ.equals("Desenvolvimento de Site")
				|| tipoServ.equals("Consultoria") && valor != null) {
			Funcionario func = (Funcionario) loginService.getUsuarioLogado();
			func.alterarServico(tipoServ, valor);
			return true;
		}
		return false;
	}

	public Servico consultaServico(String tipoServ) throws ExceptionDAO {
		return Servico.consultaServico(getTipoSelecionado(tipoServ).toString());
	}

}
