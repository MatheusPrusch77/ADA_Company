package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.exceptions.ExceptionDAO;
import model.Servico;
import model.enums.TipoServico;

public class ServicoDAO {

	public ArrayList<Servico> visualizarServicos(String input) throws ExceptionDAO, SQLException {
		String sql = "SELECT * FROM SERVICO WHERE Nome LIKE ?";
		PreparedStatement pStatement = null;
		Connection connection = null;
		ArrayList<Servico> servicos = new ArrayList<>();
		try {
			connection = new ConexaoBD().getConnection();
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, "%" + input + "%");
			ResultSet rs = pStatement.executeQuery();

			while (rs.next()) {
				Servico serv = new Servico(TipoServico.valueOf(rs.getString("TipoServico")), rs.getString("Nome"),
						rs.getDouble("Valor"));
				servicos.add(serv);
			}

		} finally {
			try {
				if (pStatement != null) {
					pStatement.close();
				}
			} catch (SQLException e) {
				throw new ExceptionDAO("Erro ao fechar o statement: " + e);

			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new ExceptionDAO("Erro ao fechar a conexao do BD:" + e);
			}
		}
		return servicos;
	}

	public Servico consultaServico(String tipoServ) throws ExceptionDAO {
		String sql = "SELECT TipoServico, Nome, Valor FROM SERVICO WHERE TipoServico = ?";
		PreparedStatement pStatement = null;
		Connection connection = null;
		Servico serv = null;
		try {
			connection = new ConexaoBD().getConnection();
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, tipoServ);
			ResultSet rs = pStatement.executeQuery();

			if (rs.next()) {
				serv = new Servico(TipoServico.valueOf(rs.getString("TipoServico")), rs.getString("Nome"),
						rs.getDouble("Valor"));
			}
		} catch (SQLException e) {
			throw new ExceptionDAO("Erro ao executar a query de alteração de serviço: " + e);
		} finally {
			try {
				if (pStatement != null) {
					pStatement.close();
				}
			} catch (SQLException e) {
				throw new ExceptionDAO("Erro ao fechar o statement: " + e);
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new ExceptionDAO("Erro ao fechar a conexão do BD: " + e);
			}
		}
		return serv;
	}

	public void alterarServico(String tipoServ, Double novoValor) throws ExceptionDAO {
		String sql = "UPDATE SERVICO SET VALOR = ? WHERE TipoServico = ?";
		PreparedStatement pStatement = null;
		Connection connection = null;
		try {
			connection = new ConexaoBD().getConnection();
			pStatement = connection.prepareStatement(sql);
			pStatement.setDouble(1, novoValor);
			pStatement.setString(2, tipoServ);

			int rowsAffected = pStatement.executeUpdate();

			if (rowsAffected == 0) {
				throw new ExceptionDAO("Nenhum serviço foi alterado. Tipo de serviço não encontrado: " + tipoServ);
			}
		} catch (SQLException e) {
			throw new ExceptionDAO("Erro ao executar a query de alteração de serviço: " + e);
		} finally {
			try {
				if (pStatement != null) {
					pStatement.close();
				}
			} catch (SQLException e) {
				throw new ExceptionDAO("Erro ao fechar o statement: " + e);
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new ExceptionDAO("Erro ao fechar a conexão do BD: " + e);
			}
		}
	}
}
