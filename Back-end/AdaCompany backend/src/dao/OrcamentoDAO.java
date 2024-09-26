package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.exceptions.ExceptionDAO;
import model.Cliente;
import model.Orcamento;
import model.Usuario;
import model.enums.StatusOrcamento;
import model.enums.TipoServico;

public class OrcamentoDAO {
	// CRUD Orcamentos.

	public void cadastrarOrcamento(Orcamento orc, Usuario user) throws ExceptionDAO {

		String sql = "insert into orcamento(ValidadeOrcamento, DataCriacao, ValorTotal, TipoServico, StatusOrc, Descricao, fk_IDCliente) value (?,?,?,?,?,?,?)";
		PreparedStatement pStatement = null;
		Connection connection = null;
		try {
			connection = new ConexaoBD().getConnection();
			pStatement = connection.prepareStatement(sql);
			pStatement.setDate(1, orc.getDataValidade());
			pStatement.setDate(2, orc.getDataCriacao());
			pStatement.setDouble(3, orc.getValor());
			pStatement.setString(4, orc.getTipoServico().toString());
			pStatement.setString(5, orc.getStatus().toString());
			pStatement.setString(6, orc.getDescricao());
			pStatement.setInt(7, user.getID());

			pStatement.execute();

		} catch (SQLException e) {
			throw new ExceptionDAO("Erro ao cadastrar orcamento: " + e);

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

	}

	// lista para a tabela da view 'GerenciarOrcamentos'
	public ArrayList<Orcamento> listarOrcamentos() throws ExceptionDAO {
		String sql = "select * from orcamento";
		Connection connection = null;
		PreparedStatement pStatement = null;
		ArrayList<Orcamento> orcamentos = null;

		try {
			connection = new ConexaoBD().getConnection();
			pStatement = connection.prepareStatement(sql);
			ResultSet rs = pStatement.executeQuery(sql);

			if (rs != null) {// se não houver dados n instancia orcamentos.
				orcamentos = new ArrayList<Orcamento>();

				while (rs.next()) {
					Usuario usuario = new Cliente();

					Orcamento orc = new Orcamento(usuario, TipoServico.valueOf(rs.getString("TipoServico")),
							rs.getString("Descricao"), rs.getDate("DataCriacao"), rs.getDate("ValidadeOrcamento"),
							rs.getDouble("ValorTotal"), StatusOrcamento.valueOf(rs.getString("StatusOrc")),
							rs.getString("EmailVendedor"));
					orc.setID(rs.getInt("ID"));
					orcamentos.add(orc); // final do while
				}

			}

		} catch (SQLException e) {
			throw new ExceptionDAO("Erro ao consultar Orcamentos no bd." + e);
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

		return orcamentos;
	}
	
	public void alterarStatusOrcamento(String status, int ID) throws ExceptionDAO {
		String sql = "UPDATE ORCAMENTO SET StatusOrc = ? WHERE ID = ?";
        PreparedStatement pStatement = null;
        Connection connection = null;
        try {
            connection = new ConexaoBD().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, status);
            pStatement.setInt(2, ID);

            int rowsAffected = pStatement.executeUpdate();

            if (rowsAffected == 0) {
                throw new ExceptionDAO("Nenhum orçamento foi alterado. Orçamento não encontrado: ");
            }
        } catch (SQLException e) {
            throw new ExceptionDAO("Erro ao executar a query de alteração de orçamento: " + e);
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
	
	
	public void editarOrcamento(int ID, Double novoValor, String descricao, String emailVendedor) throws ExceptionDAO {
		String sql = "UPDATE ORCAMENTO SET valorTotal = ?, descricao = ?, emailVendedor = ? WHERE ID = ?";
        PreparedStatement pStatement = null;
        Connection connection = null;
        
        try {
            connection = new ConexaoBD().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setDouble(1, novoValor);
            pStatement.setString(2, descricao);
            pStatement.setString(3, emailVendedor);
            pStatement.setInt(4, ID);

            int rowsAffected = pStatement.executeUpdate();

            if (rowsAffected == 0) {
                throw new ExceptionDAO("Nenhum orçamento foi alterado. Orçamento não encontrado: ");
            }
        } catch (SQLException e) {
            throw new ExceptionDAO("Erro ao executar a query de alteração de orçamento: " + e);
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
	


